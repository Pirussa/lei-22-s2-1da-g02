package app.controller;

import app.domain.model.*;
import dto.VaccinationCenterDto;
import mapper.VaccinationCenterMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * US005 - Consult the users in the waiting room of a Vaccination Center.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class ConsultUsersInTheWaitingRoomController {

    private VaccinationCenter vaccinationCenter;
    private Company company = App.getInstance().getCompany();

    public ConsultUsersInTheWaitingRoomController() {
    }

    /**
     * Gets the vaccination center according to the selected index.
     *
     * @param index is the option selected by the nurse containing the vaccination center they work at.
     */

    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index);
    }

    /**
     * Gets the Arrivals List.
     *
     * @return a list of Users that arrived in the selected vaccination center.
     */

    public List<Arrival> getArrivalsList() {
        return vaccinationCenter.getArrivalsList();
    }


    public VaccinationCenterDto getVaccinationCenterInfo() {
        VaccinationCenterMapper vaccinationCenterMapper = new VaccinationCenterMapper();
        return vaccinationCenterMapper.domainToDto(vaccinationCenter);
    }

    /**
     * Searches in the SNS Users' list for the users from the arrivals list, using the sns user number, and adds them to the waiting room list.
     *
     * @return the list of users in the waiting room.
     */

    public ArrayList<String> listOfUsersInTheWaitingRoom() {
        ArrayList<String> listOfUsersInTheWaitingRoom = new ArrayList<>();
        String snsUserInfo = "";
        for (int arrivalListPosition = 0; arrivalListPosition < getArrivalsList().size(); arrivalListPosition++) {
            for (int snsUserListPosition = 0; snsUserListPosition < company.getSNSUserList().size(); snsUserListPosition++) {
                if (getArrivalsList().get(arrivalListPosition).getSnsNumber() == company.getSNSUserList().get(snsUserListPosition).getSnsUserNumber())
                    snsUserInfo = "Name: " + company.getSNSUserList().get(snsUserListPosition).getStrName() + '\n' +
                            "Sex: " + company.getSNSUserList().get(snsUserListPosition).getStrSex() + '\n' +
                            "Birth Date: " + company.getSNSUserList().get(snsUserListPosition).getStrBirthDate() + '\n' +
                            "SNS User Number: " + company.getSNSUserList().get(snsUserListPosition).getSnsUserNumber() + '\n' +
                            "Phone Number: " + company.getSNSUserList().get(snsUserListPosition).getStrPhoneNumber() + '\n';
                listOfUsersInTheWaitingRoom.add(snsUserInfo);
            }
        }
        return listOfUsersInTheWaitingRoom;
    }
}