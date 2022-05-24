package app.domain.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class Arrival {

    private int snsNumber;

    private LocalDateTime dateTime;

    private VaccineType vaccineType;

    /**
     * Creates and instance of the Arrival class with the following attributes
     *
     * @param snsNumber   Number that identifies the SNS user
     * @param vaccineType The type of vaccine
     */
    public Arrival(int snsNumber, VaccineType vaccineType) {
        this.snsNumber = snsNumber;
        this.dateTime = LocalDateTime.now();
        this.vaccineType = vaccineType;
    }

    /**
     * Gets the SNS number
     *
     * @return int
     */
    public int getSnsNumber() {
        return snsNumber;
    }

    /**
     * Gets the Date and Time
     *
     * @return LocalDateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Information about the arrival of a user
     *
     * @return String
     */
    @Override
    public String toString() {
        return "SNS Number: " + snsNumber + " | Day: " + dateTime.getDayOfMonth() + "/" + dateTime.getMonth() + "/" + dateTime.getYear() + " , at " + dateTime.getHour() + ":" + dateTime.getMinute() + " |  Vaccine Type: " + vaccineType;
    }

    /**
     * ATUALIZAR
     * Check if a User has an appointment, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @return ScheduleVaccine - return an appointment of a user
     */
    public static ScheduledVaccine getUserAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {

        for (ScheduledVaccine vaccineAppointment : vaccineAppointments)
            if (vaccineAppointment.getSnsNumber() == snsNumber)
                return vaccineAppointment;

        return null;
    }

    /**
     * Checks if the user is on the vaccination center on the right day and time
     *
     * @param date Date of the appointment
     * @return boolean - true if day and time match
     */
    public boolean checkDateAndTime(LocalDateTime date, VaccinationCenter vaccinationCenter) {
        if (checkDate(date))
            return false;

        return checkTime(date, vaccinationCenter);
    }


    private boolean checkDate(LocalDateTime appointmentDay) {

        if (appointmentDay.getDayOfMonth() != dateTime.getDayOfMonth())
            return false;

        if (appointmentDay.getMonth() != dateTime.getMonth())
            return false;

        if (appointmentDay.getYear() != dateTime.getYear())
            return false;

        return true;
    }

    private boolean checkTime(LocalDateTime appointmentTime, VaccinationCenter vaccinationCenter) {
        int appointmentHour = appointmentTime.getHour();

        /*
        O QUE FAZER - EM VEZ DE 1H VAMOS USAR SLOT DURATION
        Saber os vários slot durations, mayve uma lista ou algo do género
        Criar método para identificar a qual slot pertence o appointment
        Verificar se o arrival está compreendido entre o slot anterior e o ínicio do dele
            Se sim, registar
         */

        return dateTime.getHour() >= appointmentHour - 1 && dateTime.getHour() <= appointmentHour;
    }

    /**
     * Checks if a user has already been registered
     *
     * @param snsNumber                     The number that identifies an SNS user
     * @param vaccinationCenterReceptionist The vaccination center where the receptionist is located
     * @return boolean - true if the user is already registered
     */
    public static boolean checkRegistration(int snsNumber, VaccinationCenter vaccinationCenterReceptionist) {
        List<Arrival> arrivals = vaccinationCenterReceptionist.getArrivalsList();

        for (Arrival arrival : arrivals)
            if (arrival.getSnsNumber() == snsNumber)
                return false;

        return true;
    }
}
