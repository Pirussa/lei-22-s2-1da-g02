package app.ui.console;

import app.controller.ConsultUsersInTheWaitingRoomController;
import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;

import java.util.List;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Centre.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomUI implements Runnable {

    public ConsultUsersInTheWaitingRoomUI() {
    }

    private ConsultUsersInTheWaitingRoomController ctrl = new ConsultUsersInTheWaitingRoomController();


    /**
     * Initializes the user interface.
     */

    @Override
    public void run() {
        System.out.printf("%n------ Consult the sns users in the waiting room of a vaccination center ------%n");

        VaccinationCenter vaccinationCenter = ctrl.getVaccinationCenter();

        if (!ctrl.listOfUsersInTheWaitingRoom(vaccinationCenter).isEmpty())
            for (SNSUser snsUser : ctrl.listOfUsersInTheWaitingRoom(vaccinationCenter))
                System.out.printf(snsUser + "%n");
        else
            System.out.println("No user has arrived yet.");
    }

}
