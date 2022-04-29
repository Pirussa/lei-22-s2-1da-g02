package app.controller;


import app.domain.model.Company;
import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import org.w3c.dom.CharacterData;

import java.util.ArrayList;
import java.util.Random;


/**
 * US010 - Register New Employee Controller
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RegisterNewEmployeeController {

    private final int PASSWORD_LENGTH = 7;

    private Company company = App.getInstance().getCompany();

    public RegisterNewEmployeeController() {
    }

    public ArrayList<String> getRolesList() {
        return company.getRolesList();
    }

    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        if (company.registerNewEmployee(dto)) {
            return true;
        }
        return false;
    }

    public void saveCreatedEmployee(RegisterNewEmployeeDto dto) {
        company.saveCreatedEmployee(dto);
    }

    public String passwordGenerator() {
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
}
