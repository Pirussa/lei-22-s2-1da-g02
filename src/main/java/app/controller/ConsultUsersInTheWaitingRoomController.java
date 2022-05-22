package app.controller;

import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Centre.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomController {

    private Company company = App.getInstance().getCompany();

    public ConsultUsersInTheWaitingRoomController() {
    }


    public VaccinationCenter getVaccinationCenter() {
        return Utils.selectVaccinationCenterUI();
    }

    public List<Arrival> getVaccinationCenter(VaccinationCenter vaccinationCenter) {
        return vaccinationCenter.getArrivalsList();
    }

    /**
     * Searches in the SNS Users' list for the users from the arrivals list, using the sns user number, and adds them to the waiting room list.
     * @param vaccinationCenter is the selected vaccination center.
     * @return the list of users in the waiting room.
     */

    public ArrayList<SNSUser> listOfUsersInTheWaitingRoom(VaccinationCenter vaccinationCenter){
        ArrayList<SNSUser> listOfUsersInTheWaitingRoom = new ArrayList<>();
        for (int arrivalListPosition = 0; arrivalListPosition < getVaccinationCenter(vaccinationCenter).size(); arrivalListPosition++) {
            for (int snsUserListPosition = 0; snsUserListPosition < company.getSNSUserList().size(); snsUserListPosition++) {
                if(getVaccinationCenter(vaccinationCenter).get(arrivalListPosition).getSnsNumber() == company.getSNSUserList().get(snsUserListPosition).getSnsUserNumber())
                    listOfUsersInTheWaitingRoom.add(company.getSNSUserList().get(snsUserListPosition));
            }
        }
        return listOfUsersInTheWaitingRoom;
    }



}



