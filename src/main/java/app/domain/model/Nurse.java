package app.domain.model;

public class Nurse extends Employee {
    public Nurse(String role, String id, String name, String address, String phoneNumber, String email, String citizenCardNumber, String password) {
        super(role, id, name, address, phoneNumber, email, citizenCardNumber, password);
    }


    /*
    public Nurse(String role, int id, String name, String address, int phoneNumber, String email, int citizenCardNumber, String password) {
        super(role, id, name, address, phoneNumber, email, citizenCardNumber, password);
    }*/

}
