package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctlr = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        int vaccinationCenterReceptionist = getOption();
        boolean check = false;
        int SNSNumber = 0;

        do {
            try {
                System.out.print("Introduze the SNS number: ");
                SNSNumber = sc.nextInt();
                check = true;

            } catch (NumberFormatException e) {
                System.out.println("Invalid Option");
                System.out.println();
            }

        } while (!check);
        // VERIFICAR SE O SNS NUMBER É VÁLIDO

        int vaccinationCenterSNSUser = getOption();

        /*
        PRÓXIMOS PASSOS
        • Receber a data e time
        • Validar tudo com o if comentado em baixo, ele dará feedback
        • Depois de validado, register o arrival
         */


//        if (ctlr.checkRequirementsForRegistration(SNSNumber, vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
//            //verificar se posso ser registado, saber o que considerar
//            //Depois da verifacação user o crlr.registerArrival
//        }
    }

    public int getOption() {
        return Utils.showAndSelectIndex(ctlr.getVaccinationCenterList(), "Vaccination Centers");
    }

    /*
    Things to consider
    • Check if the SNS user has an appointment
    • Know if I have to work with day and hours when check if the user is ready to go to the waitting room

    Funcionamento
    • O user forneceria o seu SNS number e a receptionist iria à lista de appointments checkar se esse user realmente tem um appointment
    • Tendo um appointment ela adiciona-o à lista de users presentes ready para tomar vaccine
     */
}
