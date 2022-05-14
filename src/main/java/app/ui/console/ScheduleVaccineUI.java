package app.ui.console;

import app.controller.App;
import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;


import java.time.LocalDate;
import java.util.*;

public class ScheduleVaccineUI implements Runnable {


    private Company c = App.getInstance().getCompany();
    private AuthFacade aF = c.getAuthFacade();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        Company c = App.getInstance().getCompany();

        //selectDateUI(c.getMassVaccinationCenter().get(0));

        if (!c.getVaccineTypes().isEmpty() && !c.getVaccinationCenters().isEmpty() && !c.getSNSUserList().isEmpty()) {
            System.out.println();
            String SNSNumber = introduceSnsNumberUI();
            VaccinationCenter vaccinationCenter = selectVaccinationCenterUI();
            VaccineType vaccineType = selectVaccineTypeUI(vaccinationCenter);
            if (vaccineType == null) {
                return;
            }


            System.out.println("Sucesso"); //TEMP

        } else {
            System.out.println();
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
            System.out.println("| There are no Vaccine Types or Vaccination Centers or SNS Users yet. Please add at least one of each first. |");
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
        }


    }

    private Date selectDateUI(VaccinationCenter vaccinationCenter) {
        List<ScheduledVaccine> appointmentsList = vaccinationCenter.getScheduledVaccineList();
        int openingHour = Integer.parseInt(vaccinationCenter.getStrOpeningHour());
        int closingHour = Integer.parseInt(vaccinationCenter.getStrClosingHour());
        int vaccinesPerSlot = Integer.parseInt(vaccinationCenter.getStrVaccinesPerSlot());
        int slotDuration = Integer.parseInt(vaccinationCenter.getStrSlotDuration());
        List<Date> datesWithAppointmentsList = new ArrayList<>();

        //       ------------------------------IMPLEMENTATION-----------------------------------------

        LocalDate dateWhenScheduling = LocalDate.now() ;

        System.out.printf("%nChoose one Date to take your Vaccine:%n");
        boolean check;

        for (int date = 0; date <5  ; date++) {

        }







        /*do {

            Calendar dateWhenScheduling = Calendar.getInstance();
            dateWhenScheduling.set(dateWhenScheduling.get(Calendar.YEAR), dateWhenScheduling.get(Calendar.MONTH), dateWhenScheduling.get(Calendar.DATE));
            int optionNumber = 1;
            for (int dayToSelect = dateWhenScheduling.get(Calendar.DATE) + 1; dayToSelect <= dateWhenScheduling.getActualMaximum(Calendar.DAY_OF_MONTH); dayToSelect++) {

                    System.out.println(optionNumber + " - " + dayToSelect + "/" + (dateWhenScheduling.get(Calendar.MONTH) + 1));
                optionNumber++;
            }
            System.out.println(optionNumber + " - Next Month");
            System.out.printf("%nType your option: ");
            int daySelected = Utils.insertInt("Insert a valid option: ");
            System.out.println();
            Date selectedDate;
            if (daySelected == optionNumber) {
                optionNumber = 1;
                dateWhenScheduling.set(dateWhenScheduling.get(Calendar.YEAR), dateWhenScheduling.get(Calendar.MONTH) + 1, dateWhenScheduling.get(Calendar.DATE));
                for (int dayToSelect = dateWhenScheduling.getActualMinimum(Calendar.DAY_OF_MONTH); dayToSelect <= (dateWhenScheduling).getActualMaximum(Calendar.DAY_OF_MONTH); dayToSelect++) {
                    System.out.println(optionNumber + " - " + dayToSelect + "/" + (dateWhenScheduling.get(Calendar.MONTH) + 1));
                    optionNumber++;
                }
                System.out.println(optionNumber + " - Previous Month");
                System.out.printf("%nType your option: ");
                daySelected = Utils.insertInt("Insert a valid option: ");
                System.out.println();
                check = daySelected != optionNumber;

                Calendar selectedDateCalendar = Calendar.getInstance();
                selectedDateCalendar.set(dateWhenScheduling.get(Calendar.YEAR), dateWhenScheduling.get(Calendar.MONTH), daySelected);
                selectedDate = selectedDateCalendar.getTime();
                System.out.println(selectedDate);

            } else {
                System.out.println();
                Calendar selectedDateCalendar = Calendar.getInstance();
                selectedDateCalendar.set(dateWhenScheduling.get(Calendar.YEAR), dateWhenScheduling.get(Calendar.MONTH), dateWhenScheduling.get(Calendar.DATE) + daySelected);
                selectedDate = selectedDateCalendar.getTime();
                System.out.println(selectedDate);
                check = true;
            }
        } while (!check);
*/




        return null;
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

    private VaccineType selectVaccineTypeHealthCareCenterUI(HealthcareCenter healthcareCenter) {
        System.out.printf("%nSelect one Vaccine Type: %n");
        int optionNumber = 1;
        for (VaccineType vacCenter : healthcareCenter.getVaccineTypes()) {
            System.out.println(optionNumber + " - " + vacCenter);
            optionNumber++;
        }
        boolean check;
        do {
            System.out.println();
            System.out.print("Insert your option: ");
            int option = Utils.insertInt("Insert a valid option: ");

            if ((option >= 0) && (option < c.getVaccinationCenters().size() + 1)) {
                return c.getVaccineTypes().get(option - 1);
            } else {
                System.out.println("Invalid option.");
                check = false;
            }
        } while (!check);

        return null;
    }

    private VaccinationCenter selectVaccinationCenterUI() {

        System.out.println("Select one Vaccination Center: ");
        System.out.println();
        int optionNumber = 1;
        for (VaccinationCenter vacCenter : c.getVaccinationCenters()) {
            System.out.println(optionNumber + " - " + vacCenter);
            optionNumber++;
        }
        boolean check;
        do {
            System.out.println();
            System.out.print("Insert your option: ");
            int option = Utils.insertInt("Insert a valid option: ");

            if ((option >= 0) && (option < c.getVaccinationCenters().size() + 1)) {
                return c.getVaccinationCenters().get(option - 1);
            } else {
                System.out.println("Invalid option.");
                check = false;
            }
        } while (!check);

        return null;
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
                                check = true;
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
}
