package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;
import dto.VaccinationCenterDto;
import mapper.ScheduledVaccineMapper;
import mapper.VaccinationCenterMapper;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class ScheduleVaccineController {


    private final Company company = App.getInstance().getCompany();
    private final AuthFacade authFacade = company.getAuthFacade();
    private VaccinationCenter vaccinationCenter;


    /**
     * Instantiates a new Schedule vaccine controller.
     */
    public ScheduleVaccineController() {
    }

    /**
     * Schedule vaccine if the appointment is valid
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return True if the vaccine appointment was valid and scheduled
     */
    public boolean scheduleVaccine(ScheduledVaccineDto scheduledVaccineDto) {
        if (!validateAppointment(scheduledVaccineDto)) return false;
        vaccinationCenter.addAppointment(createScheduledVaccine(scheduledVaccineDto));
        ScheduledVaccine.addAppointment(createScheduledVaccine(scheduledVaccineDto));
        return true;
    }

    /**
     * Validate appointment boolean.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return the boolean
     */
    public boolean validateAppointment(ScheduledVaccineDto scheduledVaccineDto) {
        if (!dataIsAllFilled(scheduledVaccineDto)) return false;
        if (!ScheduledVaccine.userIsEligibleForTheAppointment(scheduledVaccineDto)) return false;
        if (!vaccinationCenter.validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, company))
            return false;

        return vaccinationCenter.centerHasAvailability(scheduledVaccineDto);
    }

    /**
     * Checks if the logged user is a Receptionist
     *
     * @return true if the logged user is a Receptionist
     */
    public boolean loggedUserIsReceptionist() {
        return authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);
    }

    /**
     * Gets SNS users list.
     *
     * @return the list
     */
    public List<SnsUser> getSnsUsersList() {
        return company.getSNSUserList();
    }

    /**
     * Gets user phone number.
     *
     * @return the user phone number
     */
    public int getUserPhoneNumber() {
        for (SnsUser user : company.getSNSUserList()) {
            if (user.getStrEmail().equals(String.valueOf(authFacade.getCurrentUserSession().getUserId())) || authFacade.getCurrentUserSession().isLoggedInWithRole("Receptionist")) {
                return Integer.parseInt(user.getStrPhoneNumber());
            }
        }
        return 0;
    }

    /**
     * Gets sns user number.
     *
     * @return the sns user number
     */
    public int getSnsUserNumber() {
        for (SnsUser user : company.getSNSUserList()) {
            if (user.getStrEmail().equals(String.valueOf(authFacade.getCurrentUserSession().getUserId()))) {
                return user.getSnsUserNumber();
            }
        }
        return 0;
    }

    /**
     * Checks if company has the necessary info to schedule a Vaccine/Vaccination.
     *
     * @return true if all the arrays have at least one object each
     */
    public boolean companyHasNecessaryInfo() {
        if (company.getSNSUserList().isEmpty()) return false;
        if (company.getVaccineTypes().isEmpty()) return false;
        return !company.getVaccinationCenters().isEmpty();
    }

    /**
     * Verifies if a DTO has all the data for an appointment
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return True if the dto has all the attributes filled
     */
    private boolean dataIsAllFilled(ScheduledVaccineDto scheduledVaccineDto) {
        if (!Utils.validateSNSUserNumber(scheduledVaccineDto.snsNumber)) return false;

        if (scheduledVaccineDto.vaccineType == null) return false;

        return scheduledVaccineDto.date != null;
    }

    private ScheduledVaccine createScheduledVaccine(ScheduledVaccineDto scheduledVaccineDto) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        return mapper.createScheduledVaccine(scheduledVaccineDto);
    }

    /**
     * Get vaccination center info.
     *
     * @return the vaccination center dto
     */
    public VaccinationCenterDto getVaccinationCenterInfo() {
        VaccinationCenterMapper mapper = new VaccinationCenterMapper();
        return mapper.domainToDto(vaccinationCenter);
    }

    /**
     * Sets vaccination center.
     *
     * @param index the index
     */
    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index);
    }

    /**
     * Is mass vaccination center or a Healthcare center.
     *
     * @return true if it is a Mass Vaccination Center
     */
    public boolean isMassVaccinationCenter() {
        return vaccinationCenter instanceof MassVaccinationCenter;
    }

    /**
     * Gets vaccine type.
     *
     * @return the vaccine type
     */
    public ArrayList<String> getVaccineType() {
        ArrayList<String> vaccineTypes = new ArrayList<>();

        if (isMassVaccinationCenter()) {
            MassVaccinationCenter massVaccinationCenter = (MassVaccinationCenter) vaccinationCenter;
            vaccineTypes = new ArrayList<>(List.of(massVaccinationCenter.getVaccineType().toString()));
        } else {
            HealthcareCenter healthcareCenter = (HealthcareCenter) vaccinationCenter;
            int index = 0;
            for (VaccineType vacineType : healthcareCenter.getVaccineTypes()) {
                vaccineTypes.add(index, vacineType.toString());
                index++;
            }
        }
        return vaccineTypes;
    }

    /**
     * Sets vaccine type.
     *
     * @param vaccineTypeIndex the vaccine type index in the Vacine Types list
     */
    public void setVaccineType(int vaccineTypeIndex, ScheduledVaccineDto scheduledVaccineDto) {
        if (isMassVaccinationCenter()) {
            MassVaccinationCenter massVaccinationCenter = (MassVaccinationCenter) vaccinationCenter;
            scheduledVaccineDto.vaccineType = massVaccinationCenter.getVaccineType();
        } else {
            HealthcareCenter healthcareCenter = (HealthcareCenter) vaccinationCenter;
            scheduledVaccineDto.vaccineType = healthcareCenter.getVaccineTypes().get(vaccineTypeIndex);
        }
    }

    /**
     * Checks if the Day has availability .
     *
     * @return true if the Day has availability
     */
    public boolean dayHasAvailability(LocalDate date) {
        return vaccinationCenter.dayHasAvailability(date);
    }

    /**
     * Checks if the slot has availability.
     *
     * @param selectedDate  the selected date
     * @param timeOfTheSlot the time of the slot
     * @return true if the slot has availability
     */
    public boolean slotHasAvailability(LocalDate selectedDate, LocalTime timeOfTheSlot) {
        return vaccinationCenter.slotHasAvailability(selectedDate, timeOfTheSlot);
    }


    /**
     * Gets the available days in current month and fill´s a list.
     *
     * @param availableDaysCurrentMonth List that will receive the available days
     * @param localDate                 The date when the user is scheduling
     * @return the filled array list
     */
    public ArrayList<String> availableDaysListCurrentMonth(ArrayList<String> availableDaysCurrentMonth, LocalDate localDate) {
        LocalDate dateWhenScheduling = LocalDate.now();
        for (int date = dateWhenScheduling.getDayOfMonth() + 1; date <= YearMonth.of(dateWhenScheduling.getYear(), dateWhenScheduling.getMonthValue()).lengthOfMonth(); date++) {
            if (dayHasAvailability(LocalDate.of(LocalDate.now().getYear(), localDate.getMonthValue(), date))) {
                if (isMonthNumberSingleDigit(localDate.getMonthValue()))
                    availableDaysCurrentMonth.add(String.valueOf(date + "/" + localDate.getMonthValue()));
                else
                    availableDaysCurrentMonth.add(String.valueOf(date + "/ 0" + localDate.getMonthValue()));
            }
        }
        return availableDaysCurrentMonth;
    }

    /**
     * Gets the available days in next month and fill´s a list.
     *
     * @param availableDaysNextMonth List that will receive the available days
     * @return the filled array list
     */
    public ArrayList<String> availableDaysListNextMonth(ArrayList<String> availableDaysNextMonth) {
        LocalDate dateWhenScheduling = LocalDate.now();
        LocalDate nextMonthDate = dateWhenScheduling.plusMonths(1).with(TemporalAdjusters.firstDayOfMonth());
        for (int date = nextMonthDate.getDayOfMonth(); date <= YearMonth.of(nextMonthDate.getYear(), nextMonthDate.getMonthValue()).lengthOfMonth(); date++) {
            if (dayHasAvailability(LocalDate.of(LocalDate.now().getYear(), nextMonthDate.getMonthValue(), date))) {
                if (isMonthNumberSingleDigit(nextMonthDate.getMonthValue()))
                    availableDaysNextMonth.add(String.valueOf(date + "/" + nextMonthDate.getMonthValue()));
                else
                    availableDaysNextMonth.add(String.valueOf(date + "/ 0" + nextMonthDate.getMonthValue()));
            }
        }
        return availableDaysNextMonth;
    }

    private boolean isMonthNumberSingleDigit(int month) {
        return month <= Constants.SEPTEMBER;
    }

    /**
     * Gets the available hours and fill´s array list.
     *
     * @param availableHours the available hours empty list
     * @param slotsPerDay    the slots per day
     * @param selectedDate   the selected date by the user
     * @param timeOfTheSlot  the time of the slot
     * @param slotDuration   the slot duration
     * @return the array list filled with available hours
     */
    public ArrayList<LocalTime> availableHoursList(ArrayList<LocalTime> availableHours, int slotsPerDay, LocalDate selectedDate, LocalTime timeOfTheSlot, int slotDuration) {
        for (int slot = 0; slot < slotsPerDay; slot++) {
            if (slotHasAvailability(selectedDate, timeOfTheSlot))
                availableHours.add(timeOfTheSlot);

            timeOfTheSlot = timeOfTheSlot.plusMinutes(slotDuration);
        }
        return availableHours;
    }


    /**
     * Time selected by the user converted into minutes to be added.
     *
     * @param selectedOption the selected slot
     * @param slotDuration   the slot duration
     * @return the minutes to be added to the opening hour to determine the selected time by the user
     */
    public int timeSelected(int selectedOption, int slotDuration) {
        int minutesToBeAdded;
        return minutesToBeAdded = (selectedOption - 1) * slotDuration;
    }

    /**
     * Print appointment to file, since the UI layer can be replaced by an FX layer, this was the best way of acting.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @throws IOException the io exception
     */
    public void printAppointmentToFile(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenterDto vaccinationCenter) throws IOException {
        System.out.printf("-----%n|SMS|%n-----%n%n");
        int options = Utils.selectFromList(List.of(Constants.OPTIONS), "Do you want to receive an SMS with the appointment information");
        if (options == 0) {
            PrintWriter printWriter = new PrintWriter(Constants.PATH_SMS_MESSAGE);
            printWriter.printf("Received at: " + Utils.formatDateToPrint(LocalDate.now()) + "%n%nYou have an appointment to take a %s vaccine, at %s in %s, on %s.", scheduledVaccineDto.vaccineType, scheduledVaccineDto.date.toLocalTime(), Utils.formatDateToPrint(scheduledVaccineDto.date.toLocalDate()), vaccinationCenter.strName);
            printWriter.close();
            System.out.printf("%nA message with the information was sent to " + getUserPhoneNumber() + ".");
        }
    }
}