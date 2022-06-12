package app.ui.console;

import app.controller.App;
import app.controller.DataFromLegacySystemController;
import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.miscellaneous.ReadLegacyDataFile;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class DataFromLegacySystemUI implements Runnable {
    private final DataFromLegacySystemController controller = new DataFromLegacySystemController();
    private List<String> sortedList;

    public void run() {
        try {
            Scanner readPath = new Scanner(System.in);
            System.out.println();
            System.out.println("File Location: ");
            System.out.println();
            String path = readPath.nextLine();
            controller.readFile(path);
            if (controller.updateFile()) {
                System.out.println("File updated successfully");
            } else {
                System.out.println("File update failed");
            }
            chooseCriteriaToSort();
            sortListWithAlgo();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseCriteriaToSort() {
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
                controller.setList(ArrivalOption);
                break;
            case 1:
                controller.setList(LeaveOption);
                break;
            case 2:
        }
    }

    public void sortListWithAlgo() {
        String algorithmToBeUsed = App.getInstance().getSortingAlgorithm();

        System.out.printf("%nAlgorithm in config file: " + algorithmToBeUsed);
        switch (algorithmToBeUsed) {
            case "HeapSort":
                Scanner scanner = new Scanner(System.in);
                System.out.printf("%nChoose the way you want to sort.%n0 - Ascending%n1 - Descending%n2 - Back to Menu%n");
                int optionOne = scanner.nextInt();
                switch (optionOne) {
                    case 0:
                         sortedList = controller.heapAscending();

                        break;
                    case 1:
                        sortedList = controller.heapDescending();

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
                       sortedList = controller.mergeAscending(controller.getListToSort(), 0, controller.getListToSort().size() - 1);
                        break;
                    case 1:
                        sortedList =  controller.mergeDescending(controller.getListToSort(), 0, controller.getListToSort().size() - 1);
                        break;
                    case 2:
                        break;
                }
                break;
        }

    }

    public void printSortedArray(List<String> sortedList) {
        System.out.println("Sorted Array:");
        for (String line : sortedList) {
            System.out.println(line);
        }
    }

}
