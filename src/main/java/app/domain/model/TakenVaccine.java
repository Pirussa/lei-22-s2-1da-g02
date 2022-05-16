package app.domain.model;

import java.time.LocalDateTime;

public class TakenVaccine {
    private Vaccine vaccine;
    private LocalDateTime dateTime;
    private int doseNumber;

    public TakenVaccine(Vaccine vaccine, LocalDateTime dateTime, int doses) {
        this.vaccine = vaccine;
        this.dateTime = dateTime;
    }
    public Vaccine getVaccine() {
        return vaccine;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public int getDose() {
        return doseNumber;
    }
    public void setDose(int doses) {
        this.doseNumber = doses;
    }
}