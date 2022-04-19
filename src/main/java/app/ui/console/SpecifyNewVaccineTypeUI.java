package app.ui.console;

import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;


/**
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */


public class SpecifyNewVaccineTypeUI implements Runnable {
    private SpecifyNewVaccineTypeController ctrl;

    public SpecifyNewVaccineTypeUI() {}


    public void run() {
        System.out.println();
        System.out.println("------Specify Vaccine Type------");
        System.out.println();
        String type = Utils.readLineFromConsole("What's the vaccine type?");

        if (typeName())
            System.out.println("New Vaccine Type added");
    }

    public boolean typeName() {
        boolean validType = false;

        do {
            String type = Utils.readLineFromConsole("What's the vaccine type?");

            validType = ctrl.validateType(type);

            if (validType)
                System.out.println("Invalid Vaccine Type. Introduce it again");

        } while(!validType);

        return validType;
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
