package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;

import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctlr = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        System.out.print("Introduze the SNS number: ");
        int SNSNumber = sc.nextInt();

        // VALIDAR SNS NUMBER

        if (ctlr.checkAppointment(SNSNumber) != null) {
            //verificar se posso ser registado, saber o que considerar
            //Depois da verifacação user o crlr.registerArrival
        }
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
