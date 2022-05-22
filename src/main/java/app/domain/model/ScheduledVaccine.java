package app.domain.model;

import dto.ScheduledVaccineDto;
import mapper.ScheduledVaccineMapper;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */
public class ScheduledVaccine {

    /**
     * User´s SNS Number
     */
    private int snsNumber;

    /**
     * User´s selected vaccine type
     */
    private VaccineType vaccineType;

    /**
     * User´s selected date and time to be vaccinated
     */
    private LocalDateTime date;

    /**
     * List with all the appointments
     */
    private static List<ScheduledVaccine> appointmentsList = new ArrayList<>();

    /**
     * Creates a scheduled vaccine with the following attributes:
     *
     * @param snsNumber   The SNS Number related to the User with the scheduled Vaccine.
     * @param vaccineType The Vaccine's Type.
     * @param date        The date of the appointment.
     */
    public ScheduledVaccine(int snsNumber, VaccineType vaccineType, LocalDateTime date) {
        this.snsNumber = snsNumber;
        this.vaccineType = vaccineType;
        this.date = date;

    }

    /**
     * User is eligible for the appointment.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return true if the user doesn't have another appointment for the same Vaccine
     */
    public static boolean userIsEligibleForTheAppointment(ScheduledVaccineDto scheduledVaccineDto) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        ScheduledVaccine appointment = mapper.dtoToDomain(scheduledVaccineDto);
        for (ScheduledVaccine appointmentCheck : appointmentsList) {
            if ((appointment.getVaccineType() == appointmentCheck.getVaccineType()) && (appointment.getSnsNumber() == appointmentCheck.getSnsNumber()))
                return false;
        }
        return true;
    }

    /**
     * Adds appointment.
     *
     * @param scheduledVaccine the scheduled vaccine
     */
    public static void addAppointment(ScheduledVaccine scheduledVaccine) {
        appointmentsList.add(scheduledVaccine);
    }

    /**
     * Gives the info of a Scheduled Vaccine in String
     *
     * @return a String
     */

    @Override
    public String toString() {
        return "ScheduledVaccine{" +
                "snsNumber=" + snsNumber +
                ", vaccineType=" + vaccineType +
                ", date=" + date +
                '}';
    }

    /**
     * Gets date of the appointment.
     *
     * @return the date
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Gets SNS number.
     *
     * @return the sns number
     */
    public int getSnsNumber() {
        return snsNumber;
    }

    /**
     * Gets vaccine type.
     *
     * @return the vaccine type
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }
}