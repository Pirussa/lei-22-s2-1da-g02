package app.ui.console;

import app.controller.App;
import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Scanner;

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
            if (vaccineType == null) {return;}

            System.out.println("Sucesso"); //TEMP

        } else {
            System.out.println();
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
            System.out.println("| There are no Vaccine Types or Vaccination Centers or SNS Users yet. Please add at least one of each first. |");
            System.out.println("|------------------------------------------------------------------------------------------------------------|");
        }


    }

    private VaccineType selectVaccineTypeUI(VaccinationCenter vaccinationCenter) {
        if (vaccinationCenter instanceof MassVaccinationCenter) {
            MassVaccinationCenter massVacCenter = (MassVaccinationCenter) vaccinationCenter;
            System.out.println();
            System.out.println("The available Vaccine Type for " + massVacCenter + " is: " + massVacCenter.getVaccineType());
            System.out.printf("%nDo you want to proceed?%n1 - Yes%n2 - No%n%nType your option: ");
            boolean check = true;

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
