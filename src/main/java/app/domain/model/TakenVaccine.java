package app.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Taken vaccine.
 */
public class TakenVaccine {

    /**
     * Vaccine taken by the user in previous appointment
     */
    private Vaccine vaccine;

    /**
     * Date and Time of when the user took the vaccine
     */
    private LocalDateTime dateTime;

    /**
     * Tracks the number of doses the user has taken so far
     */
    private int doseNumber;

    public TakenVaccine(Vaccine vaccine, LocalDateTime dateTime, int doses) {
        this.vaccine = vaccine;
        this.dateTime = dateTime;
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
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Gets number of doses
     *
     * @return the number of doses the user has taken
     */
    public int getDose() {
        return doseNumber;
    }

    public void setDose(int doses) {
        this.doseNumber = doses;
    }
}