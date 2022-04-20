package app.ui.console;

import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;


/**
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */


public class SpecifyNewVaccineTypeUI implements Runnable {
    private SpecifyNewVaccineTypeController ctrl = new SpecifyNewVaccineTypeController();

    public SpecifyNewVaccineTypeUI() {}


    public void run() {
        System.out.println();
        System.out.println("------Specify Vaccine Type------");
        System.out.println();

        String type = createVaccineType();
        String answer = Utils.readLineFromConsole("The chosen name is valid. Confirm (yes/no)");
// CORREÇÃO, NÃO É SUPOSTO PASSAR COMO PARAMETRO UMA STRING MAS SIM O OBJETO VACCINETYPE
        if (answer.equals("yes")) {
            ctrl.saveVaccineType(type);
            System.out.println("Vaccine Type added");
        }

        else
            System.out.println("");


    }

    public String createVaccineType() {
        boolean creation;
        String type;

        do {
            type = Utils.readLineFromConsole("What's the vaccine type?");

            creation = ctrl.createVaccineType(type);

            if (!creation)
                System.out.println("Invalid Vaccine Type. Introduce it again");

        } while(!creation);
        return type;
    }


//    System.out.println("\nLogin UI:");
//
//    int maxAttempts = 3;
//    boolean success = false;
//        do
//    {
//        maxAttempts--;
//        String id = Utils.readLineFromConsole("Enter UserId/Email: ");
//        String pwd = Utils.readLineFromConsole("Enter Password: ");
//
//        success = ctrl.doLogin(id, pwd);
//        if (!success)
//        {
//            System.out.println("Invalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
//        }
//
//    } while (!success && maxAttempts > 0);
//        return success;
}
