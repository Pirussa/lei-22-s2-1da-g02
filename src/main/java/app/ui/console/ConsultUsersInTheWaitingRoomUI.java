package app.ui.console;

import app.controller.ConsultUsersInTheWaitingRoomController;
import app.ui.console.utils.Utils;

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
        Utils.selectVaccinationCenterUI();
    }

}
