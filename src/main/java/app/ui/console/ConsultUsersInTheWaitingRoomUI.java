package app.ui.console;

import app.controller.ConsultUsersInTheWaitingRoomController;
import app.domain.model.Arrival;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.List;
import java.util.Scanner;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Centre.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomUI implements Runnable {

    public ConsultUsersInTheWaitingRoomUI() {
    }

    private ConsultUsersInTheWaitingRoomController ctrl = new ConsultUsersInTheWaitingRoomController();

    @Override
    public void run() {
        System.out.println();
        System.out.println("------ Consult the sns users in the waiting room of a vaccination centre ------");
        System.out.println();

        VaccinationCenter vaccinationCenter  = ctrl.getVaccinationCenter();
        List<Arrival> listOfUsersThatArrivedInAVaccinationCentre = ctrl.getVaccinationCenter(vaccinationCenter);

        if(!listOfUsersThatArrivedInAVaccinationCentre.isEmpty()) {
            for (int listPosition = 0; listPosition < listOfUsersThatArrivedInAVaccinationCentre.size(); listPosition++) {
                System.out.println(listOfUsersThatArrivedInAVaccinationCentre.get(listPosition));
            }
        } else {
            System.out.println("No user has arrived yet.");
        }

    }

}
