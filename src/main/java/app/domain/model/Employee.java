package app.domain.model;

import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Random;

public class Employee {

    private static final int PASSWORD_LENGTH = 7;

    private static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;

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

    /*public static StringBuilder idGenerator(String role) {
        int auxNurse = 0;
        String orderedId = "";

        switch (role) {
            case Constants.ROLE_CENTRE_COORDINATOR:
                orderedId = "CC-" + String.valueOf(aux);
                aux++;
        }
    } */
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
