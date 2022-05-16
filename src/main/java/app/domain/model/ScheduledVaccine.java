package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */
public class ScheduledVaccine {

    private int snsNumber;

    private VaccineType vaccineType;

    private LocalDateTime date;


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

    public boolean checkDate(LocalDateTime date) {
        if (date.getDayOfMonth() != this.date.getDayOfMonth())
            return false;

        if (date.getMonth() != this.date.getMonth())
            return false;

        if (date.getYear() != this.date.getYear())
            return false;

        return true;
    }
}
