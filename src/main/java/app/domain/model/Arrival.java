package app.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Arrival {

    private int snsNumber;

    private LocalDateTime dateTime;

    private VaccineType vaccineType;

    public Arrival(int snsNumber, VaccineType vaccineType) {
        this.snsNumber = snsNumber;
        this.dateTime = LocalDateTime.now();
        this.vaccineType = vaccineType;
    }

    @Override
    public String toString() {
        return "Arrival{" +
                "snsNumber=" + snsNumber +
                ", dateTime=" + dateTime +
                ", vaccineType=" + vaccineType +
                '}';
    }

    /** ATUALIZAR
     * Check if a User has an appointment, introducing his/her SNS number
     *
     * @param snsNumber Number that identifies the SNS user
     * @return boolean ou Date and Time
     */
    public static ScheduledVaccine getUserAppointment(int snsNumber, List<ScheduledVaccine> vaccineAppointments) {

        for (ScheduledVaccine vaccineAppointment : vaccineAppointments)
            if (vaccineAppointment.getSnsNumber() == snsNumber)
                return vaccineAppointment;

        return null;
    }


    public boolean checkDate(LocalDateTime appointmentDay) {

        if (appointmentDay.getDayOfMonth() != dateTime.getDayOfMonth())
            return false;

        if (appointmentDay.getMonth() != dateTime.getMonth())
            return false;

        if (appointmentDay.getYear() != dateTime.getYear())
            return false;

        return true;
    }

    public boolean checkTime(LocalDateTime appointmentTime) {
        int timeArrival = dateTime.getHour();
        int appointmentHour = appointmentTime.getHour();

        // em vez de ser 1h maybe devia trabalhar uma SLOT DURATION

        return timeArrival >= appointmentHour - 1 && timeArrival <= appointmentHour;
    }
}
