package app.miscellaneous;

import app.domain.model.Arrival;
import app.domain.model.VaccinationCenter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalyzer {

private final VaccinationCenter vaccinationCenter;


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
        int [] listToBeAnalyzed = getListToBeAnalyzed(date, timeInterval);
        results.add(listToBeAnalyzed);
        int [] maxSumSublist = findMaxSumSublist(listToBeAnalyzed);
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
    private int [] findMaxSumSublist(int [] listToBeAnalyzed ) {
        int maxSum = 0;

        int startIndex = 0;
        int endIndex = 0;

        for(int firstPositionList = 0;  firstPositionList < listToBeAnalyzed.length; firstPositionList++){

            for(int lastPositionList = firstPositionList; lastPositionList < listToBeAnalyzed.length; lastPositionList++){

                int sum = 0;
                for( int sumPosition = firstPositionList; sumPosition < lastPositionList; sumPosition++){
                    sum += listToBeAnalyzed[sumPosition];
                }

                if(sum > maxSum){
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

    private int calculateSum(int [] maxSumSublist){
        int sum = 0;
        for (int position : maxSumSublist) {
            sum += position;
        }
        return sum;
    }


    //Método que gera a lista a ser analisada aka a lista que mandei desenhada no paint pelo wpp (Guga -> Pedro)
    private int [] getListToBeAnalyzed(LocalDate date, int timeInterval){
       List <Arrival>  arrivalsList =  vaccinationCenter.getArrivalsList();

       int [] listOfArrivalsAndDepartures = new int[2]; //TODO: get the list of arrivals and departures from the vaccinationCenter

        return listOfArrivalsAndDepartures;
    }
}
