package app.miscellaneous;

import app.controller.App;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReadLegacyDataFile {

    private List<String> csvLegacyData = new ArrayList<>();

    private List<LocalDateTime> listOfArrivalDates = new ArrayList<>();

    public void readFile(String path) throws Exception {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            csvLegacyData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
                    + values[6] + "_" + values[7]);
        }
    }

    public void sortListByArrivalTime() {
        setArrivalDateList();
        String algorithmToBeUsed = App.getInstance().getSortingAlgorithm();
        switch (algorithmToBeUsed) {
            case "BubbleSort":
                bubbleSort();
                break;
            case "OtherSort":
                System.out.println("Not implemented yet");
                break;
        }


    }

    public void bubbleSort() {

        for (int numberToBeChecked = 0; numberToBeChecked < listOfArrivalDates.size() - 1; ++numberToBeChecked) {

            for (int otherElements = 0; otherElements < listOfArrivalDates.size() - numberToBeChecked - 1; ++otherElements) {

                if (listOfArrivalDates.get(otherElements + 1).isAfter(listOfArrivalDates.get(otherElements))) {

                    LocalDateTime swap = listOfArrivalDates.get(otherElements);
                    //listOfArrivalDates.get(otherElements) = listOfArrivalDates.get(otherElements + 1);
                    //listOfArrivalDates.get(otherElements + 1) = swap;

                    String swapString = csvLegacyData.get(otherElements);

                }
            }
        }


    }


    public void setArrivalDateList() {
        String[] values;
        for (String csvLegacyDatum : csvLegacyData) {
            values = csvLegacyDatum.split("_");
            String date = values[6];
            String[] dateAndHour = date.split(" ");
            String[] dayMonthYear = dateAndHour[0].split("/");
            int year = Integer.parseInt(dayMonthYear[2]);
            int month = Integer.parseInt(dayMonthYear[1]);
            int day = Integer.parseInt(dayMonthYear[0]);
            String[] hourAndMinute = dateAndHour[1].split(":");
            int hour = Integer.parseInt(hourAndMinute[0]);
            int minute = Integer.parseInt(hourAndMinute[1]);

            LocalDateTime dataToBeAdded = LocalDateTime.of(year, month, day, hour, minute);
            listOfArrivalDates.add(dataToBeAdded);
        }
    }


}
