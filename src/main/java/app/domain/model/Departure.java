package app.domain.model;

import java.time.LocalDateTime;

public class Departure {

    private final LocalDateTime departureTime;

    public Departure(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
}
