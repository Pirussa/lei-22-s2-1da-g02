package app.miscellaneous;

import app.domain.model.Arrival;
import app.domain.model.Departure;
import app.domain.model.VaccinationCenter;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalyzer {

    private final VaccinationCenter vaccinationCenter;

    private int[] listToBeAnalyzed;
    private int[] maxSumSubList;


    public PerformanceAnalyzer(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }



    public int[] getTheStatisticsDailyList(LocalDate date, int timeInterval) {
        listToBeAnalyzed = getListToBeAnalyzed(date, timeInterval);
        return listToBeAnalyzed;
    }

    public int[] getMaxSumSubList() {
        maxSumSubList = findMaxSumSublist(listToBeAnalyzed);
        return maxSumSubList;
    }

    public int getMaxSum() {
        return calculateSum(maxSumSubList);
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
                for (int sumPosition = firstPositionList; sumPosition <= lastPositionList; sumPosition++) {
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
        int position = 0;
        if (maxSubList.length != 1){
            while (index <= endIndex) {
                maxSubList[position] = listToBeAnalyzed[index];
                position++;
                index++;
            }
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

        int[] listOfArrivalsAndDepartures = new int[getLengthOfList(timeInterval)];
        int counterArrivals =0;
        int counterDepartures =0;
        for (int position = 0; position < listOfArrivalsAndDepartures.length; position++) {
            int[] arrivalsAndDepartures = countArrivalsAndDepartures(date, timeInterval, position);
            listOfArrivalsAndDepartures[position] = arrivalsAndDepartures[0] - arrivalsAndDepartures[1];
            counterArrivals += arrivalsAndDepartures[0];
            counterDepartures+= arrivalsAndDepartures[1];

        }
        System.out.println(counterArrivals);
        System.out.println(counterDepartures);
        return listOfArrivalsAndDepartures;
    }

    private int getLengthOfList(int timeInterval) {


        return getMinutesOpenCenterPerDay() / timeInterval;
    }

    public int getMinutesOpenCenterPerDay(){
        int openingHour = Integer.parseInt(vaccinationCenter.getStrOpeningHour());
        int closingHour = Integer.parseInt(vaccinationCenter.getStrClosingHour());
        return (closingHour - openingHour) * 60;
    }

    public List<String> getTimeIntervals(int timeInterval) {
        List<String> timeIntervals = new ArrayList<>();
        LocalDateTime date = LocalDateTime.of(2022, 1, 1, Integer.parseInt(vaccinationCenter.getStrOpeningHour()), 0);

        for (int position = 0; position < getLengthOfList(timeInterval); position++) {
            StringBuilder stringBuilder = new StringBuilder();
            if (date.getHour() < 10)
                stringBuilder.append("0").append(date.getHour());
            else
                stringBuilder.append(date.getHour());

            stringBuilder.append(":");

            if (date.getMinute() < 10)
                stringBuilder.append("0").append(date.getMinute());
            else
                stringBuilder.append(date.getMinute());

            timeIntervals.add(stringBuilder.toString());
            date = date.plusMinutes(timeInterval);
        }

        return timeIntervals;
    }

    private int[] countArrivalsAndDepartures(LocalDate date, int timeInterval, int slot) {
        int counterArrivals = 0;
        int counterDepartures = 0;

        LocalDateTime time = LocalDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), Integer.parseInt(vaccinationCenter.getStrOpeningHour()), 0);
        LocalDateTime beginningSlot;
        LocalDateTime endSlot;

        if (slot == 0)
            beginningSlot = time;
        else
            beginningSlot = time.plusMinutes((long) timeInterval * slot);
        endSlot = time.plusMinutes((long) timeInterval * (slot + 1));

        for (Arrival arrival : vaccinationCenter.getArrivalsList())
            if ((arrival.getArrivalTime().isAfter(beginningSlot) || arrival.getArrivalTime().isEqual(beginningSlot)) && (arrival.getArrivalTime().isBefore(endSlot) ))
                counterArrivals++;

        for (Departure departure : vaccinationCenter.getDeparturesList())
            if ((departure.getDepartureTime().isAfter(beginningSlot) || departure.getDepartureTime().isEqual(beginningSlot)) && departure.getDepartureTime().isBefore(endSlot))
                counterDepartures++;

        return new int[] {counterArrivals, counterDepartures};
    }

}
