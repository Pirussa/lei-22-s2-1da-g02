package app.domain.model;

import java.util.ArrayList;

public class Nurse extends Employee {
    public Nurse(String id, String name, String address, String phoneNumber, String email, String citizenCardNumber, String password) {
        super(id, name, address, phoneNumber, email, citizenCardNumber, password);
    }

    @Override
    public String toString() {
        return "Name: " + super.getName() +
                " | ID: " + super.getId() +
                " | Address: " + super.getAddress() +
                " | Phone Number: " + super.getPhoneNumber() +
                " | Email: " + super.getEmail() +
                " | Citizen Card Number: " + super.getCitizenCardNumber();
    }

//    public ArrayList<SNSUser> listOfUsersInTheWaitingRoom(VaccinationCenter vaccinationCenter){
//        ArrayList<SNSUser> listOfUsersInTheWaitingRoom = new ArrayList<>();
//        for (int arrivalListPosition = 0; arrivalListPosition < getVaccinationCenter(vaccinationCenter).size(); arrivalListPosition++) {
//            for (int snsUserListPosition = 0; snsUserListPosition < company.getSNSUserList().size(); snsUserListPosition++) {
//                if(getVaccinationCenter(vaccinationCenter).get(arrivalListPosition).getSnsNumber() == company.getSNSUserList().get(snsUserListPosition).getSnsUserNumber())
//                    listOfUsersInTheWaitingRoom.add(company.getSNSUserList().get(snsUserListPosition));
//            }
//        }
//        return listOfUsersInTheWaitingRoom;
//    }
}
