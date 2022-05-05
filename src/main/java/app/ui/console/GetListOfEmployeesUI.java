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
        int option;
        Scanner read = new Scanner(System.in);
        ctrl.fillListOfEmployeesWithAGivenRole();
        System.out.println("");
        System.out.println("**You have chosen to get a list of Employees**");
        System.out.println("");
        System.out.println("Select the option you pretend to get a list of:");
        System.out.println("1- List of Nurses");
        System.out.println("2- List of Receptionists");
        System.out.println("3- List of Centre Coordinators");
        System.out.println("");
        System.out.print("Type your option: ");


        do {
            option = read.nextInt();
            if (!ctrl.getEmployees().isEmpty()) {
                switch (option) {
                    case 1:
                        if (!ctrl.getNurseList().isEmpty()) {
                            for (int listPosition = 0; listPosition < ctrl.getNurseList().size(); listPosition++) {
                                System.out.println(ctrl.getNurseList().get(listPosition));
                            }
                            break;
                        }
                    case 2:
                        if (!ctrl.getReceptionistList().isEmpty()) {
                            for (int listPosition = 0; listPosition < ctrl.getReceptionistList().size(); listPosition++) {
                                System.out.println(ctrl.getReceptionistList().get(listPosition));
                                break;
                            }
                        }
                    case 3:
                        if (!ctrl.getCentreCoordinatorList().isEmpty()) {
                            for (int listPosition = 0; listPosition < ctrl.getCentreCoordinatorList().size(); listPosition++) {
                                System.out.println(ctrl.getCentreCoordinatorList().get(listPosition));
                                break;
                            }
                        }

                    case 0:

                    default:
                        System.out.println("Invalid option, please select one that's valid.");
                }
            }
            System.out.println("0 - Go Back.");
        } while (option == 1 || option == 2 || option == 3 || option == 0);
    }

}
