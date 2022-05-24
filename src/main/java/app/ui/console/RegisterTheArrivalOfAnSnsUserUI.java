package app.ui.console;

import app.controller.RegisterTheArrivalOfAnSnsUserController;
import app.domain.model.Arrival;
import app.domain.model.SnsUser;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import java.util.List;
import java.util.Objects;

public class RegisterTheArrivalOfAnSnsUserUI implements Runnable {


    private RegisterTheArrivalOfAnSnsUserController ctrl = new RegisterTheArrivalOfAnSnsUserController();

    @Override
    public void run() {
        System.out.printf("%n------Register the Arrival of an SNS user------%n");

        int vaccinationCenterReceptionist  = Utils.selectVaccinationCenterIndex();
        ctrl.setVaccinationCenterReceptionist(vaccinationCenterReceptionist);
        ctrl.cleanArrivalsList();


        int snsNumber;
        do {
            snsNumber = Utils.readIntegerFromConsole("Introduce SNS Number: ");
        } while (!SnsUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SnsUser.getUserIndexInUsersList(snsNumber) < 0);

        System.out.println();

        int vaccinationCenterSNSUser = Utils.selectVaccinationCenterIndex();
        ctrl.setVaccinationCenterSnsUser(vaccinationCenterSNSUser);


        if(checkRequirementsForRegistration(snsNumber)) {

            System.out.printf("%nThe user meets all the requirements to be registered. Do you confirm this arrival?%n%n");
            System.out.println("1. Yes");
            System.out.println("2. No");
            if(Utils.readIntegerFromConsole("Insert your option: ") == 1) {
                ctrl.registerArrival();
                System.out.printf("%nThe user has been registered");
            }
            else
                System.out.printf("%nThe user has not been registered");

        }

    }

    /**
     * Checks all the requirements needed in order to register an arrival
     *
     * @param snsNumber The number that identifies an SNS user
     * @return return true if all the requirements are met
     */
    public boolean checkRequirementsForRegistration(int snsNumber) {

        if (!ctrl.getUserAppointment(snsNumber)) {
            System.out.printf("%nThe user does not have any appointment%n");
            return false;
        }

        if (!ctrl.checkVaccinationCenters()) {
            System.out.printf("%nWrong Vaccination Center%n");
            return false;
        }

        if(!ctrl.checkDateAndTime(snsNumber)) {
            System.out.println("Wrong Day/Time%n");
            return false;
        }

        if(!ctrl.checkRegistration(snsNumber)) {
            System.out.printf("%nUser has already been registered%n");
            return false;
        }

        return true;
    }
}
