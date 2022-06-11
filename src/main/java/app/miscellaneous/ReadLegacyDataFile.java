package app.miscellaneous;

import app.controller.App;
import app.controller.DataFromLegacySystemController;
import app.domain.model.Company;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadLegacyDataFile {


    //private final DataFromLegacySystemController ctrl = new DataFromLegacySystemController();
    private final Company company = App.getInstance().getCompany();


    public final List<String> legacyDataList = new ArrayList<>();
    public final List<LocalTime> listToSort = new ArrayList<>();

    public void readFile(String path) throws Exception {
        String line;
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            legacyDataList.add(values[0] + "|" + values[1] + "|" + values[2] + "|" + values[3] + "|" + values[4] + "|" + values[5] + "|"
                    + values[6] + "|" + values[7]);
        }
    }

    public void updateLegacyFile() throws NotSerializableException {
        if (!company.getSnsUserList().isEmpty() && !company.getVaccinesList().isEmpty()) {
            for (int i = 0; i < legacyDataList.size(); i++) {
                String[] values;
                float percentage = (float) i * 100 / legacyDataList.size();
                int j;
                int k;

                System.out.printf("\n%.1f%% complete...", percentage);
                values = legacyDataList.get(i).split("\\|");

                for (j = 0; j < company.getSnsUserList().size(); j++) {
                    if (company.getSnsUserList().get(j).getSnsUserNumber() == Integer.parseInt(values[0])) {
                        break;
                    }
                }
                legacyDataList.set(i, company.getSnsUserList().get(j).getStrName() + "|" + legacyDataList.get(i));

                for (k = 0; k < company.getVaccinesList().size(); k++) {
                    if (company.getVaccinesList().get(k).getName().equals(values[1])) {
                        break;
                    }
                }
                legacyDataList.set(i, legacyDataList.get(i) + "|" + company.getVaccinesList().get(k).getVaccineType().getDescription());
            }
            //ctrl.exportDataToFile(legacyDataList);
        } else {
            System.out.println("Either SNS User list is empty or Vaccine's list is.");
        }
    }

    public void printUpdatedLegacy(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
        System.out.println();
        System.out.println(list.size() + " " + "entries.");
    }

    public void mergeSortAscending(List<LocalTime> list, int begin, int end) {

        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortAscending(list, begin, middle); //call Merge Sort on the first half
            mergeSortAscending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int i = begin, j = middle + 1;
            while (i <= middle && j <= end) {
                if (list.get(j).compareTo(list.get(i)) > 0) {
                    int x = i;
                    newlist.add(list.get(i++));
                    tempList.add(legacyDataList.get(x++));
                } else {
                    int z = j;
                    newlist.add(list.get(j++));
                    tempList.add(legacyDataList.get(z++));
                }
            }
            while (i <= middle) {
                int t = i;
                newlist.add(list.get(i++));

                tempList.add(legacyDataList.get(t++));
            }
            while (j <= end) {
                int r = j;
                newlist.add(list.get(j++));
                tempList.add(legacyDataList.get(r++));
            }
            i = begin;
            int k = i;
            for (LocalTime item : newlist) {
                list.set(i++, item);
            }

            for (String strItem : tempList) {
                legacyDataList.set(k++, strItem);
            }

            System.out.println(legacyDataList);
            writeArrayToFile(legacyDataList);
        }
    }

    public void mergeSortDescending(List<LocalTime> list, int begin, int end) {
        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortDescending(list, begin, middle); //call Merge Sort on the first half
            mergeSortDescending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int i = begin, j = middle + 1;
            while (i <= middle && j <= end) {
                if (list.get(j).compareTo(list.get(i)) < 0) {
                    int x = i;
                    newlist.add(list.get(i++));
                    tempList.add(legacyDataList.get(x++));
                } else {
                    int z = j;
                    newlist.add(list.get(j++));
                    tempList.add(legacyDataList.get(z++));
                }
            }
            while (i <= middle) {
                int t = i;
                newlist.add(list.get(i++));

                tempList.add(legacyDataList.get(t++));
            }
            while (j <= end) {
                int r = j;
                newlist.add(list.get(j++));
                tempList.add(legacyDataList.get(r++));
            }
            i = begin;
            int k = i;
            for (LocalTime item : newlist) {
                list.set(i++, item);
            }

            for (String strItem : tempList) {
                legacyDataList.set(k++, strItem);
            }
            printUpdatedLegacy(legacyDataList);
            writeArrayToFile(legacyDataList);
        }
    }

    public void setList(int position) {
        String[] values;
        listToSort.clear();
        for (String line : legacyDataList) {
            values = line.split("\\|");
            String date = values[position];
            String[] dateAndHour = date.split(" ");
            String[] hourAndMinute = dateAndHour[1].split(":");
            int hour = Integer.parseInt(hourAndMinute[0]);
            int minute = Integer.parseInt(hourAndMinute[1]);

            LocalTime dataToBeAdded = LocalTime.of(hour, minute);
            listToSort.add(dataToBeAdded);
        }
    }

    public void writeArrayToFile(List<String> list) {
        FileWriter writer = null;
        System.out.println("Writing to file...");
        try {
            writer = new FileWriter("SortingTest.txt");
            for (String str : list) {
                writer.write(str + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void heapSortAscending() {
        int length = listToSort.size();

        // Build the heap the first time
        for (int lineOfTheHeap = length / 2 - 1; lineOfTheHeap >= 0; lineOfTheHeap--) {
            heapifyAscending(listToSort, length, lineOfTheHeap);
        }


        for (int position = length - 1; position >= 0; position--) {
            LocalTime temp = listToSort.get(0);
            listToSort.set(0, listToSort.get(position));
            listToSort.set(position, temp);

            String temp2 = legacyDataList.get(0);
            legacyDataList.set(0, legacyDataList.get(position));
            legacyDataList.set(position, temp2);


            heapifyAscending(listToSort, position, 0);
        }
    }

    private void heapifyAscending(List<LocalTime> listToSort, int length, int lineOfTheHeap) {

        int latest = lineOfTheHeap;
        int leftChild = 2 * lineOfTheHeap + 1;
        int rightChild = 2 * lineOfTheHeap + 2;

        // If the left child is later (later) than the root
        if (leftChild < length && listToSort.get(leftChild).isAfter(listToSort.get(latest))) {
            latest = leftChild;
        }

        // If the right child is bigger (later) than the now latest
        if (rightChild < length && listToSort.get(rightChild).isAfter(listToSort.get(latest))) {
            latest = rightChild;
        }

        // If the root is not the latest
        if (latest != lineOfTheHeap) {
            LocalTime temp = listToSort.get(latest);
            listToSort.set(latest, listToSort.get(lineOfTheHeap));
            listToSort.set(lineOfTheHeap, temp);
            String temp2 = legacyDataList.get(latest);
            legacyDataList.set(latest, legacyDataList.get(lineOfTheHeap));
            legacyDataList.set(lineOfTheHeap, temp2);

            // Recursive call to heapify until the root is the latest
            heapifyAscending(listToSort, length, latest);
        }
    }

    public void heapSortDescending() {

        int length = listToSort.size();

        // Build the heap the first time
        for (int lineOfTheHeap = length / 2 - 1; lineOfTheHeap >= 0; lineOfTheHeap--) {
            heapifyDescending(listToSort, length, lineOfTheHeap);
        }


        for (int position = length - 1; position >= 0; position--) {
            LocalTime temp = listToSort.get(0);
            listToSort.set(0, listToSort.get(position));
            listToSort.set(position, temp);

            String temp2 = legacyDataList.get(0);
            legacyDataList.set(0, legacyDataList.get(position));
            legacyDataList.set(position, temp2);

            heapifyDescending(listToSort, position, 0);
        }
    }

    private void heapifyDescending(List<LocalTime> listToSort, int length, int lineOfTheHeap) {

        int earliest = lineOfTheHeap;
        int leftChild = 2 * lineOfTheHeap + 1;
        int rightChild = 2 * lineOfTheHeap + 2;

        // If the left child is later (later) than the root
        if (leftChild < length && listToSort.get(leftChild).isBefore(listToSort.get(earliest))) {
            earliest = leftChild;
        }

        // If the right child is bigger (later) than the now latest
        if (rightChild < length && listToSort.get(rightChild).isBefore(listToSort.get(earliest))) {
            earliest = rightChild;
        }

        // If the root is not the latest
        if (earliest != lineOfTheHeap) {
            LocalTime temp = listToSort.get(earliest);
            listToSort.set(earliest, listToSort.get(lineOfTheHeap));
            listToSort.set(lineOfTheHeap, temp);
            String temp2 = legacyDataList.get(earliest);
            legacyDataList.set(earliest, legacyDataList.get(lineOfTheHeap));
            legacyDataList.set(lineOfTheHeap, temp2);

            // Recursive call to heapify until the root is the latest
            heapifyDescending(listToSort, length, earliest);
        }
    }

    public void printSortedArray() {
        System.out.println("Sorted Array:");
        for (LocalTime line : listToSort) {
            System.out.println(line);
        }
    }

}
