package app.ui.console;

import app.controller.ConsultUsersInTheWaitingRoomController;
import app.domain.model.Arrival;
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

    @Override
    public void run() {
        System.out.printf("%n------ Consult the sns users in the waiting room of a vaccination center ------%n");

        VaccinationCenter vaccinationCenter = ctrl.getVaccinationCenter();
        List<Arrival> listOfUsersThatArrivedInAVaccinationCentre = ctrl.getVaccinationCenter(vaccinationCenter);

        if (!listOfUsersThatArrivedInAVaccinationCentre.isEmpty())
            for (Arrival arrival : listOfUsersThatArrivedInAVaccinationCentre)
                System.out.println(arrival);
        else
            System.out.println("No user has arrived yet.");

    }

}
