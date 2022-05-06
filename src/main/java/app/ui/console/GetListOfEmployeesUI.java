package app.ui.console;

import app.controller.GetListOfEmployeesController;
import app.domain.model.Employee;

import java.util.ArrayList;
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



        do {

            System.out.print("Type your option: ");
            option = read.nextInt();

            switch (option) {
                case 1:
                    if (!ctrl.getNurseList().isEmpty()) {
                        System.out.println("**These are the Nurses registered in the system:**");
                        for (int listPosition = 0; listPosition < ctrl.getNurseList().size(); listPosition++) {
                            System.out.println(ctrl.getNurseList().get(listPosition));
                        }
                    } else {
                        System.out.println("There aren't any Nurses registered in the system.");
                        System.out.println("");
                    }
                    break;
                case 2:
                    if (!ctrl.getReceptionistList().isEmpty()) {
                        System.out.println("**These are the Receptionists registered in the system:**");
                        for (int listPosition = 0; listPosition < ctrl.getReceptionistList().size(); listPosition++) {
                            System.out.println(ctrl.getReceptionistList().get(listPosition));
                        }
                    } else {
                        System.out.println("There aren't any Receptionists registered in the system.");
                        System.out.println("");
                    }
                    break;
                case 3:
                    if (!ctrl.getCentreCoordinatorList().isEmpty()) {
                        System.out.println("**These are the Centre Coordinators registered in the system:**");
                        for (int listPosition = 0; listPosition < ctrl.getCentreCoordinatorList().size(); listPosition++) {
                            System.out.println(ctrl.getCentreCoordinatorList().get(listPosition));
                        }
                    } else {
                        System.out.println("There aren't any Centre Coordinators registered in the system.");
                        System.out.println("");
                    }
                    break;


                default:
                    System.out.println("Invalid option, please select one that's valid.");
            }
            if (option == 1) {
                System.out.println("");
                System.out.println("**Other options:**");
                System.out.println("2 - Receptionists List.");
                System.out.println("3 - Centre Coordinators List.");
                System.out.println("0 - Return.");
                System.out.println("");
            }

            if (option == 2) {
                System.out.println("");
                System.out.println("**Other options:**");
                System.out.println("1 - Nurses List.");
                System.out.println("3 - Centre Coordinators List.");
                System.out.println("0 - Return.");
                System.out.println("");
            }

            if (option == 3) {
                System.out.println("");
                System.out.println("**Other options:**");
                System.out.println("1 - Nurses List.");
                System.out.println("2 - Receptionists List.");
                System.out.println("0 - Return.");
                System.out.println("");
            }

        } while (option == 1 || option == 2 || option == 3);

    }

}
