package app.controller;

import app.domain.model.*;
import java.util.List;

public class RegisterTheArrivalOfAnSnsUserController {

    private VaccinationCenter vaccinationCenterReceptionist;
    private VaccinationCenter vaccinationCenterSnsUser;
    private List<ScheduledVaccine> scheduledVaccineList;
    private ScheduledVaccine appointment;
    private Arrival arrival;
    private Company company = App.getInstance().getCompany();

    public RegisterTheArrivalOfAnSnsUserController() {}

    /**
     * Sets the Receptionist vaccination center
     *
     * @param index Position of the vaccination center
     */
    public void setVaccinationCenterReceptionist(int index) {
        vaccinationCenterReceptionist = company.getVaccinationCenters().get(index);
        scheduledVaccineList = vaccinationCenterReceptionist.getScheduledVaccineList();
    }

    /**
     * Sets the Sns User vaccination center
     *
     * @param index Position of the vaccination center
     */
    public void setVaccinationCenterSnsUser(int index) {
        vaccinationCenterSnsUser = company.getVaccinationCenters().get(index);
    }


    /**
     * Checks the if user and receptionist are on the same vaccination center
     *
     * @return Boolean - true if both are on the same vaccination center
     */
    public boolean checkVaccinationCenters() {
        return vaccinationCenterReceptionist == vaccinationCenterSnsUser;
    }

    /**
     * Check if a User has an appointment, for that day and time, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @return ScheduleVaccine - return an appointment of a user
     */
    public boolean getUserAppointment(int snsNumber) {
        appointment = Arrival.getUserAppointment(snsNumber, scheduledVaccineList);
        return appointment != null;
    }

    /**
     * Checks if a user has already been registered
     *
     * @param snsNumber The number that identifies an SNS user
     * @return boolean - true if the user is already registered
     */
    public boolean checkRegistration(int snsNumber) {
        return Arrival.checkRegistration(snsNumber, vaccinationCenterReceptionist);
    }

    /**
     * Checks if the user is on the vaccination center on the right day and time
     *
     * @return boolean - true if day and time match
     */
    public boolean checkDateAndTime() {
        return arrival.checkDateAndTime(appointment.getDate(), vaccinationCenterReceptionist);
    }

    /**
     * Registers the arrival of an SNS user
     *
     */
    public void registerArrival() {
        vaccinationCenterReceptionist.registerArrival(arrival);
    }

    /**
     * Cleans the lisf of arrivals
     *
     */
    public void cleanArrivalsList() {
        vaccinationCenterReceptionist.cleanArrivalsList();
    }

    public void setArrival(int snsNumber) {
        arrival = new Arrival(snsNumber, appointment.getVaccineType());
    }
}
