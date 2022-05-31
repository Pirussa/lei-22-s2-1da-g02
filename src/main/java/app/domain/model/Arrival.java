package app.domain.model;

import java.time.LocalDateTime;

/**
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class Arrival {

    private final int snsNumber;

    private final LocalDateTime dateTime;

    private final VaccineType vaccineType;

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

    public VaccineType getVaccineType() {
        return vaccineType;
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
     *
     * @param date Date of the appointment
     * @param vaccinationCenter Vaccination Center where the user has an appointment
     * @return boolean - true if the user is on time
     */
    public boolean validateDateAndTime(LocalDateTime date, VaccinationCenter vaccinationCenter) {
        if (!validateDate(date))
            return false;

        return validateTime(date, vaccinationCenter);
    }


    private boolean validateDate(LocalDateTime appointmentDay) {

        if (appointmentDay.getDayOfMonth() != dateTime.getDayOfMonth())
            return false;

        if (appointmentDay.getMonth() != dateTime.getMonth())
            return false;

        return appointmentDay.getYear() == dateTime.getYear();
    }

    private boolean validateTime(LocalDateTime appointmentTime, VaccinationCenter vaccinationCenter) {
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

}
