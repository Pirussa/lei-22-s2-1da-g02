package app.domain.model;

import app.controller.App;
import app.domain.shared.Constants;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class VaccinationCenterScheduledVaccinations {

    private String name;

    private String openingHours;

    private String closingHours;

    private int[][] scheduledVaccinations;

    private static Company company = App.getInstance().getCompany();

    public VaccinationCenterScheduledVaccinations(String name, String openingHours, String closingHours, int[][] scheduledVaccinations) {
        this.name = name;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.scheduledVaccinations = scheduledVaccinations;
    }

    public int[][] getScheduledVaccinations() {
        return scheduledVaccinations;
    }

    public void setScheduledVaccinations(int[][] scheduledVaccinations) {
        this.scheduledVaccinations = scheduledVaccinations;
    }

    public static int[][] createArrayForVaccinationScheduling (String openingHours, String closingHours) {
        int numberOfTimeSlots = Integer.parseInt(closingHours) - Integer.parseInt(openingHours);
        return new int[Constants.NUMBER_OF_DAYS_FOR_VACCINATION_SCHEDULE][numberOfTimeSlots];
    }
}