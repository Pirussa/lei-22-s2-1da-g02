package app.domain.model;
/**
 * Creates an SNS User
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */

import app.ui.console.utils.Utils;

import app.controller.App;
import pt.isep.lei.esoft.auth.AuthFacade;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Sns user.
 */
public class SnsUser {
    private final String strName;
    private final String strSex;
    private final String strBirthDate;
    private final String strAddress;
    private final String strPhoneNumber;
    private final String strEmail;
    private final int snsUserNumber;
    private final String strCitizenCardNumber;
    private final String strPassword;
    private List<TakenVaccine> takenVaccines = new ArrayList<>();
    private static final int MAX_NUMBER_OF_CHARS_SNS_USER_NUMBER = 9;

    private AuthFacade auth = new AuthFacade();

    /**
     * Instantiates a new Sns user.
     *
     * @param strName              the str name
     * @param strSex               the str sex
     * @param strBirthDate         the str birthdate
     * @param strAddress           the str address
     * @param strPhoneNumber       the str phone number
     * @param strEmail             the str email
     * @param snsUserNumber        the sns user number
     * @param strCitizenCardNumber the str citizen card number
     * @param strPassword          the str password
     */
    public SnsUser(String strName, String strSex, String strBirthDate, String strAddress, String strPhoneNumber,
                   String strEmail, int snsUserNumber, String strCitizenCardNumber, String strPassword) {

        this.strName = strName;
        this.strSex = strSex;
        this.strBirthDate = strBirthDate;
        this.strAddress = strAddress;
        this.strPhoneNumber = strPhoneNumber;
        this.strEmail = strEmail;
        this.snsUserNumber = snsUserNumber;
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

    /**
     * Gets str name.
     *
     * @return the str name
     */
    public String getStrName() {
        return strName;
    }

    /**
     * Gets str sex.
     *
     * @return the str sex
     */
    public String getStrSex() {
        return strSex;
    }

    /**
     * Gets str address.
     *
     * @return the str address
     */
    public String getStrAddress() {
        return strAddress;
    }

    /**
     * Gets str password.
     *
     * @return the str password
     */
    public String getStrPassword() {
        return strPassword;
    }

    /**
     * Gets sns user number.
     *
     * @return the sns user number
     */
    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * Gets user email.
     *
     * @return the email
     */
    public String getStrEmail() {
        return strEmail;
    }

    /**
     * Gets phone number.
     *
     * @return the phone number
     */
    public String getStrPhoneNumber() {
        return strPhoneNumber;
    }

    /**
     * Gets citizen card number.
     *
     * @return the citizen card number
     */
    public String getStrCitizenCardNumber() {
        return strCitizenCardNumber;
    }

    /**
     * Gets user birthdate.
     *
     * @return the user birthdate
     */
    public String getStrBirthDate() {
        return strBirthDate;
    }

    /**
     * Gets taken vaccines.
     *
     * @return the taken vaccines list
     */
    public List<TakenVaccine> getTakenVaccines() {
        return takenVaccines;
    }

    /**
     * Sets taken vaccines list.
     *
     * @param takenVaccines the taken vaccines
     */
    public void setTakenVaccines(List<TakenVaccine> takenVaccines) {
        this.takenVaccines = takenVaccines;
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

    /**
     * Validate address boolean.
     *
     * @param strAddress the str address
     * @return the boolean
     */
    public static boolean validateAddress(String strAddress) {
        String[] splitAddress = strAddress.split("#");
        if (splitAddress.length != 3)
            return false;

        String zipCode = splitAddress[1].trim();
        return zipCode.length() == 8 && zipCode.charAt(4) == '-';
    }

    /**
     * Validate sex boolean.
     *
     * @param strSex the str sex
     * @return the boolean
     */
    public boolean validateSex(String strSex) {
        return strSex.equals("Male") || strSex.equals("Female") || strSex.equals("NA") || strSex.isEmpty();
    }

    /**
     * Validate birthdate boolean.
     *
     * @param strBirthDate the str birthdate
     * @return the boolean
     */
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

    /**
     * Validate sns user.
     *
     * @return true if the SNS User is valid.
     */
    public boolean validateSNSUser() {
        return strName != null && strEmail != null && strPassword != null && strBirthDate != null &&
                strPhoneNumber != null && strAddress != null && strCitizenCardNumber != null && !strName.isEmpty() && !strEmail.isEmpty() &&
                !strPassword.isEmpty() && !strBirthDate.isEmpty() && !strPhoneNumber.isEmpty() &&
                !strAddress.isEmpty() && !strCitizenCardNumber.isEmpty() && Utils.validateEmail(strEmail) &&
                Utils.validateSnsUserNumber(snsUserNumber) && validateSex(strSex) && validateAddress(strAddress) &&
                Utils.validateCitizenCardNumber(strCitizenCardNumber) && Utils.validatePhoneNumber(strPhoneNumber) && validateBirthDate(strBirthDate);
    }

    /**
     * To String for an SNS User (contains user´s information)
     *
     * @return String that contains all the information about a user
     */
    @Override
    public String toString() {
        return "Name: " + strName + '\n' +
                "Sex: " + strSex + '\n' +
                "Birth Date: " + strBirthDate + '\n' +
                "Address: " + strAddress + '\n' +
                "Phone Number: " + strPhoneNumber + '\n' +
                "Email: " + strEmail + '\n' +
                "SNS User Number: " + snsUserNumber + '\n' +
                "Citizen Card Number: " + strCitizenCardNumber + '\n' +
                "Password: " + strPassword;
    }

    /**
     * Register vaccine.
     *
     * @param takenVaccine the taken vaccine
     */
    public void registerVaccine(TakenVaccine takenVaccine) {
        takenVaccines.add(takenVaccine);
    }
}