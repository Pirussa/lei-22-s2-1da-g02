package app.domain.model;

public class Receptionist extends Employee {
    public Receptionist(String id, String name, String address, String phoneNumber, String email, String citizenCardNumber, String password) {
        super(id, name, address, phoneNumber, email, citizenCardNumber, password);
    }

    /*
    public Receptionist(String role, int id, String name, String address, int phoneNumber, String email, int citizenCardNumber, String password) {
        super(role, id, name, address, phoneNumber, email, citizenCardNumber, password);
    }*/

    @Override
    public String toString() {
        return "Employee: " + super.getName() + '\'' +
                ", id= " + super.getId() + '\'' +
                ", address= " + super.getAddress() + '\'' +
                ", phoneNumber= " + super.getPhoneNumber() + '\'' +
                ", email= " + super.getEmail() + '\'' +
                ", citizenCardNumber= " + super.getCitizenCardNumber() + '\'';
    }


}
