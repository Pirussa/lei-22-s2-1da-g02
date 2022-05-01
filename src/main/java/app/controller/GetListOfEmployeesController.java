package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;

import java.util.ArrayList;

/**
 * US011 - Get a list of Employees with a given role.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class GetListOfEmployeesController {

    private Company company = App.getInstance().getCompany();

    public GetListOfEmployeesController() {
    }

    public ArrayList<Employee> getEmployees(){
        return company.getEmployees();
    }

    public ArrayList<Employee> getNurseList(){
        return company.getNurseList();
    }

    public ArrayList<Employee> getReceptionistList() {
        return company.getReceptionistList();
    }

    public ArrayList<Employee> getCentreCoordinatorList() {
        return company.getCentreCoordinatorList();
    }


}


