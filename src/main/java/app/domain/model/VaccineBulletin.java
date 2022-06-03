package app.domain.model;

import java.time.LocalDateTime;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

/**
 * The type Taken vaccine.
 */
public class VaccineBulletin {

    /**
     * Vaccine taken by the user in previous appointment
     */
    private final Vaccine vaccine;

    /**
     * Date and Time of when the user took the last dose of the vaccine
     */
    private LocalDateTime dateTimeOfLastDose;

    /**
     * Tracks the number of doses the user has taken so far
     */
    private int doseNumber;

    private String lotNumber;

    public VaccineBulletin(Vaccine vaccine, LocalDateTime dateTime, int doses, String lotNumber) {
        this.vaccine = vaccine;
        this.dateTimeOfLastDose = dateTime;
        this.doseNumber = doses;
        this.lotNumber = lotNumber;
    }


    /**
     * Gets vaccine the user took.
     *
     * @return the vaccine taken by the user
     */
    public Vaccine getVaccine() {
        return vaccine;
    }

    /**
     * Gets the date and time
     *
     * @return the date and time of when the user took the previous vaccine
     */
    public LocalDateTime getDateTimeOfLastDose() {
        return dateTimeOfLastDose;
    }

    /**
     * Gets number of doses
     *
     * @return the number of doses the user has taken
     */
    public int getDose() {
        return doseNumber;
    }

    /**
     * Sets number of doses taken by the user.
     *
     * @param doses the doses
     */
    public void setDose(int doses) {
        this.doseNumber = doses;
    }
}