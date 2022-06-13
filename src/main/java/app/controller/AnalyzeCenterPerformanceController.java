package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.miscellaneous.PerformanceAnalyzer;
import app.stores.VaccinationCentersStore;
import app.ui.console.utils.Utils;

import java.time.LocalDate;
import java.util.List;

/**
 *  Analyze center performance controller.
 */
public class AnalyzeCenterPerformanceController {

    private final VaccinationCenter center;
    private LocalDate selectedDate;
    private int timeInterval;


    /**
     * Sets time interval.
     *
     * @param timeInterval the time interval to divide the list in
     */
    public void setTimeInterval(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    /**
     * Sets selected date.
     *
     * @param selectedDate the selected date
     */
    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    /**
     * Instantiates a new controller.
     */
    public AnalyzeCenterPerformanceController( ) {
        final Company company = App.getInstance().getCompany();
        final VaccinationCentersStore store = company.getVaccinationCentersStore();
        String id = Utils.getLoggedCoordinatorId();
        center = store.getVaccinationCenterAssociatedToCoordinator(id);
    }


    /**
     * Analyzes center performance for a day.
     *
     * @return a list with all the relevant information about the center performance
     */
    public List<Object> analyzeCenterPerformanceForDay(){
        PerformanceAnalyzer analyzer = new PerformanceAnalyzer(center);
        return analyzer.analyzeCenterPerformanceForDay(selectedDate, timeInterval);
    }



}
