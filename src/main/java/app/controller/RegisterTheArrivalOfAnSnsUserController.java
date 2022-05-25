package app.controller;

import app.domain.model.*;
import java.util.List;

public class RegisterTheArrivalOfAnSnsUserController {

    private VaccinationCenter vaccinationCenter;
    private List<ScheduledVaccine> vaccineAppointments;
    private ScheduledVaccine appointment;
    private Arrival arrival;
    private Company company = App.getInstance().getCompany();

    public RegisterTheArrivalOfAnSnsUserController() {}

    /**
     * Sets the Receptionist vaccination center
     *
     * @param index Position of the vaccination center
     */
    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index);
    }



    /**
     * Check if a User has an appointment
     *
     * @param snsNumber Number that identifies the SNS user
     * @return ScheduleVaccine - return an appointment of a user
     */
    public boolean checkAndSetUserAppointment(int snsNumber) {
        vaccineAppointments = vaccinationCenter.getScheduledVaccineList();

        for (ScheduledVaccine vaccineAppointment : vaccineAppointments)
            if (vaccineAppointment.getSnsNumber() == snsNumber) {
                appointment = vaccineAppointment;
                return true;
            }

        appointment = null;
        return false;
    }

    /**
     * Checks if a user has already been registered
     *
     * @param snsNumber The number that identifies an SNS user
     * @return boolean - true if the user is already registered
     */
    public boolean checkIfAlreadyRegistered(int snsNumber) {
        return vaccinationCenter.checkIfAlreadyRegistered(snsNumber);
    }

    /**
     * Checks if the user is on the vaccination center on the right day and time
     *
     * @return boolean - true if day and time match
     */
    public boolean validateDateAndTime() {
        return arrival.validateDateAndTime(appointment.getDate(), vaccinationCenter);
    }

    /**
     * Registers the arrival of an SNS user
     *
     */
    public void registerArrival() {
        vaccinationCenter.registerArrival(arrival);
        vaccinationCenter.removeAppointment(appointment);
    }

    /**
     * Cleans the list of arrivals
     *
     */
    public void cleanArrivalsList() {
        vaccinationCenter.cleanArrivalsList();
    }

    /**
     * Sets the arrival
     *
     * @param snsNumber The number that identifies an SNS user
     */
    public void setArrival(int snsNumber) {
        arrival = new Arrival(snsNumber, appointment.getVaccineType());
    }
}
