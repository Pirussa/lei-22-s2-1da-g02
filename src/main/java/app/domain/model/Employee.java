package app.domain.model;

import app.controller.RegisterNewEmployeeController;
import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.VaccineAndAdminProcessDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Employee {

    private static final int PASSWORD_LENGTH = 7;
    private static final int ID_LENGTH = 5;

    private static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;

    private static final int STARTING_NUMBER_PORTUGUESE_PHONE = 9;

    private static final int NUMBER_OF_CITIZEN_CARD_DIGITS = 12;

    private static final int FIRST_SECOND_DIGIT_CC = 10;

    private static final int SECOND_SECOND_DIGIT_CC = 8;

    private static final int THIRD_SECOND_DIGIT_CC = 6;

    private static final int FOURTH_SECOND_DIGIT_CC = 4;

    private static final int FIFTH_SECOND_DIGIT_CC = 2;

    private static final int LAST_SECOND_DIGIT_CC = 0;
    private String role;
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String citizenCardNumber;
    private String password;

    public Employee(String id, String name, String address, String phoneNumber, String email, String citizenCardNumber, String password) {

        if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || name == null || address == null || email == null || phoneNumber == null)
            throw new IllegalArgumentException("Arguments can´t be null or empty");

        if (validateCitizenCardNumber(citizenCardNumber) && validatePhoneNumber(phoneNumber) && validateEmail(email)) {
            this.id = id;
            this.name = name;
            this.address = address;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.citizenCardNumber = citizenCardNumber;
            this.password = password;
        }
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
            case "Center Coordinator":
                orderedID = new StringBuilder("CC-" + orderedID);
                break;
            case "Receptionist":
                orderedID = new StringBuilder("RC-" + orderedID);
                break;
            case "Nurse":
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

    public boolean validatePhoneNumber(String phoneNumber) {
        final String portugueseSecondDigit = "1236";

        if (phoneNumber.length() != NUMBER_OF_PHONE_NUMBER_DIGITS) {
            throw new IllegalArgumentException("Invalid Phone Number, verify the number of digits or the starting digit");
        }
        char ch1 = (phoneNumber.charAt(0));
        char ch2 = phoneNumber.charAt(1);
        if (!(ch1 == STARTING_NUMBER_PORTUGUESE_PHONE || ch2 == portugueseSecondDigit.charAt(0) || ch2 == portugueseSecondDigit.charAt(1) || ch2 == portugueseSecondDigit.charAt(2) || ch2 == portugueseSecondDigit.charAt(3))) {
            throw new IllegalArgumentException("Invalid Phone Number, verify the second digit");
        }
        return true;
    }

    public boolean validateCitizenCardNumber(String citizenCardNumber) {
        int sum = 0;
        if (citizenCardNumber.length() != NUMBER_OF_CITIZEN_CARD_DIGITS)
            throw new IllegalArgumentException("Invalid length for Citizen Card Number.");

        boolean secondDigit = true;

        for (int digit = 0; digit < citizenCardNumber.length(); digit++) {
            String toUpperCase = String.valueOf(citizenCardNumber.charAt(digit)).toUpperCase();
            int value = getValueFromCitizenCardNumberDigit(toUpperCase);

            if (secondDigit) {
                value *= 2;

                if (value >= 10)
                    value -= 9;
            }
            sum += value;
            secondDigit = !secondDigit;
        }
        return (sum % FIRST_SECOND_DIGIT_CC) == 0;
    }

    public int getValueFromCitizenCardNumberDigit(String letter) {
        switch (letter) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
            case "G":
                return 16;
            case "H":
                return 17;
            case "I":
                return 18;
            case "J":
                return 19;
            case "K":
                return 20;
            case "L":
                return 21;
            case "M":
                return 22;
            case "N":
                return 23;
            case "O":
                return 24;
            case "P":
                return 25;
            case "Q":
                return 26;
            case "R":
                return 27;
            case "S":
                return 28;
            case "T":
                return 29;
            case "U":
                return 30;
            case "V":
                return 31;
            case "W":
                return 32;
            case "X":
                return 33;
            case "Y":
                return 34;
            case "Z":
                return 35;
        }
        throw new IllegalArgumentException("Invalid Value in the Document.");
    }

    public boolean validateEmail(String email) {
        if (!email.contains("@"))
            throw new IllegalArgumentException("Email must contain - @");

        String[] emailSplitter = email.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt"};

        for (int position = 0; position < validEmailDomain.length; position++) {
            if (Objects.equals(emailSplitter[1], validEmailDomain[position]))
                return true;
        }
        return false;
    }
}