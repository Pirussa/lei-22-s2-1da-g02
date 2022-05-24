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
     * @param snsNumber Number that identifies the SNS user
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
     * @return int - snsNumber
     */
    public int getSnsNumber() {
        return snsNumber;
    }

    /**
     * Gets the Date and Time
     *
     * @return LocalDateTime - dateTime
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
     * Check if a User has an appointment, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @param vaccineAppointments List that contains the appointments
     * @return ScheduleVaccine - returns the appointment of a user
     */
    public static ScheduledVaccine getUserAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {

        for (ScheduledVaccine vaccineAppointment : vaccineAppointments)
            if (vaccineAppointment.getSnsNumber() == snsNumber)
                return vaccineAppointment;

        return null;
    }

    /**
     *
     * @param date Date of the appointment
     * @param vaccinationCenter Vaccination Center where the user has an appointment
     * @return boolean - true if the user is on time
     */
    public boolean checkDateAndTime(LocalDateTime date, VaccinationCenter vaccinationCenter) {
        if (!checkDate(date))
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
        int slotDuration = Integer.parseInt(vaccinationCenter.getStrSlotDuration());
        LocalDateTime minusTime = subtractTimes(slotDuration, appointmentTime);
        LocalDateTime plusTime = sumTimes(appointmentTime);

        return (dateTime.getHour() == minusTime.getHour() && dateTime.getMinute() >= minusTime.getMinute()) && dateTime.getHour() <= plusTime.getHour() ;
    }

    private LocalDateTime subtractTimes(int slotDuration, LocalDateTime appointmentTime) {
        return appointmentTime.minusMinutes(slotDuration);
    }

    private LocalDateTime sumTimes(LocalDateTime appointmentTime) {
        return appointmentTime.plusMinutes(10);
    }

    /**
     * Checks if a user has already been registered
     *
     * @param snsNumber The number that identifies an SNS user
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
