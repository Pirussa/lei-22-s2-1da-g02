package app.domain.model;


import app.ui.console.utils.Utils;

import app.controller.App;
import pt.isep.lei.esoft.auth.AuthFacade;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SNSUser {

    private String strName;
    private int snsUserNumber;
    private String strEmail;
    private String strBirthDate;
    private String strPhoneNumber;
    private String strSex;
    private String strAddress;
    private String strCitizenCardNumber;
    private String strPassword;
    private List<TakenVaccine> takenVaccines = new ArrayList<>();
    private static final int MAX_NUMBER_OF_CHARS_SNS_USER_NUMBER = 9;

    private AuthFacade auth = new AuthFacade();

    private static Company company = App.getInstance().getCompany();


    public SNSUser(String strName, int snsUserNumber, String strEmail, String strBirthDate, String strPhoneNumber,
                   String strSex, String strAddress, String strCitizenCardNumber, String strPassword) {

        this.strName = strName;
        this.snsUserNumber = snsUserNumber;
        this.strEmail = strEmail;
        this.strBirthDate = strBirthDate;
        this.strPhoneNumber = strPhoneNumber;
        this.strSex = strSex;
        this.strAddress = strAddress;
        this.strCitizenCardNumber = strCitizenCardNumber;
        this.strPassword = strPassword;

        try {
            if (!validateSNSUser()) {
                throw new IllegalArgumentException("SNS User Info is Invalid");
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public String getStrPhoneNumber() {
        return strPhoneNumber;
    }

    public String getStrCitizenCardNumber() {
        return strCitizenCardNumber;
    }

    public String getStrBirthDate() {
        return strBirthDate;
    }

    public List<TakenVaccine> getTakenVaccines() {
        return takenVaccines;
    }

    public boolean validateEmail(String strEmail) {
        if (!strEmail.contains("@") && !strEmail.contains("."))
            return false;

        String[] emailSplitter = strEmail.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt", "outlook.com"};

        for (String s : validEmailDomain) {
            if (Objects.equals(emailSplitter[1], s))
                return true;
        }
        return false;
    }

    public boolean validatePassword(String strPassword) {
        final int PASSWORDLENGHT = 7;
        return strPassword.length() == PASSWORDLENGHT;
    }

    public static boolean validateSNSUserNumber(int snsUserNumber) {
        return String.valueOf(snsUserNumber).length() == MAX_NUMBER_OF_CHARS_SNS_USER_NUMBER;
    }

    public boolean validatePhoneNumber(String phoneNumber) {
        final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;
        final int STARTING_NUMBER_PORTUGUESE_PHONE = 9;
        final int FIRST_SECOND_NUMBER_PORTUGUESE_PHONE = 1;
        final int SECOND_SECOND_NUMBER_PORTUGUESE_PHONE = 2;
        final int THIRD_SECOND_NUMBER_PORTUGUESE_PHONE = 3;
        final int FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE = 6;

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

    public boolean validateCitizenCardNumber(String strCitizenCardNumber) {
        final int NUMBER_OF_CITIZEN_CARD_DIGITS = 12;
        final int FIRST_SECOND_DIGIT_CC = 10;
        String noBlankSpotsCitizenCardNumber = strCitizenCardNumber.replaceAll("\\s", "");
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

    public static boolean validateAddress(String strAddress) {
        String[] splitAddress = strAddress.split("#");
        if (splitAddress.length != 3)
            return false;

        String zipCode = splitAddress[1].trim();
        if (zipCode.length() != 8 || zipCode.charAt(4) != '-')
            return false;

        return true;
    }

    public boolean validateSex(String strSex) {
        return strSex.equals("Male") || strSex.equals("Female") || strSex.isEmpty();
    }

    public boolean validateBirthDate(String strBirthDate) {
        String dateFormat = "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(strBirthDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public boolean validateSNSUser() {
        return strName != null && strEmail != null && strPassword != null && strBirthDate != null &&
                strPhoneNumber != null && strAddress != null && strCitizenCardNumber != null && !strName.isEmpty() && !strEmail.isEmpty() &&
                !strPassword.isEmpty() && !strBirthDate.isEmpty() && !strPhoneNumber.isEmpty() &&
                !strAddress.isEmpty() && !strCitizenCardNumber.isEmpty() && validateEmail(strEmail) && validatePassword(strPassword) &&
                Utils.validateSNSUserNumber(snsUserNumber) && validateSex(strSex) && validateAddress(strAddress) &&
                validateCitizenCardNumber(strCitizenCardNumber) && validatePhoneNumber(strPhoneNumber) && validateBirthDate(strBirthDate);
    }

    public static int getUserIndexInUsersList(int snsUserNumber) {
        for (int position = 0; position < company.getSNSUserList().size(); position++) {
            if (snsUserNumber == (company.getSNSUserList().get(position).getSnsUserNumber())) {
                return position;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "Name of the SNS User: " + strName + '\n' +
                "SNS User Number of the SNS User: " + snsUserNumber + '\n' +
                "Email of the SNS User: " + strEmail + '\n' +
                "Birth Date of the SNS User: " + strBirthDate + '\n' +
                "Phone Number of the SNS User: " + strPhoneNumber + '\n' +
                "Sex of the SNS User: " + strSex + '\n' +
                "Address of the SNS User: " + strAddress + '\n' +
                "Citizen Card Number of the SNS User: " + strCitizenCardNumber + '\n' +
                "Password of the SNS User: " + strPassword;
    }

    public void registerVaccine(TakenVaccine takenVaccine) {
        takenVaccines.add(takenVaccine);
    }
}