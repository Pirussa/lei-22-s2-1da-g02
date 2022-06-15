package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.miscellaneous.PerformanceAnalyzer;
import app.stores.VaccinationCentersStore;
import app.ui.console.utils.Utils;

import java.time.LocalDate;
import java.util.List;

/**
 * Analyze center performance controller.
 */
public class AnalyzeCenterPerformanceController {

    private final VaccinationCenter center;
    private LocalDate selectedDate;
    private int timeInterval;

    private final PerformanceAnalyzer analyzer;


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
    public AnalyzeCenterPerformanceController() {
        final Company company = App.getInstance().getCompany();
        final VaccinationCentersStore store = company.getVaccinationCentersStore();
        String id = Utils.getLoggedCoordinatorId();
        center = store.getVaccinationCenterAssociatedToCoordinator(id);
        analyzer = new PerformanceAnalyzer(center);
    }


    public int[] getTheStatisticsDailyList() {
        return analyzer.getTheStatisticsDailyList(selectedDate, timeInterval);
    }

    public List<String> getTimeIntervals() {
        return analyzer.getTimeIntervals(timeInterval);
    }

    public int[] getMaxSumSubList() {
        return analyzer.getMaxSumSubList();
    }

    public int getMaxSum() {
        return analyzer.getMaxSum();
    }

    public boolean checkIfTimeIntervalIsValid(String timeInterval) {

        if (timeInterval.isEmpty()) {
            return false;
        }
        try {
            int timeIntervalInt = Integer.parseInt(timeInterval);
            if (!(timeIntervalInt > 0 && timeIntervalInt <= analyzer.getMinutesOpenCenterPerDay())) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


}
