package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.stores.VaccinationCentersStore;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Departure implements Serializable {

    private final int snsNumber;

    private final LocalDateTime departureTime;



    public Departure(int snsNumber, LocalDateTime departureTime) {
        this.snsNumber = snsNumber;
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    @Override
    public String toString() {
        return "departureTime=" + departureTime;
    }


}
