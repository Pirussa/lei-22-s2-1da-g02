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

    public int getSnsNumber() {
        return snsNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "SNS Number: " + snsNumber + " | Date and Time: " + dateTime + " |  Vaccine Type: " + vaccineType;
    }

    /**
     * ATUALIZAR
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

    public static boolean checkDateAndTime(LocalDateTime date, LocalDateTime day, int hour) {
        if (!Arrival.checkDate(date, day))
            return false;

        return Arrival.checkTime(date, hour);
    }


    private static boolean checkDate(LocalDateTime appointmentDay, LocalDateTime dateTime) {

        if (appointmentDay.getDayOfMonth() != dateTime.getDayOfMonth())
            return false;

        if (appointmentDay.getMonth() != dateTime.getMonth())
            return false;

        if (appointmentDay.getYear() != dateTime.getYear())
            return false;

        return true;
    }

    private static boolean checkTime(LocalDateTime appointmentTime, int timeArrival) {
        int appointmentHour = appointmentTime.getHour();

        // em vez de ser 1h maybe devia trabalhar uma SLOT DURATION

        return timeArrival >= appointmentHour - 1 && timeArrival <= appointmentHour;
    }

    public static boolean checkRegistration(int snsNumber, VaccinationCenter vaccinationCenterReceptionist) {
        List<Arrival> arrivals = vaccinationCenterReceptionist.getArrivalsList();

        for (Arrival arrival : arrivals)
            if (arrival.getSnsNumber() == snsNumber)
                return false;

        return true;
    }
}
