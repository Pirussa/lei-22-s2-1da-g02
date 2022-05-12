package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctlr = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        int vaccinationCenterReceptionist = Utils.showAndSelectIndex(ctlr.getVaccinationCenterList(), "Vaccination Centers");
        boolean checkInt = false;
        boolean checkValid = false;
        int SNSNumber = 0;

        do {
            do {
                try {
                    System.out.print("Introduze the SNS number: ");
                    SNSNumber = sc.nextInt();
                    checkInt = true;

                } catch (NumberFormatException e) {
                    System.out.println("Invalid SNS User");
                    System.out.println();
                }

            } while (!checkInt);

            if (SNSUser.validateSNSUserNumber(String.valueOf(SNSNumber)))
                checkValid = true;
//            else if (check se o sns number dele coincide com algum já existente
//                System.out.println(SNSNumber + " does not exist!");
            else
                System.out.println("Invalid SNS User");

        } while (!checkValid);




        int vaccinationCenterSNSUser = Utils.showAndSelectIndex(ctlr.getVaccinationCenterList(), "Vaccination Centers");

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

    public boolean checkRequirementsForRegistration(int SNSNumber, int vaccinationCenterReceptionist, int vaccinationCenterSNSUser, Date date, Time time) {
        if (ctlr.checkAppointment(SNSNumber) == null) {
            System.out.println("The user does not have any appointment for today");
            return false;
        }


        if (!ctlr.checkVaccinationCenters(vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            System.out.println("Wrong Vaccination Center");
            return false;
        }



        if (!ctlr.checkTimeAndDate(date, time)) {
            System.out.println("Wrong Day/Hour");
            return false;
        }


        return true;
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
