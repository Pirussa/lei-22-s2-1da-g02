package app.ui.console;

import app.controller.RegisterTheArrivalOfASNSUserController;
import app.domain.model.Arrival;
import app.domain.model.SNSUser;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class RegisterTheArrivalOfASNSUserUI implements Runnable {

    private RegisterTheArrivalOfASNSUserController ctrl = new RegisterTheArrivalOfASNSUserController();

    @Override
    public void run() {
        System.out.println();
        System.out.println("------Register the Arrival of an SNS user------");
        System.out.println();

        VaccinationCenter vaccinationCenterReceptionist  = ctrl.getVaccinationCenter();
        List<ScheduledVaccine> scheduleVaccinesOfTheVaccinationCenter = ctrl.getScheduledVaccineList(vaccinationCenterReceptionist);


        int snsNumber;
        do {
            snsNumber = Utils.readIntegerFromConsole("Introduce SNS Number: ");
        } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.getUserIndexInUsersList(snsNumber) < 0);

        System.out.println();

        VaccinationCenter vaccinationCenterSNSUser = ctrl.getVaccinationCenter();


        if(checkRequirementsForRegistration(snsNumber, scheduleVaccinesOfTheVaccinationCenter, vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {

            System.out.println("1. Yes");
            System.out.println("2. No");
            if(Utils.readIntegerFromConsole("Insert your option: ") == 1) {
                ScheduledVaccine appointment = ctrl.checkAppointment(snsNumber, scheduleVaccinesOfTheVaccinationCenter);
                ctrl.registerArrival(new Arrival(snsNumber, appointment.getVaccineType()), vaccinationCenterSNSUser);
                System.out.printf("%nThe user has been registered");
            }
            else
                System.out.printf("%nThe user has not been registered");

        }

    }

    public boolean checkRequirementsForRegistration(int snsNumber, List<ScheduledVaccine> vaccineAppointments, VaccinationCenter vaccinationCenterReceptionist, VaccinationCenter vaccinationCenterSNSUser) {
        ScheduledVaccine appointment = ctrl.checkAppointment(snsNumber, vaccineAppointments);

        if (appointment == null) {
            System.out.printf("%nThe user does not have any appointment");
            return false;
        }

        Arrival arrival = new Arrival(snsNumber, appointment.getVaccineType());


        if(!arrival.checkDate(appointment.getDate()) || !arrival.checkTime(appointment.getDate())) {
            System.out.println("Wrong Day/Time");
            return false;
        }


        if (!ctrl.checkVaccinationCenters(vaccinationCenterReceptionist, vaccinationCenterSNSUser)) {
            System.out.printf("%nWrong Vaccination Center");
            return false;
        }

        if(!ctrl.checkRegistration(snsNumber, vaccinationCenterReceptionist)) {
            System.out.printf("%nUser has already been registered");
            return false;
        }

        System.out.printf("%nThe user meets all the requirements to be registered. Do you confirm this arrival?%n%n");
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
