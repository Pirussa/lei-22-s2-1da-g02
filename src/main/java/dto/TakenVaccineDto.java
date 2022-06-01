package dto;

import app.domain.model.Vaccine;

import java.time.LocalDateTime;

public class TakenVaccineDto {

    public final Vaccine vaccine;
    public LocalDateTime dateTimeOfLastDose;

    public int doseNumber;

    public TakenVaccineDto(Vaccine vaccine, LocalDateTime dateTime, int doses) {
        this.vaccine = vaccine;
        this.dateTimeOfLastDose = dateTime;
    }
}
