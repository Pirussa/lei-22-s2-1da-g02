package app.ui.console;

import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;

import java.util.Scanner;


/** US012 - Specify Vaccine Type
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */


public class SpecifyNewVaccineTypeUI implements Runnable {

    private SpecifyNewVaccineTypeController ctrl = new SpecifyNewVaccineTypeController();

    public SpecifyNewVaccineTypeUI() {
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Specify Vaccine Type------");
        System.out.println();
        String description;
        boolean check;
        do {
            System.out.println("--Insert the new Vaccine Type:");
            description = sc.next();
            sc.nextLine();
            System.out.println();

            check = ctrl.specifyNewVaccineType(description);

            if (!check)
                System.out.println("Invalid Vaccine Type");

        } while (!check);

        System.out.println("Inforamtion about the new Vaccine Type");
        System.out.println("Description - " + description);
        System.out.println();
        if (Utils.confirmCreation()) {
            ctrl.saveVaccineType(description);
            System.out.println("New Vaccine Type added");
        }

    }


}




