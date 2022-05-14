package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccinationController;
import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.Objects;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationUI implements Runnable {

    ScheduleVaccinationController ctrl = new ScheduleVaccinationController();

    private static Company company = App.getInstance().getCompany();

    public ScheduleVaccinationUI() {
    }

    public void run() {
        System.out.printf("%n----------------------%n|Schedule Vaccination|%n----------------------%n%n");
        String snsNumber;

        do {

            snsNumber = Utils.readLineFromConsole("Introduce SNS Number: ");

        } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.userExists(snsNumber) < 0);

        int vaccinationCenterReceptionist = Utils.showAndSelectIndex(ctrl.getVaccinationCenterList(), "Vaccination Centers");
        VaccinationCenter vaccinationCenter = company.getVaccinationCenters().get(vaccinationCenterReceptionist);
        VaccineType vaccineType = selectVaccineTypeUI(vaccinationCenter);
        VaccinationCenterScheduledVaccinations vaccinationCenterScheduledVaccinations = new VaccinationCenterScheduledVaccinations(vaccinationCenter.getStrName(), vaccinationCenter.getStrOpeningHour(), vaccinationCenter.getStrClosingHour(), VaccinationCenterScheduledVaccinations.createArrayForVaccinationScheduling(vaccinationCenter.getStrOpeningHour(), vaccinationCenter.getStrClosingHour()));
        Utils.printIntegerArray(vaccinationCenterScheduledVaccinations.getScheduledVaccinations());
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

            if ((option >= 0) && (option < company.getVaccinationCenters().size() + 1)) {
                return company.getVaccineTypes().get(option - 1);
            } else {
                System.out.println("Invalid option.");
                check = false;
            }
        } while (!check);

        return null;
    }
    }

    /*
     Como iria funcionar? - O User dirige-se à rececionista e procede ao agendamento da vacina, a rececionista deve ter em conta,
      a data de nascimento e o tempo desde a última vacina que o user tomou.
    */