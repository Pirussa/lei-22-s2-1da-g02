package app.controller;

import app.domain.model.*;
import app.stores.VaccinationCentersStore;
import app.ui.console.utils.Utils;

import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Check and export vaccination stats controller.
 */
public class CheckAndExportVaccinationStatsController {

    private final VaccinationCenter center;


    /**
     * Instantiates a new Check and export vaccination stats controller.
     */
    public CheckAndExportVaccinationStatsController() {
        final Company company = App.getInstance().getCompany();
        final VaccinationCentersStore store = company.getVaccinationCentersStore();
        String id = Utils.getLoggedCoordinatorId();
        center = store.getVaccinationCenterAssociatedToCoordinator(id);
    }


    /**
     * Check if dates are valid.
     *
     * @param firstDate the first date
     * @param lastDate  the last date
     * @return an int related to the outcome
     */
    public int checkIfDatesAreValid(LocalDate firstDate, LocalDate lastDate) {
        if (firstDate == null || lastDate == null)
            return 1;
        if (firstDate.isAfter(lastDate))
            return 2;
        if (firstDate.isBefore(LocalDate.of(2021, 1, 1)))
            return 3;
        if (lastDate.isAfter(LocalDate.now()))
            return 4;
        return 0;
    }



    public boolean exportVaccinationStats(String fileName, LocalDate firstDate, LocalDate lastDate) {
        return center.exportVaccinationStats(fileName, firstDate, lastDate);
    }

    public List<String> getVaccinationStatsListBetweenDates(LocalDate firstDate, LocalDate lastDate) {
       return center.getVaccinationStatsListBetweenDates(firstDate, lastDate);
    }
}




