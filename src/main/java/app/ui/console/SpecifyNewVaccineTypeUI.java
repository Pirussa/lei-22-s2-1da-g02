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
        String type;
        boolean check;
        do {
            System.out.println("--Insert the new Vaccine Type:");
            type = sc.next();
            sc.nextLine();
            System.out.println();

            check = ctrl.specifyNewVaccineType(type);

            if (!check)
                System.out.println("Invalid Vaccine Type");

        } while (!check);

        System.out.println("Inforamtion about the new Vaccine Type");
        System.out.println("Type - " + type);
        System.out.println();
        if (Utils.confirmCreation()) {
            ctrl.saveVaccineType(type);
            System.out.println("New Vaccine Type added");
        }

    }


}




