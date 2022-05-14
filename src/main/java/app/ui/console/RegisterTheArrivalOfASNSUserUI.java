package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.ui.console.utils.Utils;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctrl = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        int vaccinationCenterReceptionist = Utils.showAndSelectIndex(ctrl.getVaccinationCenterList(), "Vaccination Centers");
        boolean checkValid = false;
        String SNSNumber;

        do {

            System.out.print("Introduce the SNS number: ");
            SNSNumber = sc.next();

            //
            if (Utils.validateSNSUserNumber(SNSNumber))
                checkValid = true;
//            else if (check se o sns number dele coincide com algum já existente
//                System.out.println(SNSNumber + " does not exist!");
            else
                System.out.println("Invalid SNS User");

        } while (!checkValid);




        int vaccinationCenterSNSUser = Utils.showAndSelectIndex(ctrl.getVaccinationCenterList(), "Vaccination Centers");


        /*
        PRÓXIMOS PASSOS
        • Receber a data e time
        • Validar tudo com o if comentado em baixo, ele dará feedback
        • Depois de validado, register o arrival
         */

    }

    public boolean checkRequirementsForRegistration(int SNSNumber, int vaccinationCenterReceptionist, int vaccinationCenterSNSUser, Date date, Time time) {
        if (ctrl.checkAppointment(SNSNumber) == null) {
            System.out.println("The user does not have any appointment for today");
            return false;
        }


        if (!ctrl.checkVaccinationCenters(vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            System.out.println("Wrong Vaccination Center");
            return false;
        }



        if (!ctrl.checkTimeAndDate(date, time)) {
            System.out.println("Wrong Day/Hour");
            return false;
        }


        return true;
    }

    /*
    Things to consider
    • Check if the SNS user has an appointment
    • Know the time limit to accept a user knowing he has an appointment for that day and place

    Funcionamento
    • O user forneceria o seu SNS number e a receptionist iria à lista de appointments checkar se esse user realmente tem um appointment
    • Tendo um appointment ela adiciona-o à lista de users presentes ready para tomar vaccine
     */
}
