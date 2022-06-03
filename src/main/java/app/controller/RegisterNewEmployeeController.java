package app.controller;


import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import dto.RegisterNewEmployeeDto;

import java.io.NotSerializableException;
import java.io.Serializable;


/**
 * US010 - Register New Employee Controller
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RegisterNewEmployeeController implements Serializable {

    private final int PASSWORD_LENGTH = 7;

    private Company company = App.getInstance().getCompany();

    GenericClass<Employee> generics = new GenericClass<>();

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

    /**
     * Exports the list of Employees to a binary file.
     * @throws NotSerializableException
     */
    public void exportDataToFile() throws NotSerializableException {
        generics.binaryFileWrite(Constants.FILE_PATH_EMPLOYEES, Company.getEmployees());
    }

    /**
     * Verifies if the Employees are duplicated.
     * @param registerNewEmployeeDto: An Employee.
     * @return true if the Employees are duplicated, or false if they are not.
     */
    public boolean duplicatedEmployee(RegisterNewEmployeeDto registerNewEmployeeDto) {
        for (int index = 0; index < Company.getEmployees().size(); index++) {
            if (Company.getEmployees().get(index).getEmail().equals(registerNewEmployeeDto.name))
                return false;
        }
        return true;
    }
}
