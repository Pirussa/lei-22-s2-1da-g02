package app.controller;

import app.domain.model.Company;
import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.ui.console.utils.Utils;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public boolean checkAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {
        return company.checkAppointment(snsNumber, vaccineAppointments);
    }

    /**
     * Register the arrival of an SNS user
     *
     * @param snsNumber Number that identifies the SNS user
     */
    public void registerArrival(int snsNumber) {
        company.registerArrival(snsNumber);
    }
}
