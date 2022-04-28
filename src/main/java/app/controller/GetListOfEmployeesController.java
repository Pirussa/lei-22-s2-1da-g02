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


    private ArrayList<Employee> nurseList = new ArrayList<>();
    private ArrayList<Employee> receptionistList =new ArrayList<>();
    private ArrayList<Employee> centreCoordinatorList = new ArrayList<>();

    public void getListOfEmployeesWithAGivenRole() {
        ArrayList<Employee> emp = company.getEmployees();
        for (int positionArrayListEmployees = 0; positionArrayListEmployees < emp.size(); positionArrayListEmployees++) {
            if (emp.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_NURSE)) {
                for (int positionArrayListNurses = 0; positionArrayListNurses < nurseList.size(); positionArrayListNurses++) {
                    nurseList.add(emp.get(positionArrayListNurses));
                }
            } else if (emp.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_RECEPTIONIST)) {
                for (int positionArrayListReceptionist = 0; positionArrayListReceptionist < receptionistList.size(); positionArrayListReceptionist++) {
                    receptionistList.add(emp.get(positionArrayListReceptionist));
                }
            } else if (emp.get(positionArrayListEmployees).getRole().equals(Constants.ROLE_CENTRE_COORDINATOR)) {
                for (int positionArrayListCentreCoordinator = 0; positionArrayListCentreCoordinator < centreCoordinatorList.size(); positionArrayListCentreCoordinator++) {
                    centreCoordinatorList.add(emp.get(positionArrayListCentreCoordinator));
                }
            }
        }
    }

    public ArrayList<Employee> getNurseList(){
        return nurseList;
    }

    public ArrayList<Employee> getReceptionistList() {
        return receptionistList;
    }

    public ArrayList<Employee> getCentreCoordinatorList() {
        return centreCoordinatorList;
    }

    public void printNurseList(){
        for (int listPosition = 0; listPosition < nurseList.size() ; listPosition++) {
            System.out.println(nurseList.get(listPosition));
        }
    }

    public void printReceptionistList(){
        for (int listPosition = 0; listPosition < receptionistList.size(); listPosition++) {
            System.out.println(receptionistList.get(listPosition));
        }
    }

    public void printCentreCoordinator(){
        for (int listPosition = 0; listPosition < centreCoordinatorList.size(); listPosition++) {
            System.out.println(centreCoordinatorList.get(listPosition));
        }
    }

}



