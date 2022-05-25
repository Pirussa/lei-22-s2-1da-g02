package app.controller;

import app.domain.model.*;
import java.util.List;

public class RegisterTheArrivalOfAnSnsUserController {

    private VaccinationCenter vaccinationCenterReceptionist;
    private VaccinationCenter vaccinationCenterSnsUser;
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
    public void setVaccinationCenterReceptionist(int index) {
        vaccinationCenterReceptionist = company.getVaccinationCenters().get(index);
        vaccineAppointments = vaccinationCenterReceptionist.getScheduledVaccineList();
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
     * @return boolean - true if both are on the same vaccination center
     */
    public boolean validateVaccinationCenters() {
        return vaccinationCenterReceptionist == vaccinationCenterSnsUser;
    }

    /**
     * Check if a User has an appointment
     *
     * @param snsNumber Number that identifies the SNS user
     * @return ScheduleVaccine - return an appointment of a user
     */
    public boolean getUserAppointment(int snsNumber) {

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
        return vaccinationCenterReceptionist.checkIfAlreadyRegistered(snsNumber);
    }

    /**
     * Checks if the user is on the vaccination center on the right day and time
     *
     * @return boolean - true if day and time match
     */
    public boolean validateDateAndTime() {
        return arrival.validateDateAndTime(appointment.getDate(), vaccinationCenterReceptionist);
    }

    /**
     * Registers the arrival of an SNS user
     *
     */
    public void registerArrival() {
        vaccinationCenterReceptionist.registerArrival(arrival);
        vaccinationCenterReceptionist.removeAppointment(appointment);
    }

    /**
     * Cleans the list of arrivals
     *
     */
    public void cleanArrivalsList() {
        vaccinationCenterReceptionist.cleanArrivalsList();
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
