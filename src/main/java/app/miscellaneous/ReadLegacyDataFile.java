package app.miscellaneous;

import app.controller.App;
import app.controller.DataFromLegacySystemController;
import app.domain.model.Company;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The type Read legacy data file.
 */
public class ReadLegacyDataFile {

    private final Company company = App.getInstance().getCompany();


    /**
     * The Legacy data list.
     */
    public final List<String> legacyDataList = new ArrayList<>();
    /**
     * The List to sort.
     */
    public final List<LocalTime> listToSort = new ArrayList<>();

    /**
     * Read file.
     *
     * @param path the path
     * @throws Exception the exception
     */
    public void readFile(String path) throws Exception {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            StringBuilder stringToBeAdded = new StringBuilder();
            stringToBeAdded.append(values[0]).append("|").append(values[1]).append("|").append(values[2]).append("|").append(values[3])
                    .append("|").append(values[4]).append("|").append(values[5]).append("|").append(values[6]).append("|").append(values[7]);
            legacyDataList.add(stringToBeAdded.toString());
        }
    }

    /**
     * Update legacy file.
     *
     * @return the boolean
     * @throws NotSerializableException the not serializable exception
     */
    public boolean updateLegacyFile() throws NotSerializableException {
        if (!company.getSnsUsersStore().getSnsUserList().isEmpty() && !company.getVaccinesList().isEmpty()) {
            for (int lineOfTheData = 0; lineOfTheData < legacyDataList.size(); lineOfTheData++) {
                String[] values;
                int positionInSnsUserList = 0;
                int positionInVaccinesList = 0;
                values = legacyDataList.get(lineOfTheData).split("\\|");

                for ( positionInSnsUserList = 0; positionInSnsUserList < company.getSnsUsersStore().getSnsUserList().size(); positionInSnsUserList++) {
                    if (company.getSnsUsersStore().getSnsUserList().get(positionInSnsUserList).getSnsUserNumber() == Integer.parseInt(values[0])) {
                        break;
                    }
                }
                legacyDataList.set(lineOfTheData, company.getSnsUsersStore().getSnsUserList().get(positionInSnsUserList).getStrName() + "|" + legacyDataList.get(lineOfTheData));

                for ( positionInVaccinesList = 0; positionInVaccinesList < company.getVaccinesList().size(); positionInVaccinesList++) {
                    if (company.getVaccinesList().get(positionInVaccinesList).getName().equals(values[1])) {
                        break;
                    }
                }
                legacyDataList.set(lineOfTheData, legacyDataList.get(lineOfTheData) + "|" + company.getVaccinesList().get(positionInVaccinesList).getVaccineType().getDescription());
            }
        } else {
            return  false;
        }
        return true;
    }

    /**
     * Merge sort ascending list.
     *
     * @param list  the list
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    public List<String> mergeSortAscending(List<LocalTime> list, int begin, int end) {

        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortAscending(list, begin, middle); //call Merge Sort on the first half
            mergeSortAscending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int firstPositionOfListToSort = begin, midPositionOfListToSort = middle + 1;
            while (firstPositionOfListToSort <= middle && midPositionOfListToSort <= end) {
                if (list.get(midPositionOfListToSort).compareTo(list.get(firstPositionOfListToSort)) > 0) {
                    int copyOfPos1 = firstPositionOfListToSort;
                    newlist.add(list.get(firstPositionOfListToSort++));
                    tempList.add(legacyDataList.get(copyOfPos1++));
                } else {
                    int copyOfPos2 = midPositionOfListToSort;
                    newlist.add(list.get(midPositionOfListToSort++));
                    tempList.add(legacyDataList.get(copyOfPos2++));
                }
            }
            while (firstPositionOfListToSort <= middle) {
                int copyOfPos3 = firstPositionOfListToSort;
                newlist.add(list.get(firstPositionOfListToSort++));

                tempList.add(legacyDataList.get(copyOfPos3++));
            }
            while (midPositionOfListToSort <= end) {
                int copyOfPos4 = midPositionOfListToSort;
                newlist.add(list.get(midPositionOfListToSort++));
                tempList.add(legacyDataList.get(copyOfPos4++));
            }
            firstPositionOfListToSort = begin;
            int copyOfPos5 = firstPositionOfListToSort;
            for (LocalTime item : newlist) {
                list.set(firstPositionOfListToSort++, item);
            }

            for (String strItem : tempList) {
                legacyDataList.set(copyOfPos5++, strItem);
            }

            writeArrayToFile(legacyDataList);
        }

        return legacyDataList;
    }

    /**
     * Merge sort descending list.
     *
     * @param list  the list
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    public List<String> mergeSortDescending(List<LocalTime> list, int begin, int end) {
        int middle = (begin + end) / 2;
        if (middle < end) {
            mergeSortDescending(list, begin, middle); //call Merge Sort on the first half
            mergeSortDescending(list, middle + 1, end); //call Merge Sort on the second half

            //merge the two sorted lists together:
            ArrayList<LocalTime> newlist = new ArrayList<>();
            ArrayList<String> tempList = new ArrayList<>();
            int firstPositionOfListToSort = begin, midPositionOfListToSort = middle + 1;
            while (firstPositionOfListToSort <= middle && midPositionOfListToSort <= end) {
                if (list.get(midPositionOfListToSort).compareTo(list.get(firstPositionOfListToSort)) < 0) {
                    int copyOfPos1 = firstPositionOfListToSort;
                    newlist.add(list.get(firstPositionOfListToSort++));
                    tempList.add(legacyDataList.get(copyOfPos1++));
                } else {
                    int copyOfPos2 = midPositionOfListToSort;
                    newlist.add(list.get(midPositionOfListToSort++));
                    tempList.add(legacyDataList.get(copyOfPos2++));
                }
            }
            while (firstPositionOfListToSort <= middle) {
                int copyOfPos3 = firstPositionOfListToSort;
                newlist.add(list.get(firstPositionOfListToSort++));

                tempList.add(legacyDataList.get(copyOfPos3++));
            }
            while (midPositionOfListToSort <= end) {
                int copyOfPos4 = midPositionOfListToSort;
                newlist.add(list.get(midPositionOfListToSort++));
                tempList.add(legacyDataList.get(copyOfPos4++));
            }
            firstPositionOfListToSort = begin;
            int copyOfPos5 = firstPositionOfListToSort;
            for (LocalTime item : newlist) {
                list.set(firstPositionOfListToSort++, item);
            }

            for (String strItem : tempList) {
                legacyDataList.set(copyOfPos5++, strItem);
            }
            writeArrayToFile(legacyDataList);
        }
        return  legacyDataList;
    }

    /**
     * Sets list.
     *
     * @param position the position
     */
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

    /**
     * Write array to file.
     *
     * @param list the list
     */
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

    /**
     * Heap sort ascending list.
     *
     * @return the list
     */
    public List<String> heapSortAscending() {
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
        return legacyDataList;
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

    /**
     * Heap sort descending list.
     *
     * @return the list
     */
    public List<String> heapSortDescending() {

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
        return legacyDataList;
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



}
