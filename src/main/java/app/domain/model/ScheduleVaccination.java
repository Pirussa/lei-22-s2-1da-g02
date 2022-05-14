package app.domain.model;

import java.sql.Time;
import java.util.Date;

public class ScheduleVaccination {

    private String snsNumber;

    private Date day;

    private Time time;

    private String vaccinationCenter;

    public ScheduleVaccination(String snsNumber, Date day, Time time, String vaccinationCenter) {
        this.snsNumber = snsNumber;
        this.day = day;
        this.time = time;
        this.vaccinationCenter = vaccinationCenter;
    }

    @Override
    public String toString() {
        return "The user " + snsNumber + " has an appointmet on " + day + " at " + time + " at " + vaccinationCenter;
    }
}
