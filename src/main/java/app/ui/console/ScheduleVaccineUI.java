package app.ui.console;

import app.controller.App;
import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class ScheduleVaccineUI implements Runnable {


    private Company c = App.getInstance().getCompany();
    private AuthFacade aF = c.getAuthFacade();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {


        if (!c.getVaccineTypes().isEmpty() && !c.getVaccinationCenters().isEmpty() && !c.getSNSUserList().isEmpty()) {
            System.out.println();
            String SNSNumber = introduceSnsNumberUI();
            VaccinationCenter vaccinationCenter = selectVaccinationCenterUI();

            VaccineType vaccineType = selectVaccineTypeUI(vaccinationCenter);
            if (vaccineType == null) {
                return;
            }

            LocalDateTime date = selectDateUI(vaccinationCenter);

            System.out.println("Sucesso:  " + date); //TEMP

        } else {
            System.out.println();
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
            System.out.println("| There are no Vaccine Types or Vaccination Centers or SNS Users yet. Please add at least one of each first. |");
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
        }


    }

    private String introduceSnsNumberUI() {
        String SNSNumber;
        boolean check = false;
        do {
            System.out.print("Introduce your SNS Number: ");
            SNSNumber = sc.next();
            System.out.println();
            sc.nextLine();
            if (Utils.validateSNSUserNumber(SNSNumber)) {

                for (SNSUser snsUser : c.getSNSUserList()) {
                    if (snsUser.getStrSNSUserNumber().contains(SNSNumber)) {

                        if (snsUser.getStrSNSUserNumber().equals(SNSNumber)) {

                            Email snsUserEmail = new Email(snsUser.getStrEmail());
                            if (aF.getCurrentUserSession().getUserId().equals(snsUserEmail)) {
                                return SNSNumber;
                            } else {
                                System.out.println("That is not your SNS number");
                            }
                        }
                    } else {
                        System.out.println("There is no such SNS number.");
                    }
                }

            } else {
                System.out.println("--Invalid number--");
                System.out.println();
            }

        } while (!check);
        return SNSNumber;
    }

    public VaccinationCenter selectVaccinationCenterUI() {
        return c.getVaccinationCenters().get(Utils.selectFromList(c.getVaccinationCenters(), "Select one Vaccination Center"));
    }

    public VaccineType selectVaccineTypeHealthCareCenterUI(HealthcareCenter healthcareCenter) {
        return healthcareCenter.getVaccineTypes().get(Utils.selectFromList(healthcareCenter.getVaccineTypes(), "Select one Vaccine Type"));
    }

    public VaccineType selectVaccineTypeUI(VaccinationCenter vaccinationCenter) {
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

    public LocalDateTime selectDateUI(VaccinationCenter vaccinationCenter) {
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
            if (slotHasAvailability(vaccinesPerSlot, selectedDate, timeOfTheSlot, appointmentsList))
                System.out.println(slot + 1 + " - " + timeOfTheSlot);

            timeOfTheSlot = timeOfTheSlot.plusMinutes(slotDuration);
        }
        /*
        1 --- opening hour + 0*slotDuration
        2 --- opening hour + 1*slotDuration
        3 --- opening hour + 2*slotDuration
        4 --- opening hours + 3*slotDuration
         */

        System.out.printf("%nType your option: ");
        int selectedOption = Utils.insertInt("Insert a valid option: ");

        LocalTime openingHourCenter = LocalTime.of(openingHour, 0);
        int minutesToBeAdded = selectedOption * slotDuration;
        LocalTime timeSelected = openingHourCenter.plusMinutes(minutesToBeAdded);

        return LocalDateTime.of(selectedDate, timeSelected);

    }

    public LocalDate selectDateFromCurrentMonth(List<ScheduledVaccine> appointmentsList, int slotsPerDay, int vaccinesPerSlot) {
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
        System.out.printf("%nType your option: ");
        int selectedOption = Utils.insertInt("Insert a valid option: ");
        int selectedDay = dateWhenScheduling.getDayOfMonth() + selectedOption;
        return LocalDate.of(LocalDate.now().getYear(), dateWhenScheduling.getMonthValue(), selectedDay);

    }

    public LocalDate selectDateFromNextMonth(List<ScheduledVaccine> appointmentsList, int slotsPerDay, int vaccinesPerSlot) {
        int optionNumber = 1;
        LocalDate dateWhenScheduling = LocalDate.now();
        LocalDate nextMonthdate = dateWhenScheduling.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        for (int date = nextMonthdate.getDayOfMonth(); date <= YearMonth.of(nextMonthdate.getYear(), nextMonthdate.getMonthValue()).lengthOfMonth(); date++) {
            if (dayHasAvailability(slotsPerDay, vaccinesPerSlot, LocalDate.of(LocalDate.now().getYear(), nextMonthdate.getMonthValue(), date), appointmentsList)) {
                if (nextMonthdate.getMonthValue() < 10)
                    System.out.println(optionNumber + " - " + date + "/" + "0" + nextMonthdate.getMonthValue());
                else
                    System.out.println(optionNumber + " - " + date + "/" + nextMonthdate.getMonthValue());
                optionNumber++;
            }
        }
        optionNumber = 0;
        System.out.printf("%n" + optionNumber + " - Previous Month%n");
        System.out.printf("%nType your option: ");
        int selectedOption = Utils.insertInt("Insert a valid option: ");
        if (selectedOption == 0)
            return LocalDate.of(LocalDate.now().getYear(), dateWhenScheduling.getMonthValue(), LocalDate.now().getDayOfMonth());
        return LocalDate.of(LocalDate.now().getYear(), nextMonthdate.getMonthValue(), selectedOption);

    }

    public int calculateSlotsPerDay(int openingHour, int closingHour, int slotDuration) {
        return (closingHour - openingHour) * 60 / slotDuration;
    }

    public boolean dayHasAvailability(int slotsPerDay, int vaccinesPerSlot, LocalDate date, List<ScheduledVaccine> appointments) {
        int vaccinesPerDay = slotsPerDay * vaccinesPerSlot;
        int counterAppointments = 0;

        for (ScheduledVaccine appointment : appointments) {
            if ((appointment.getDate().toLocalDate()).equals(date))
                counterAppointments++;

            if (counterAppointments == vaccinesPerDay)
                return false;
        }

        return true;
        // Praticamente ir à lista e ver para o dia que isto recebe se: há pelo menos um slot que esteja livre (livre = ter pelo menos 1 espaço para vacina)
    }

    public boolean slotHasAvailability(int vaccinesPerSlot, LocalDate date, LocalTime slot, List<ScheduledVaccine> appointments) {
        int counterAppointments = 0;
        for (ScheduledVaccine appointment : appointments) {
            if (((appointment.getDate().toLocalDate()).equals(date)) && (appointment.getDate().toLocalTime().equals(slot))) {
                counterAppointments++;
            }
        }
        return counterAppointments != vaccinesPerSlot;
    }
}