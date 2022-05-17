package app.controller;

import app.domain.model.*;
import app.ui.console.utils.Utils;

import java.util.List;

public class RegisterTheArrivalOfASNSUserController {

    private Company company = App.getInstance().getCompany();

    public RegisterTheArrivalOfASNSUserController() {}


    public VaccinationCenter getVaccinationCenter() {
        return Utils.selectVaccinationCenterUI();
    }

    public List<ScheduledVaccine> getScheduledVaccineList(VaccinationCenter vaccinationCenter) {
        return vaccinationCenter.getScheduledVaccineList();
    }

    public boolean checkVaccinationCenters(VaccinationCenter vaccinationCenterReceptionist, VaccinationCenter vaccinationCenterSNSUser) {
        return vaccinationCenterReceptionist == vaccinationCenterSNSUser;
    }

    /**
     * Check if a User has an appointment, for that day and time, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @return boolean - true if the user has
     */
    public ScheduledVaccine checkAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {
        return Arrival.getUserAppointment(snsNumber, vaccineAppointments);
    }

    /**
     * Register the arrival of an SNS user
     *
     * @param arrival An object regarding the  arrival of a user
     * @param vaccinationCenter The vacciantion center the user is
     */
    public void registerArrival(Arrival arrival, VaccinationCenter vaccinationCenter) {
        company.registerArrival(arrival, vaccinationCenter);
    }
}
