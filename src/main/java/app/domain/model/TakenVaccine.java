package app.domain.model;

import java.time.LocalDateTime;

public class TakenVaccine {

    private Vaccine vaccine;

    private LocalDateTime dateTime;

    public TakenVaccine(Vaccine vaccine, LocalDateTime dateTime) {
        this.vaccine = vaccine;
        this.dateTime = dateTime;
    }
}
