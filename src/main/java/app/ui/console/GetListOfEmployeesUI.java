package app.ui.console;

import app.controller.GetListOfEmployeesController;

import java.util.Scanner;


/**
 * US011 - Get a list of Employees with a given role.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class GetListOfEmployeesUI implements Runnable {

    public GetListOfEmployeesUI() {
    }

    private GetListOfEmployeesController ctrl = new GetListOfEmployeesController();

    public void run() {
        int op;
        Scanner read = new Scanner(System.in);
        System.out.println("");
        System.out.println("-----|You have chosen to get a list of Employees.|-----");
        System.out.println("");
        System.out.println("Select the option you pretend to get a list of:");
        System.out.println("1- List of Nurses");
        System.out.println("2- List of Receptionists");
        System.out.println("3- List of Centre Coordinators");
        op = read.nextInt();

       /* if(!ctrl.getEmployees().isEmpty())
        switch (op) {
            case 1:
                if (!ctrl.getNurseList().isEmpty()) {
                    ctrl.printNurseList();
                    break;
                }
            case 2:
                if(!ctrl.getReceptionistList().isEmpty()) {
                    ctrl.printReceptionistList();
                    break;
                }
            case 3:
                if(!ctrl.getCentreCoordinatorList().isEmpty()) {
                    ctrl.printCentreCoordinator();
                    break;
                }
            default:
                System.out.println("Invalid option, please select one that's valid.");
        } */
    }
}
