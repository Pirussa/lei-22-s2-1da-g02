package app.miscellaneous;

import app.controller.App;
import app.controller.DataFromLegacySystemController;
import app.domain.model.Company;
import app.ui.console.CenterCoordinatorUI;
import app.ui.console.MainMenuUI;
import app.ui.console.utils.Utils;
import com.sun.scenario.effect.Merge;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadLegacyDataFile {

    private final DataFromLegacySystemController ctrl = new DataFromLegacySystemController();

    private List<String> csvLegacyData = new ArrayList<>();
    private List<LocalDateTime> listOfArrivalDates = new ArrayList<>();

    public boolean readFile(String path) throws Exception {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            csvLegacyData.add(values[0] + "|" + values[1] + "|" + values[2] + "|" + values[3] + "|" + values[4] + "|" + values[5] + "|"
                    + values[6] + "|" + values[7]);
        }
        return true;
    }

    public void sortListWithAlgo() {
        String algorithmToBeUsed = App.getInstance().getSortingAlgorithm();
        System.out.println();
        System.out.println("Algo in config file: "+algorithmToBeUsed);
        switch (algorithmToBeUsed) {
            case "BubbleSort":
                Scanner scOne = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose the way you want to sort.");
                System.out.println("0 - Ascending");
                System.out.println("1 - Descending");
                System.out.println("2 - Back to Menu");
                int optionOne = scOne.nextInt();
                switch (optionOne) {
                    case 0:
                        bubbleSortAscending();
                        break;
                    case 1:
                        bubbleSortDescending();
                        break;
                    case 2:
                        break;
                }
                break;
            case "MergeSort":
                Scanner scTwo = new Scanner(System.in);
                System.out.println();
                System.out.println("Choose the way you want to sort.");
                System.out.println("0 - Ascending");
                System.out.println("1 - Descending");
                System.out.println("2 - Back to Menu");
                int optionTwo = scTwo.nextInt();
                switch (optionTwo) {
                    case 0:
                        mergeSortAscending(listOfArrivalDates, 0, listOfArrivalDates.size() - 1);
                        break;
                    case 1:
                        mergeSortDescending(listOfArrivalDates, 0, listOfArrivalDates.size() - 1);
                        break;
                    case 2:
                        break;
                }
                break;
        }

    }


    public void updateLegacyFile() throws NotSerializableException {
        if (!ctrl.getSNSUserList().isEmpty() && !ctrl.getVaccines().isEmpty()) {
            for (int i = 0; i < csvLegacyData.size(); i++) {
                String[] values;
                float percentage = (float) i * 100 / csvLegacyData.size();
                boolean flag;
                int j;
                int k;

                System.out.printf("\n%.1f%% complete...", percentage);
                values = csvLegacyData.get(i).split("\\|");

                for (j = 0; j < ctrl.getSNSUserList().size(); j++) {
                    if (ctrl.getSNSUserList().get(j).getSnsUserNumber() == Integer.parseInt(values[0])) {
                        break;
                    }
                }
                csvLegacyData.set(i, ctrl.getSNSUserList().get(j).getStrName() + "|" + csvLegacyData.get(i));

                for (k = 0; k < ctrl.getVaccines().size(); k++) {
                    if (ctrl.getVaccines().get(k).getName().equals(values[1])) {
                        break;
                    }
                }
                csvLegacyData.set(i, csvLegacyData.get(i) + "|" + ctrl.getVaccines().get(k).getVaccineType().getDescription());
            }
            ctrl.exportDataToFile(csvLegacyData);
        } else {
            System.out.println("Either SNS User list is empty or Vaccine's list is.");
        }
    }

    public void printUpdatedLegacy(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        System.out.println();
        System.out.println(list.size() + " " + "entries.");
    }

    void mergeSortAscending(List<LocalDateTime> list, int begin, int end) {

        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortAscending(list, begin, middle); //call Merge Sort on the first half
            mergeSortAscending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalDateTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int i = begin, j = middle + 1;
            while (i <= middle && j <= end) {
                if (list.get(j).compareTo(list.get(i)) > 0) {
                    int x = i;
                    newlist.add(list.get(i++));
                    tempList.add(csvLegacyData.get(x++));
                } else {
                    int z = j;
                    newlist.add(list.get(j++));
                    tempList.add(csvLegacyData.get(z++));
                }
            }
            while (i <= middle) {
                int t = i;
                newlist.add(list.get(i++));

                tempList.add(csvLegacyData.get(t++));
            }
            while (j <= end) {
                int r = j;
                newlist.add(list.get(j++));
                tempList.add(csvLegacyData.get(r++));
            }
            i = begin;
            int k = i;
            for (LocalDateTime item : newlist) {
                list.set(i++, item);
            }

            for (String strItem : tempList) {
                csvLegacyData.set(k++, strItem);
            }
            //printUpdatedLegacy(csvLegacyData);
            writeArrayToFile(csvLegacyData);
        }
    }

    public void mergeSortDescending(List<LocalDateTime> list, int begin, int end) {
        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortDescending(list, begin, middle); //call Merge Sort on the first half
            mergeSortDescending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalDateTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int i = begin, j = middle + 1;
            while (i <= middle && j <= end) {
                if (list.get(j).compareTo(list.get(i)) < 0) {
                    int x = i;
                    newlist.add(list.get(i++));
                    tempList.add(csvLegacyData.get(x++));
                } else {
                    int z = j;
                    newlist.add(list.get(j++));
                    tempList.add(csvLegacyData.get(z++));
                }
            }
            while (i <= middle) {
                int t = i;
                newlist.add(list.get(i++));

                tempList.add(csvLegacyData.get(t++));
            }
            while (j <= end) {
                int r = j;
                newlist.add(list.get(j++));
                tempList.add(csvLegacyData.get(r++));
            }
            i = begin;
            int k = i;
            for (LocalDateTime item : newlist) {
                list.set(i++, item);
            }

            for (String strItem : tempList) {
                csvLegacyData.set(k++, strItem);
            }
            //printUpdatedLegacy(csvLegacyData);
            writeArrayToFile(csvLegacyData);
        }
    }

    public void bubbleSortAscending() {

        for (int i = 0; i < listOfArrivalDates.size() - 1; ++i) {

            for (int j = 0; j < listOfArrivalDates.size() - i - 1; ++j) {

                if (listOfArrivalDates.get(j + 1).isBefore(listOfArrivalDates.get(j))) {

                    LocalDateTime swap = listOfArrivalDates.get(j);
                    listOfArrivalDates.set(j, listOfArrivalDates.get(j + 1));
                    listOfArrivalDates.set(j + 1, swap);

                    String swapString = csvLegacyData.get(j);
                    csvLegacyData.set(j, csvLegacyData.get(j + 1));
                    csvLegacyData.set(j + 1, swapString);
                }
            }
        }
        //printUpdatedLegacy(csvLegacyData);
        writeArrayToFile(csvLegacyData);
    }


    public void bubbleSortDescending() {

        for (int i = 0; i < listOfArrivalDates.size() - 1; ++i) {

            for (int j = 0; j < listOfArrivalDates.size() - i - 1; ++j) {

                if (listOfArrivalDates.get(j + 1).isAfter(listOfArrivalDates.get(j))) {

                    LocalDateTime swap = listOfArrivalDates.get(j);
                    listOfArrivalDates.set(j, listOfArrivalDates.get(j + 1));
                    listOfArrivalDates.set(j + 1, swap);

                    String swapString = csvLegacyData.get(j);
                    csvLegacyData.set(j, csvLegacyData.get(j + 1));
                    csvLegacyData.set(j + 1, swapString);
                }
            }
        }
        //printUpdatedLegacy(csvLegacyData);
        writeArrayToFile(csvLegacyData);
    }


    public boolean choosePositionToSort() {
        Scanner scPos = new Scanner(System.in);
        final int ArrivalOption = 6;
        final int LeaveOption = 8;
        System.out.println();
        System.out.println("Choose the option you want to sort.");
        System.out.println("0 - Arrival Date Time");
        System.out.println("1 - Leaving Date Time");
        System.out.println("2 - Back to Menu");
        int optionPosition = scPos.nextInt();
        switch (optionPosition) {
            case 0:
                setList(ArrivalOption);
                break;
            case 1:
                setList(LeaveOption);
                break;
            case 2:
                return false;
        }
        return true;
    }


    public void setList(int position) {
        String[] values;
        listOfArrivalDates.clear();
        for (int i = 0; i < csvLegacyData.size(); i++) {
            values = csvLegacyData.get(i).split("\\|");
            String date = values[position];
            String[] dateAndHour = date.split(" ");
            String[] dayMonthYear = dateAndHour[0].split("/");
            int year = Integer.parseInt(dayMonthYear[2]);
            int month = Integer.parseInt(dayMonthYear[0]);
            int day = Integer.parseInt(dayMonthYear[1]);
            String[] hourAndMinute = dateAndHour[1].split(":");
            int hour = Integer.parseInt(hourAndMinute[0]);
            int minute = Integer.parseInt(hourAndMinute[1]);

            LocalDateTime dataToBeAdded = LocalDateTime.of(year, month, day, hour, minute);
            listOfArrivalDates.add(dataToBeAdded);
        }
    }

    public void writeArrayToFile(List<String> list){
        FileWriter writer = null;
        System.out.println("Writing to file...");
        try {
            writer = new FileWriter("SortingTest.txt");
            for(String str: list) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
