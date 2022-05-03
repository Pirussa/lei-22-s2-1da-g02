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

    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        return company.registerNewEmployee(dto);
    }

    public void saveCreatedEmployee(RegisterNewEmployeeDto dto) {
        company.saveCreatedEmployee(dto);
    }
}
