package app.domain.model;

import app.controller.RegisterNewEmployeeController;
import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.VaccineAndAdminProcessDto;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
    private static final int PASSWORD_LENGTH = 7;
    private static final int ID_LENGTH = 5;
    private static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;
    private static final int STARTING_NUMBER_PORTUGUESE_PHONE = 9;

    private static final int FIRST_SECOND_NUMBER_PORTUGUESE_PHONE = 1;

    private static final int SECOND_SECOND_NUMBER_PORTUGUESE_PHONE = 2;

    private static final int THIRD_SECOND_NUMBER_PORTUGUESE_PHONE = 3;

    private static final int FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE = 6;
    private static final int NUMBER_OF_CITIZEN_CARD_DIGITS = 12;
    private static final int FIRST_SECOND_DIGIT_CC = 10;
    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String citizenCardNumber;
    private String password;

    public Employee(String id, String name, String address, String phoneNumber, String email, String citizenCardNumber, String password) {

        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.password = password;

    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCitizenCardNumber() {
        return citizenCardNumber;
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
        return orderedID;
    }

    public boolean validateEmployee() {
        return !name.isEmpty() && !address.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !citizenCardNumber.isEmpty() && validateEmail(email) && validateCitizenCardNumber(citizenCardNumber) && validateAddress(address) && validatePhoneNumber(phoneNumber);
    }

    public boolean validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.length() == NUMBER_OF_PHONE_NUMBER_DIGITS && Integer.parseInt(phoneNumber) % 1 == 0) {
            int ch1 = Integer.parseInt(String.valueOf(phoneNumber.charAt(0)));
            if (ch1 != STARTING_NUMBER_PORTUGUESE_PHONE)
                return false;

            int ch2 = Integer.parseInt(String.valueOf(phoneNumber.charAt(1)));
            if (ch2 != FIRST_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != SECOND_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != THIRD_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean validateCitizenCardNumber(String citizenCardNumber) {
        String noBlankSpotsCitizenCardNumber = citizenCardNumber.replaceAll("\\s", "");
        int sum = 0;
        if (noBlankSpotsCitizenCardNumber.length() != NUMBER_OF_CITIZEN_CARD_DIGITS)
            return false;

        boolean secondDigit = true;

        for (int digit = 0; digit < noBlankSpotsCitizenCardNumber.length(); digit++) {
            String toUpperCase = String.valueOf(noBlankSpotsCitizenCardNumber.charAt(digit)).toUpperCase();
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
        if (!email.contains("@") && !email.contains("."))
            return false;

        String[] emailSplitter = email.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt", "outlook.com"};

        for (int position = 0; position < validEmailDomain.length; position++) {
            if (Objects.equals(emailSplitter[1], validEmailDomain[position]))
                return true;
        }
        return false;
    }

    public boolean validateAddress(String address) {
        String[] splitAddress = address.split(",");
        if (splitAddress.length != 3)
            return false;

        String zipCode = splitAddress[1].trim();
        if (zipCode.length() != 8)
            return false;
        return true;
    }
}