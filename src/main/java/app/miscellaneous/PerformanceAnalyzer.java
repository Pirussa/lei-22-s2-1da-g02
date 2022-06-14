package app.miscellaneous;

import app.domain.model.Arrival;
import app.domain.model.Departure;
import app.domain.model.VaccinationCenter;
import app.stores.DepartureStore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalyzer {

    private final VaccinationCenter vaccinationCenter;

    private DepartureStore departureStore;

    public PerformanceAnalyzer(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }


    /**
     * Analyzes center performance for a day.
     *
     * @param date         the date
     * @param timeInterval the time interval
     * @return a list with all the relevant information about the center performance
     */
    public List<Object> analyzeCenterPerformanceForDay(LocalDate date, int timeInterval) {
        List<Object> results = new ArrayList<>();
        int[] listToBeAnalyzed = getListToBeAnalyzed(date, timeInterval);
        results.add(listToBeAnalyzed);
        int[] maxSumSublist = findMaxSumSublist(listToBeAnalyzed);
        results.add(maxSumSublist);
        int maxSum = calculateSum(maxSumSublist);
        results.add(maxSum);

        return results;
    }


    /**
     * Find the sublist with the maximum sum.
     *
     * @param listToBeAnalyzed the list to be analyzed
     * @return the sublist with the maximum sum
     */
    private int[] findMaxSumSublist(int[] listToBeAnalyzed) {
        int maxSum = 0;

        int startIndex = 0;
        int endIndex = 0;

        for (int firstPositionList = 0; firstPositionList < listToBeAnalyzed.length; firstPositionList++) {

            for (int lastPositionList = firstPositionList; lastPositionList < listToBeAnalyzed.length; lastPositionList++) {

                int sum = 0;
                for (int sumPosition = firstPositionList; sumPosition < lastPositionList; sumPosition++) {
                    sum += listToBeAnalyzed[sumPosition];
                }

                if (sum > maxSum) {
                    maxSum = sum;
                    startIndex = firstPositionList;
                    endIndex = lastPositionList;
                }


            }
        }

        int[] maxSubList = new int[endIndex - startIndex + 1];

        int index = startIndex;

        while (index < endIndex) {
            maxSubList[index] = listToBeAnalyzed[index];
            index++;
        }

        return maxSubList;
    }

    private int calculateSum(int[] maxSumSublist) {
        int sum = 0;
        for (int position : maxSumSublist) {
            sum += position;
        }
        return sum;
    }


    //Método que gera a lista a ser analisada aka a lista que mandei desenhada no paint pelo wpp (Guga -> Pedro)
    private int[] getListToBeAnalyzed(LocalDate date, int timeInterval) {
        List<Arrival> arrivalsList = vaccinationCenter.getArrivalsList();
        List<Departure> departuresList = departureStore.getDeparturesList();

        int[] listOfArrivalsAndDepartures = new int[getLengthOfList(timeInterval)];

        for (int position = 0; position < listOfArrivalsAndDepartures.length; position++) {
            int[] arrivalsAndDepartures = countArrivalsAndDepartures(date, timeInterval, position);
            listOfArrivalsAndDepartures[position] = arrivalsAndDepartures[0] - arrivalsAndDepartures[1];
        }

        return listOfArrivalsAndDepartures;
    }

    private int getLengthOfList(int timeInterval) {
        int openingHour = Integer.parseInt(vaccinationCenter.getStrOpeningHour());
        int closingHour = Integer.parseInt(vaccinationCenter.getStrClosingHour());
        int minsOfTheDay = (closingHour - openingHour) * 60;

        return minsOfTheDay / timeInterval;
    }

    private int[] countArrivalsAndDepartures(LocalDate date, int timeInterval, int slot) {
        int counterArrivals = 0;
        int counterDepartures = 0;

        LocalDateTime time = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(vaccinationCenter.getStrOpeningHour()), 0);
        LocalDateTime beginningSlot;
        LocalDateTime endSlot;

        if (slot == 1)
            beginningSlot = time;
        else
            beginningSlot = time.plusMinutes((long) timeInterval * slot);
        endSlot = time.plusMinutes((long) timeInterval * (slot + 1));

        for (Arrival arrival : vaccinationCenter.getArrivalsList())
            if (arrival.getArrivalTime().isAfter(beginningSlot) && arrival.getArrivalTime().isBefore(endSlot))
                counterArrivals++;

        for (Departure departure : departureStore.getDeparturesList())
            if (departure.getDepartureTime().isAfter(beginningSlot) && departure.getDepartureTime().isBefore(endSlot))
                counterDepartures++;

        return new int[] {counterArrivals, counterDepartures};
    }

}
