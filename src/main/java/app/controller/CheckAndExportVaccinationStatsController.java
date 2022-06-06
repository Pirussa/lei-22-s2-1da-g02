package app.controller;

import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Check and export vaccination stats controller.
 */
public class CheckAndExportVaccinationStatsController {

    /**
     * Instantiates a new Check and export vaccination stats controller.
     */
    public CheckAndExportVaccinationStatsController() {
        setCenter();
    }

    private final Company company = App.getInstance().getCompany();

    private VaccinationCenter center;

    private void setCenter(){
        String id = Utils.getLoggedCoordinatorId();
        center = getVaccinationCenterAssociatedToCoordinator(id);
    }

    /**
     * Gets a list with the total of fully vaccinated people per day (each day has a total) .
     *
     * @return the vaccination stats
     */
    private List<String> getVaccinationStatsList() {
        List<String> vaccinationStats = new ArrayList<>();
        List<VaccineBulletin> listFullyVaccinated= center.getVaccinesAdministeredList();
        LocalDate lastDay = getFirstDateAvailable(listFullyVaccinated);
        int total = 0;
        for (VaccineBulletin vaccineBulletin: listFullyVaccinated) {
            if (!vaccineBulletin.getDateTimeOfLastDose().toLocalDate().equals(lastDay)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(lastDay).append(";").append(total);
                String statsOfOneDay = stringBuilder.toString();
                vaccinationStats.add(statsOfOneDay);
                lastDay = vaccineBulletin.getDateTimeOfLastDose().toLocalDate();
            }
            total++;
        }

        return vaccinationStats;
    }

    public List<String>getVaccinationStatsListBetweenDates(LocalDate firstDate, LocalDate lastDate){
        List<String> dailyStats = getVaccinationStatsList();
        List<String> statsBetweenDates = new ArrayList<>();

        for (String dailyStat: dailyStats) {
            String[] dailyStatArray = dailyStat.split(";");
            LocalDate date = LocalDate.parse(dailyStatArray[0]);
            if (date.isAfter(firstDate) && date.isBefore(lastDate)) {
                statsBetweenDates.add(dailyStat);
            }
        }
        return statsBetweenDates;

    }

    private LocalDate getFirstDateAvailable(List<VaccineBulletin> listFullyVaccinated) {
        return listFullyVaccinated.get(0).getDateTimeOfLastDose().toLocalDate();
    }

    /**
     * Export vaccination stats boolean.
     *
     * @return true if the export was done successfully
     */

    public boolean exportVaccinationStats(String fileName,LocalDate firstDate, LocalDate lastDate) {
        fileName = fileName + ".csv";
        File file = new File(fileName);
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(file);
            writer.format("%s;%s\n", "Date", "Total");
            for (String stat: getVaccinationStatsListBetweenDates(firstDate,lastDate)) {
                writer.format("%s\n", stat);
            }

        } catch (Exception FileNotFoundException) {
            return false;
        } finally {
            assert writer != null;
            writer.close();
        }

        return true;
    }

    /**
     * Check if dates are valid.
     *
     * @param firstDate the first date
     * @param lastDate  the last date
     * @return an int related to the outcome
     */
    public int checkIfDatesAreValid(LocalDate firstDate, LocalDate lastDate) {
        int errorCode = 0;
        if (firstDate == null || lastDate == null) {
             errorCode = 1;
        }
        if (firstDate.isAfter(lastDate)) {
             errorCode = 2;
        }

        if (firstDate.isBefore(LocalDate.of(2021, 1, 1))) {
             errorCode = 3;
        }

        if (lastDate.isAfter(LocalDate.now()) ) {
             errorCode = 4;

        }
        return errorCode;
    }

    private VaccinationCenter getVaccinationCenterAssociatedToCoordinator(String coordinatorId) {
        if (company.getVaccinationCenterAssociatedToCoordinator(coordinatorId) != null ) {
            return company.getVaccinationCenterAssociatedToCoordinator(coordinatorId);
        }
        return null;
    }

}




