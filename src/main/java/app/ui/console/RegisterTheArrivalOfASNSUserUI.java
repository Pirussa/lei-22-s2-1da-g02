package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.domain.model.SNSUser;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctrl = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        VaccinationCenter vaccinationCenterReceptionist  = ctrl.getVaccinationCenter();
        List<ScheduledVaccine> scheduleVaccinesOfTheVaccinationCenter = ctrl.getScheduledVaccineList(vaccinationCenterReceptionist);

        /*
        Criar um arraylist para get schedule vaccines
        Tendo isso posso começar a fazer as validações do check appointment
         */

        int snsNumber;
        do {
            snsNumber = Integer.parseInt( Utils.readLineFromConsole("Introduce SNS Number: "));
        } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.getUserIndexInUsersList(snsNumber) < 0);

        VaccinationCenter vaccinationCenterSNSUser = ctrl.getVaccinationCenter();


        if(checkRequirementsForRegistration(snsNumber, scheduleVaccinesOfTheVaccinationCenter, vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            ctrl.registerArrival(snsNumber);
            System.out.printf("%n The user has been registered");
        }



    }

    public boolean checkRequirementsForRegistration(int SNSNumber, List<ScheduledVaccine> vaccineAppointments, VaccinationCenter vaccinationCenterReceptionist, VaccinationCenter vaccinationCenterSNSUser) {
        if (!ctrl.checkAppointment(SNSNumber, vaccineAppointments)) {
            System.out.printf("%nThe user does not have any appointment");
            return false;
        }


        if (!ctrl.checkVaccinationCenters(vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            System.out.printf("%nWrong Vaccination Center");
            return false;
        }

        System.out.printf("%nThe user is ready to be registered");
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
