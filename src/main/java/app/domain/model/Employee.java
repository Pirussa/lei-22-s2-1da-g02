package app.domain.model;

import app.controller.RegisterNewEmployeeController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Employee {

    /**
     * Represents a new Employee of the vaccination process.
     *
     * @author Guilherme Sousa <1211073@isep.ipp.pt>
     */

    private String id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String citizenCardNumber;
    private String password;

    /**
     * Creates an Employee with the following attributes:
     *
     * @param id                The Employee´s id.
     * @param name              The Employee´s name.
     * @param address           The Employee´s address.
     * @param phoneNumber       The Employee´s phone number.
     * @param email             The Employee´s email.
     * @param citizenCardNumber The Employee´s citizen card number.
     * @param password          The Employee´s password.
     */

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

    /**
     * Generates a new password for each created Employee.
     *
     * @return new Employee generated password
     */

    public static String passwordGenerator() {
        final String alphabetLetters = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789";
        StringBuilder password = new StringBuilder();
        Random generate = new Random();
        ArrayList<String> randomAlphanumerics = new ArrayList<>();
        StringBuilder employeePassword = new StringBuilder();

        for (int position = 0; position < Constants.PASSWORD_LENGTH; position++) {
            if (position <= 2)
                password.append(Character.toUpperCase(alphabetLetters.charAt(generate.nextInt(25))));
            else if (position <= 4)
                password.append(String.valueOf(generate.nextInt(9)));
            else
                password.append(alphabetLetters.charAt(generate.nextInt(alphabetLetters.length())));
        }

        for (int position = 0; position < Constants.PASSWORD_LENGTH; position++) {
            int index = (generate.nextInt(password.length()));
            char passwordAux = password.charAt(index);
            employeePassword.append(passwordAux);
            password.deleteCharAt(index);
        }
        return String.valueOf(employeePassword);
    }

    /**
     * Validate new Employee data
     *
     * @return true if all the validations are also true
     */

    public boolean validateEmployee() {
        return name != null && address != null && email != null && phoneNumber != null && citizenCardNumber != null && !name.isEmpty() && !address.isEmpty() && !email.isEmpty() && !phoneNumber.isEmpty() && !citizenCardNumber.isEmpty() && Utils.validateEmail(email) && Utils.validateCitizenCardNumber(citizenCardNumber) && Utils.validateAddress(address) && Utils.validatePhoneNumber(phoneNumber);
    }
}