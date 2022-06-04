package app.domain.model;

import java.time.LocalDate;

public class FullyVaccinatedTotalDay {

    private final LocalDate date;

    private int total;

    public FullyVaccinatedTotalDay(int previousDayTotal) {
        date = LocalDate.now();
        total = previousDayTotal;
    }

    public void addFullyVaccinatedUser(){
        total++;
    }

    public int getTotal() {
        return total;
    }

public LocalDate getDate() {
        return date;
    }

}
