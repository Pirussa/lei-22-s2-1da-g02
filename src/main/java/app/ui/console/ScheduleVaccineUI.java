package app.ui.console;

import app.controller.App;
import app.controller.ScheduledVaccineController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;


import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class ScheduleVaccineUI implements Runnable {

    private final Company company = App.getInstance().getCompany();
    private final AuthFacade aF = company.getAuthFacade();
    private final Scanner sc = new Scanner(System.in);
    private final ScheduledVaccineController controller = new ScheduledVaccineController();


    /**
     * When the run() method calls, the code specified in the run() method is executed.
     */
    @Override
    public void run() {
        if (Utils.arrayListIsNotEmpty(company.getVaccineTypes(), company.getVaccinationCenters(), company.getSNSUserList())) {
            System.out.println();
            int snsNumber = introduceSnsNumberUI();

            VaccinationCenter vaccinationCenter  = Utils.selectVaccinationCenterUI();
            VaccineType vaccineType = selectVaccineTypeUI(vaccinationCenter);
            if (vaccineType == null) return;

            LocalDateTime date = selectDateUI(vaccinationCenter);

            ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
            scheduledVaccineDto.snsNumber = snsNumber;
            scheduledVaccineDto.vaccineType = vaccineType;
            scheduledVaccineDto.date = date;

            if (aF.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST)) {
                if (controller.validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, vaccinationCenter)) {
                    printAppointmentInfo(scheduledVaccineDto, vaccinationCenter);
                    if (Utils.confirmCreation()) {
                        if (controller.scheduleVaccine(scheduledVaccineDto, vaccinationCenter)) {
                            printValidAppointmentInfo(scheduledVaccineDto, vaccinationCenter);
                            try {
                                printAppointmentToFile(scheduledVaccineDto, vaccinationCenter);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            System.out.printf("%n-----------------------------|No appointment was registered|-----------------------------%n");
                    } else
                        System.out.printf("%n-----------------------------|No appointment was registered|-----------------------------%n");
                } else
                    System.out.printf("%nOops, something went wrong. Please try again!%nCommon causes: Your age doesn't meet any of the existing age groups or the waiting time since the last dose isn´t finished.");
            } else {
                if (controller.validateAppointment(scheduledVaccineDto, vaccinationCenter)) {
                    printAppointmentInfo(scheduledVaccineDto, vaccinationCenter);
                    if (Utils.confirmCreation()) {
                        if (controller.scheduleVaccine(scheduledVaccineDto, vaccinationCenter)) {
                            printValidAppointmentInfo(scheduledVaccineDto, vaccinationCenter);
                            try {
                                printAppointmentToFile(scheduledVaccineDto, vaccinationCenter);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else
                            System.out.printf("%n-----------------------------|No appointment was registered|-----------------------------%n");
                    } else
                        System.out.printf("%n-----------------------------|No appointment was registered|-----------------------------%n");
                } else {
                    System.out.printf("%nOops, something went wrong. Please try again!%nCommon causes: You already have an appointment for that vaccine; Your slot is not available anymore. ");
                }
            }
        } else
            printInvalidAppointment();
    }

    private int introduceSnsNumberUI() {
        int SNSNumber = 0;
        boolean check = false;
        do {
            System.out.print("Introduce your SNS Number: ");
            try {
                SNSNumber = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Insert a valid option.");
                sc.nextLine();
            }
            System.out.println();
            if (Utils.validateSNSUserNumber(SNSNumber)) {
                boolean flag = false;
                for (SNSUser snsUser : company.getSNSUserList()) {

                    if (snsUser.getSnsUserNumber() == (SNSNumber)) {
                        flag = true;
                        Email snsUserEmail = new Email(snsUser.getStrEmail());
                        if (aF.getCurrentUserSession().getUserId().equals(snsUserEmail) || aF.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST)) {
                            return SNSNumber;
                        } else {
                            System.out.println("That is not your SNS number.");
                        }
                    }

                }
                if (!flag) System.out.println("There is no such SNS number.");


            } else {
                System.out.println("Invalid number.");
                System.out.println();
            }

        }
        while (!check);
        return SNSNumber;
    }

    private VaccineType selectVaccineTypeHealthCareCenterUI(HealthcareCenter healthcareCenter) {
        return healthcareCenter.getVaccineTypes().get(Utils.selectFromList(healthcareCenter.getVaccineTypes(), "Select one Vaccine Type"));
    }

    private VaccineType selectVaccineTypeUI(VaccinationCenter vaccinationCenter) {
        if (vaccinationCenter instanceof MassVaccinationCenter) {
            MassVaccinationCenter massVacCenter = (MassVaccinationCenter) vaccinationCenter;
            System.out.println();
            System.out.println("The available Vaccine Type for " + massVacCenter + " is: " + massVacCenter.getVaccineType());
            System.out.printf("%nDo you want to proceed?%n1 - Yes%n2 - No%n%nType your option: ");
            boolean check;
            do {
                int proceedVerification = Utils.insertInt("Insert a valid option: ");
                if (proceedVerification == 1) {
                    check = true;
                } else if (proceedVerification == 2) {
                    return null;
                } else {
                    System.out.println("Insert a valid option: ");
                    check = false;
                }
            } while (!check);

            return massVacCenter.getVaccineType();

        } else if (vaccinationCenter instanceof HealthcareCenter) {
            HealthcareCenter healthcareCenter = (HealthcareCenter) vaccinationCenter;
            return selectVaccineTypeHealthCareCenterUI(healthcareCenter);
        }
        return null;
    }

    private LocalDateTime selectDateUI(VaccinationCenter vaccinationCenter) {
        List<ScheduledVaccine> appointmentsList = vaccinationCenter.getScheduledVaccineList();
        int openingHour = Integer.parseInt(vaccinationCenter.getStrOpeningHour());
        int closingHour = Integer.parseInt(vaccinationCenter.getStrClosingHour());
        int vaccinesPerSlot = Integer.parseInt(vaccinationCenter.getStrVaccinesPerSlot());
        int slotDuration = Integer.parseInt(vaccinationCenter.getStrSlotDuration());
        int slotsPerDay = calculateSlotsPerDay(openingHour, closingHour, slotDuration);
        LocalDate dateWhenScheduling = LocalDate.now();

        System.out.printf("%nChoose the Date for the appointment:%n");
        boolean check = false;

        LocalDate selectedDate;
        do {

            selectedDate = selectDateFromCurrentMonth(appointmentsList, slotsPerDay, vaccinesPerSlot);

            if (selectedDate.equals(dateWhenScheduling)) {
                selectedDate = selectDateFromNextMonth(appointmentsList, slotsPerDay, vaccinesPerSlot);
            } else check = true;

            if (!(selectedDate.getMonth() == dateWhenScheduling.getMonth())) {
                check = true;
            }


        } while (!check);


        System.out.printf("%nChoose the time:%n");
        LocalTime timeOfTheSlot = LocalTime.of(openingHour, 0);

        for (int slot = 0; slot < slotsPerDay; slot++) {
            if (Utils.slotHasAvailability(vaccinesPerSlot, selectedDate, timeOfTheSlot, appointmentsList))
                System.out.println(slot + 1 + " - " + timeOfTheSlot);

            timeOfTheSlot = timeOfTheSlot.plusMinutes(slotDuration);
        }

        boolean flag;
        int selectedOption;
        LocalTime timeSelected;
        do {
            System.out.printf("%nType your option: ");
            selectedOption = Utils.insertInt("Insert a valid option: ");
            LocalTime openingHourCenter = LocalTime.of(openingHour, 0);
            LocalTime closingHourCenter = LocalTime.of(closingHour, 0);
            int minutesToBeAdded;
            flag = true;
            if (selectedOption > 0) {
                minutesToBeAdded = (selectedOption - 1) * slotDuration;
            } else {
                flag = false;
                minutesToBeAdded = 0;
                System.out.println("Invalid option.");
            }
            timeSelected = openingHourCenter.plusMinutes(minutesToBeAdded);

            if (timeSelected.isBefore(openingHourCenter) || timeSelected.isAfter(closingHourCenter) || selectedOption > slotsPerDay) {
                flag = false;
                System.out.println("Invalid option.");
            }

        } while (!flag);

        return LocalDateTime.of(selectedDate, timeSelected);

    }

    private LocalDate selectDateFromCurrentMonth(List<ScheduledVaccine> appointmentsList, int slotsPerDay,
                                                 int vaccinesPerSlot) {
        LocalDate dateWhenScheduling = LocalDate.now();

        int optionNumber = 1;
        int date;
        for (date = dateWhenScheduling.getDayOfMonth() + 1; date <= YearMonth.of(dateWhenScheduling.getYear(), dateWhenScheduling.getMonthValue()).lengthOfMonth(); date++) {

            if (dayHasAvailability(slotsPerDay, vaccinesPerSlot, LocalDate.of(LocalDate.now().getYear(), dateWhenScheduling.getMonthValue(), date), appointmentsList)) {
                if (dateWhenScheduling.getMonthValue() < 10)
                    System.out.println(optionNumber + " - " + date + "/" + "0" + dateWhenScheduling.getMonthValue());
                else
                    System.out.println(optionNumber + " - " + date + "/" + dateWhenScheduling.getMonthValue());
                optionNumber++;
            }
        }
        optionNumber = 0;
        System.out.printf("%n" + optionNumber + " - Next Month%n");
        boolean flag;
        int selectedDay;
        do {
            System.out.printf("%nType your option: ");
            int selectedOption = Utils.insertInt("Insert a valid option: ");
            selectedDay = dateWhenScheduling.getDayOfMonth() + selectedOption;
            flag = true;
            if (selectedDay > YearMonth.of(dateWhenScheduling.getYear(), dateWhenScheduling.getMonthValue()).lengthOfMonth()) {
                flag = false;
                System.out.println("Invalid option.");
            }

        } while (!flag);

        return LocalDate.of(LocalDate.now().getYear(), dateWhenScheduling.getMonthValue(), selectedDay);
    }

    private LocalDate selectDateFromNextMonth(List<ScheduledVaccine> appointmentsList, int slotsPerDay,
                                              int vaccinesPerSlot) {
        int optionNumber = 1;
        LocalDate dateWhenScheduling = LocalDate.now();
        LocalDate nextMonthDate = dateWhenScheduling.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        for (int date = nextMonthDate.getDayOfMonth(); date <= YearMonth.of(nextMonthDate.getYear(), nextMonthDate.getMonthValue()).lengthOfMonth(); date++) {
            if (dayHasAvailability(slotsPerDay, vaccinesPerSlot, LocalDate.of(LocalDate.now().getYear(), nextMonthDate.getMonthValue(), date), appointmentsList)) {
                if (nextMonthDate.getMonthValue() < 10)
                    System.out.println(optionNumber + " - " + date + "/" + "0" + nextMonthDate.getMonthValue());
                else
                    System.out.println(optionNumber + " - " + date + "/" + nextMonthDate.getMonthValue());
                optionNumber++;
            }
        }
        optionNumber = 0;
        System.out.printf("%n" + optionNumber + " - Previous Month%n");
        boolean flag;
        int selectedDay;
        do {
            System.out.printf("%nType your option: ");
            selectedDay = Utils.insertInt("Insert a valid option: ");
            flag = true;
            if (selectedDay > YearMonth.of(dateWhenScheduling.getYear(), nextMonthDate.getMonthValue()).lengthOfMonth()) {
                flag = false;
                System.out.println("Invalid option.");
            }
        } while (!flag);

        if (selectedDay == 0)
            return LocalDate.of(LocalDate.now().getYear(), dateWhenScheduling.getMonthValue(), LocalDate.now().getDayOfMonth());
        return LocalDate.of(LocalDate.now().getYear(), nextMonthDate.getMonthValue(), selectedDay);

    }

    private int calculateSlotsPerDay(int openingHour, int closingHour, int slotDuration) {
        return (closingHour - openingHour) * 60 / slotDuration;
    }

    private boolean dayHasAvailability(int slotsPerDay, int vaccinesPerSlot, LocalDate
            date, List<ScheduledVaccine> appointments) {
        int vaccinesPerDay = slotsPerDay * vaccinesPerSlot;
        int counterAppointments = 0;

        for (ScheduledVaccine appointment : appointments) {
            if ((appointment.getDate().toLocalDate()).equals(date))
                counterAppointments++;

            if (counterAppointments == vaccinesPerDay)
                return false;
        }

        return true;
    }

    private void printAppointmentInfo(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        System.out.printf("%n-------------------------%n|Appointment Information|%n-------------------------%n%n");
        System.out.printf("Given SNS Number: " + scheduledVaccineDto.snsNumber + "%n%nSelected Vaccination Center: " + vaccinationCenter + "%n%nSelected Vaccine Type: " + scheduledVaccineDto.vaccineType + "%n%nDate: " + Utils.formatDateToPrint(scheduledVaccineDto.date.toLocalDate()) + "%n%nTime: " + scheduledVaccineDto.date.toLocalTime() + "%n%n");
    }

    private void printValidAppointmentInfo(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter
            vaccinationCenter) {
        System.out.printf("%n----------------------%n|Scheduling completed|%n----------------------%n%nYou have an appointment to take a %s vaccine, at %s in %s, on %s.%n%n", scheduledVaccineDto.vaccineType, scheduledVaccineDto.date.toLocalTime(), Utils.formatDateToPrint(scheduledVaccineDto.date.toLocalDate()), vaccinationCenter);
    }

    private void printInvalidAppointment() {
        System.out.printf("System is unable to schedule a vaccination without at least:%n- One Vaccination Center;%n- One Vaccine Type;%n- One Know System User.");
    }

    private void printAppointmentToFile(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter
            vaccinationCenter) throws IOException {
        System.out.printf("---------%n|SMS.txt|%n---------%n%n");
        int options = Utils.selectFromList(List.of(Constants.options), "Do you want to create an SMS.txt with the appointment information");
        if (options == 0) {
            PrintWriter printWriter = new PrintWriter(Constants.PATH_SMS_MESSAGE);
            printWriter.printf("You have an appointment to take a %s vaccine, at %s in %s, on %s.", scheduledVaccineDto.vaccineType, scheduledVaccineDto.date.toLocalTime(), Utils.formatDateToPrint(scheduledVaccineDto.date.toLocalDate()), vaccinationCenter);
            printWriter.close();
        }
    }
}