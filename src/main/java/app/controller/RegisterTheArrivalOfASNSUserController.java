package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class RegisterTheArrivalOfASNSUserController {

    private Company company = App.getInstance().getCompany();

    public RegisterTheArrivalOfASNSUserController() {}

    /**
     * Gets a list of Vaccination Centers
     *
     * @return Arraylist - an array list of the previous created vaccination centers
     */
    public ArrayList<VaccinationCenter> getVaccinationCenterList() {
        return company.getVaccinationCenters();
    }

    /**
     * Checks if the user is at the right time to be registered
     *
     * @param date the Date of the vaccine appointment
     * @param time the Time of the vaccine appointment
     * @return boolean - checks if the user is at the right time to be registered
     */
    public boolean checkTimeAndDate(Date date, Time time) {
        Date currentDate = new Date();
        Time currentTime = new Time(currentDate.getTime());

        if (!date.equals(currentDate)) {
            System.out.println("Wrong Date");
            return false;
        }

        if(!time.equals(currentTime)) {
            System.out.println("Wrong time");
            return false;
        }
        return true;
    }

    public boolean checkVaccinationCenters(int vaccinationCenterReceptionist, int vaccinationCenterSNSUser) {
        return vaccinationCenterReceptionist == vaccinationCenterSNSUser;
    }

    // VAI DAR RETURN A UM OBJETO DE VACCINE APPOINTMENT OU APENAS À DATA E TIME
    public String checkAppointment(int SNSNumber) {
        System.out.println("Invalid SNS Number");
        return company.checkAppointment(SNSNumber);
    }

    public void registerArrival(int SNSNumber) {
        company.registerArrival(SNSNumber);
    }
}
