package app.domain.model;

public class ScheduleVaccination {

    private String snsNumber;

    private int day;

    private int time;

    private String vaccinationCenter;

    public ScheduleVaccination(String snsNumber, int day, int time, String vaccinationCenter) {
        this.snsNumber = snsNumber;
        this.day = day;
        this.time = time;
        this.vaccinationCenter = vaccinationCenter;
    }
}
