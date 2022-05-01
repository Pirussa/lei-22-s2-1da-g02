package app.domain.model;

import app.controller.RegisterNewEmployeeController;
import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Employee {

    private static final int PASSWORD_LENGTH = 7;

    private static final int ID_LENGTH = 5;

    private static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;

    private String role;
    private String id;

    private String name;

    private String address;

    private int phoneNumber;

    private String email;

    private int citizenCardNumber;

    private String password;

    public Employee(String role, String id, String name, String address, int phoneNumber, String email, int citizenCardNumber, String password) {

        if (role.isEmpty() || id.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException("Arguments can´t be null or empty");

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    RegisterNewEmployeeController ctrl = new RegisterNewEmployeeController();

    public static String passwordGenerator() {
        final String alphabetLetters = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789";
        StringBuilder password = new StringBuilder();
        Random generate = new Random();
        ArrayList<String> randomAlphanumerics = new ArrayList<>();
        StringBuilder employeePassword = new StringBuilder();

        for (int position = 0; position < PASSWORD_LENGTH; position++) {
            if (position <= 2)
                password.append(Character.toUpperCase(alphabetLetters.charAt(generate.nextInt(25))));
            else if (position <= 4)
                password.append(String.valueOf(generate.nextInt(9)));
            else
                password.append(alphabetLetters.charAt(generate.nextInt(alphabetLetters.length())));
        }

        for (int position = 0; position < PASSWORD_LENGTH; position++) {
            int index = (generate.nextInt(password.length()));
            char passwordAux = password.charAt(index);
            employeePassword.append(passwordAux);
            password.deleteCharAt(index);
        }
        return String.valueOf(employeePassword);
    }

    public static StringBuilder idGenerator(String role) {
        StringBuilder orderedID = new StringBuilder();
        Random generate = new Random();

        for (int position = 0; position < ID_LENGTH; position++) {
            orderedID.append(String.valueOf(generate.nextInt(9)));
        }

        switch (role) {
            case Constants.ROLE_CENTRE_COORDINATOR:
                orderedID = new StringBuilder("CC-" + orderedID);
                break;
            case Constants.ROLE_RECEPTIONIST:
                orderedID = new StringBuilder("RC-" + orderedID);
                break;
            case Constants.ROLE_NURSE:
                orderedID = new StringBuilder("NR-" + orderedID);
                break;
        }

        String auxID = String.valueOf(orderedID);
        String[] splitID = auxID.split("-");

        if (!orderedID.isEmpty() && orderedID.length() == 8 && splitID[1].length() == 5) {
            return orderedID;
        } else {
            orderedID = new StringBuilder("");
            return orderedID;
        }
    }
}

    /*public boolean validateEmployeeData() {
        if (phoneNumber) {
            int aux = phoneNumber;
            for (int count = 0; aux != 0; aux /= 10, count++) {
                if (count < NUMBER_OF_PHONE_NUMBER_DIGITS)
                    return false;
            }
        }
        return true;
    }
}*/
