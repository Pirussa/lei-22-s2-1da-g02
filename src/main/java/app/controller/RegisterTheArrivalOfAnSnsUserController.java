package app.controller;

import app.domain.model.*;

import java.time.LocalDateTime;
import java.util.List;

public class RegisterTheArrivalOfAnSnsUserController {

    public RegisterTheArrivalOfAnSnsUserController() {}

    /**
     * Gets the list that contains the appointments of a certain vaccination center
     *
     * @param vaccinationCenter The chosen vaccination center
     * @return the list of appointments
     */
    public List<ScheduledVaccine> getScheduledVaccineList(VaccinationCenter vaccinationCenter) {
        return vaccinationCenter.getScheduledVaccineList();
    }

    /**
     * Checks the if user and receptionist are on the same vaccination center
     *
     * @param vaccinationCenterReceptionist The vaccination center where the receptionist is located
     * @param vaccinationCenterSNSUser The vaccination center where the user is located
     * @return Boolean - true if both are on the same vaccination center
     */
    public boolean checkVaccinationCenters(VaccinationCenter vaccinationCenterReceptionist, VaccinationCenter vaccinationCenterSNSUser) {
        return vaccinationCenterReceptionist == vaccinationCenterSNSUser;
    }

    /**
     * Check if a User has an appointment, for that day and time, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @return ScheduleVaccine - return an appointment of a user
     */
    public ScheduledVaccine getUserAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {
        return Arrival.getUserAppointment(snsNumber, vaccineAppointments);
    }

    /**
     * Checks if a user has already been registered
     *
     * @param snsNumber The number that identifies an SNS user
     * @param vaccinationCenterReceptionist The vaccination center where the receptionist is located
     * @return boolean - true if the user is already registered
     */
    public boolean checkRegistration(int snsNumber, VaccinationCenter vaccinationCenterReceptionist) {
        return Arrival.checkRegistration(snsNumber, vaccinationCenterReceptionist);
    }

    /**
     * Checks if the user is on the vaccination center on the right day and time
     *
     * @param date Date of the appointment
     * @param day Day of the arrival
     * @param hour Hour of the arrival
     * @return boolean - true if day and time match
     */
    public boolean checkDateAndTime(LocalDateTime date, LocalDateTime day, int hour, VaccinationCenter vaccinationCenter) {
        return Arrival.checkDateAndTime(date, day, hour, vaccinationCenter);
    }

    /**
     * Register the arrival of an SNS user
     *
     * @param arrival An instance of the class Arrival regarding the arrival of a user
     * @param vaccinationCenter The vaccination center where the user is located
     */
    public void registerArrival(Arrival arrival, VaccinationCenter vaccinationCenter) {
        vaccinationCenter.registerArrival(arrival, vaccinationCenter);
    }
}
