package app.controller;


import app.domain.model.Company;
import app.ui.console.RegisterNewEmployeeDto;



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

    /**
     * Register an Employee in the company storage
     *
     * @param dto A data transfer object with all the necessary information about the new Employee
     * @return true if the new Employee data is valid
     */

    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        return company.registerNewEmployee(dto);
    }

    /**
     * Saves an Employee into the Company storage.
     *
     * @param dto A data transfer object with all the necessary information about the new Employee
     * @param selectedRole Selected role for the new Employee by the user
     */

    public void saveCreatedEmployee(RegisterNewEmployeeDto dto, String selectedRole) {
        company.saveCreatedEmployee(dto, selectedRole);
    }
}
