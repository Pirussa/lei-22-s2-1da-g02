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


    ArrayList<Employee> nurseList = new ArrayList<>();
    ArrayList<Employee> receptionistList = new ArrayList<>();
    ArrayList<Employee> centreCoordinatorList = new ArrayList<>();

    public void GetListOfEmployeesWithAGivenRole(ArrayList<Employee> employees, ArrayList<Employee> nurseList, ArrayList<Employee> receptionistList, ArrayList<Employee> centreCoordinatorList) {
        for (int positionArrayListEmployees = 0; positionArrayListEmployees < employees.size(); positionArrayListEmployees++) {
            if (employees.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_NURSE)) {
                for (int positionArrayListNurses = 0; positionArrayListNurses < nurseList.size(); positionArrayListNurses++) {
                    nurseList.add(employees.get(positionArrayListNurses));
                }
            } else if (employees.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_RECEPTIONIST)) {
                for (int positionArrayListReceptionist = 0; positionArrayListReceptionist < receptionistList.size(); positionArrayListReceptionist++) {
                    receptionistList.add(employees.get(positionArrayListReceptionist));
                }
            } else if (employees.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_CENTRE_COORDINATOR)) {
                for (int positionArrayListCentreCoordinator = 0; positionArrayListCentreCoordinator < centreCoordinatorList.size(); positionArrayListCentreCoordinator++) {
                    centreCoordinatorList.add(employees.get(positionArrayListCentreCoordinator));
                }
            }
        }
    }
}



