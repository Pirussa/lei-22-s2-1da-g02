package app.ui.console;

import app.controller.ConsultUsersInTheWaitingRoomController;
import app.ui.console.utils.Utils;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Center.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomUI implements Runnable {


    private ConsultUsersInTheWaitingRoomController ctrl = new ConsultUsersInTheWaitingRoomController();


    /**
     * Initializes the user interface.
     */

    @Override
    public void run() {
        System.out.printf("%n------ Consult the sns users in the waiting room of a vaccination center ------%n");

        int index = Utils.selectVaccinationCenterIndex();
        ctrl.setVaccinationCenter(index);

        if (!ctrl.listOfUsersInTheWaitingRoom().isEmpty()) {
            System.out.printf("%n------You've chosen to get the list of SNS Users in the waiting room:------%n");
            for (int listPosition = 0; listPosition < ctrl.listOfUsersInTheWaitingRoom().size(); listPosition++) {
                System.out.printf("%n" + ctrl.listOfUsersInTheWaitingRoom().get(listPosition));
            }
        } else
            System.out.printf("%nNo user has arrived yet.%n");
    }
}
