package app.controller;

import app.domain.model.Company;
import app.domain.model.Employee;
import app.ui.console.utils.GetListOfEmployeesUI;

import java.util.ArrayList;

/**
 * US011 - Get a list of Employees with a given role.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class GetListOfEmployeesController {

    private Company company = App.getInstance().getCompany();

    public GetListOfEmployeesController(){
    }

    public ArrayList<Employee> getListOfEmployees(){
        return company.getListOfEmployees();
    }


}
