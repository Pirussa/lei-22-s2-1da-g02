package app.domain.model;

import java.sql.Time;
import java.util.Date;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccination {

    private String snsNumber;

    private Date day;

    private Time time;

    private String vaccinationCenter;

    private VaccineType vaccineType;

    public ScheduleVaccination(String snsNumber, Date day, Time time, String vaccinationCenter, VaccineType vaccineType) {
        this.snsNumber = snsNumber;
        this.day = day;
        this.time = time;
        this.vaccinationCenter = vaccinationCenter;
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString() {
        return "The user " + snsNumber + " has an appointmet on " + day + " at " + time + " at " + vaccinationCenter;
    }
}
