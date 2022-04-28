package app.domain.model;

import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;

public class Employee {

    private final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;

    private String role;
    private int id;

    private String name;

    private String address;

    private int phoneNumber;

    private String email;

    private int citizenCardNumber;

    private String password;

    public Employee(String role, int id, String name, String address, int phoneNumber, String email, int citizenCardNumber,String password) {
        this.role = role;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean validateEmployeeData() {
        if (phoneNumber != 0) {
            int aux = phoneNumber;
            for (int count = 0; aux != 0; aux/= 10, count++) {
                if (count < NUMBER_OF_PHONE_NUMBER_DIGITS)
                    return false;
            }
        }
        return true;
    }
}
