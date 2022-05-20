package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import java.util.List;
import java.util.Objects;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {
    private final VaccinationCenter vaccinationCenterReceptionist;

    public RegisterTheArrivalOfASNSUserUI(VaccinationCenter vaccinationCenterReceptionist) {
        this.vaccinationCenterReceptionist = vaccinationCenterReceptionist;
    }

    private RegisterTheArrivalOfASNSUserController ctrl = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        System.out.printf("%n------Register the Arrival of an SNS user------%n");

        vaccinationCenterReceptionist.cleanArrivalsList();
        List<ScheduledVaccine> scheduleVaccinesOfTheVaccinationCenter = ctrl.getScheduledVaccineList(vaccinationCenterReceptionist);


        int snsNumber;
        do {
            snsNumber = Utils.readIntegerFromConsole("Introduce SNS Number: ");
        } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.getUserIndexInUsersList(snsNumber) < 0);

        System.out.println();

        VaccinationCenter vaccinationCenterSNSUser = Utils.selectVaccinationCenterUI();


        if(checkRequirementsForRegistration(snsNumber, scheduleVaccinesOfTheVaccinationCenter, vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {

            System.out.println("1. Yes");
            System.out.println("2. No");
            if(Utils.readIntegerFromConsole("Insert your option: ") == 1) {
                ScheduledVaccine appointment = ctrl.getUserAppointment(snsNumber, scheduleVaccinesOfTheVaccinationCenter);
                ctrl.registerArrival(new Arrival(snsNumber, appointment.getVaccineType()), vaccinationCenterSNSUser);
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
     * @param vaccineAppointments The list of appointments of the chosen vaccination center
     * @param vaccinationCenterReceptionist The vaccination center where the receptionist is located
     * @param vaccinationCenterSNSUser The vaccination center where the user is located
     * @return return true if all the requirements are met
     */
    public boolean checkRequirementsForRegistration(int snsNumber, List<ScheduledVaccine> vaccineAppointments, VaccinationCenter vaccinationCenterReceptionist, VaccinationCenter vaccinationCenterSNSUser) {
        ScheduledVaccine appointment = ctrl.getUserAppointment(snsNumber, vaccineAppointments);

        if (appointment == null) {
            System.out.printf("%nThe user does not have any appointment%n");
            return false;
        }

        Arrival arrival = new Arrival(snsNumber, appointment.getVaccineType());

        if(!ctrl.checkDateAndTime(appointment.getDate(), arrival.getDateTime(), arrival.getDateTime().getHour())) {
            System.out.println("Wrong Day/Time%n");
            return false;
        }


        if (!ctrl.checkVaccinationCenters(vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            System.out.printf("%nWrong Vaccination Center%n");
            return false;
        }

        if(!ctrl.checkRegistration(snsNumber, vaccinationCenterReceptionist)) {
            System.out.printf("%nUser has already been registered%n");
            return false;
        }

        System.out.printf("%nThe user meets all the requirements to be registered. Do you confirm this arrival?%n%n");
        return true;
    }
}
