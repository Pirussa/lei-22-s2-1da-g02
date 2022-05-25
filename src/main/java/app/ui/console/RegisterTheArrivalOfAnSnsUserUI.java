package app.ui.console;

import app.controller.RegisterTheArrivalOfAnSnsUserController;
import app.domain.model.SnsUser;
import app.ui.console.utils.Utils;

public class RegisterTheArrivalOfAnSnsUserUI implements Runnable {


    private RegisterTheArrivalOfAnSnsUserController ctrl = new RegisterTheArrivalOfAnSnsUserController();

    @Override
    public void run() {
        System.out.printf("%n------Register the Arrival of an SNS user------%n");

        int vaccinationCenterReceptionist  = Utils.selectVaccinationCenterIndex();
        ctrl.setVaccinationCenter(vaccinationCenterReceptionist);
        ctrl.cleanArrivalsList();


        int snsNumber;
        do {
            snsNumber = Utils.readIntegerFromConsole("Introduce SNS Number: ");
        } while (!SnsUser.validateSNSUserNumber(snsNumber) || SnsUser.getUserIndexInUsersList(snsNumber) < 0);


        if(checkRequirementsForRegistration(snsNumber)) {

            System.out.printf("%nThe user meets all the requirements to be registered. Do you confirm this arrival?%n%n");
            System.out.println("1. Yes");
            System.out.println("2. No");
            if(Utils.readIntegerFromConsole("Insert your option: ") == 1) {
                ctrl.registerArrival();
                System.out.printf("%nThe user has been registered%n");
            }
            else
                System.out.printf("%nThe user has not been registered %n");

        }

    }

    /**
     * Checks all the requirements needed in order to register an arrival
     *
     * @param snsNumber The number that identifies an SNS user
     * @return boolean - true if all the requirements are met
     */
    public boolean checkRequirementsForRegistration(int snsNumber) {

        if (!ctrl.checkAndSetUserAppointment(snsNumber)) {
            System.out.printf("%nThe user does not have any appointment%n");
            return false;
        }
        else
            ctrl.setArrival(snsNumber);

        if(!ctrl.validateDateAndTime()) {
            System.out.printf("%nWrong Day/Time %n");
            return false;
        }

        if(!ctrl.checkIfAlreadyRegistered(snsNumber)) {
            System.out.printf("%nUser has already been registered %n");
            return false;
        }

        return true;
    }
}
