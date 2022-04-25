package app.ui.console;

import app.controller.SpecifyNewVaccineTypeController;
import app.ui.console.utils.Utils;

import java.util.Scanner;


/**
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

            check = ctrl.specifyNewVaccineType(type);

            if (!check)
                System.out.println("Invalid Vaccine Type");

        } while (!check);

        /*
        • Perguntar
        • Validar
            • null
            • empty
        • Confirmar
            • mostrar input
            • perguntar se é aquilo
        • Salvar
         */


    }
}




