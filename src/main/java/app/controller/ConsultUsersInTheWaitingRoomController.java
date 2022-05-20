package app.controller;

import app.domain.model.Arrival;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Centre.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomController {

    public ConsultUsersInTheWaitingRoomController() {
    }


    public VaccinationCenter getVaccinationCenter() {
        return Utils.selectVaccinationCenterUI();
    }

    public List<Arrival> getVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return vaccinationCenter.getArrivalsList();
    }



}



