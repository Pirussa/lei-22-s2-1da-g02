package app.miscellaneous;

import app.controller.App;
import app.domain.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Read legacy data file.
 */
public class ReadLegacyDataFile {

    private final Company company = App.getInstance().getCompany();
    private final VaccinationCenter center;

    public ReadLegacyDataFile(VaccinationCenter center) {
        this.center = center;
    }

    /**
     * The Legacy data list.
     */
    public final List<String> legacyDataList = new ArrayList<>();
    /**
     * The List to sort.
     */
    public final List<LocalDateTime> listToSort = new ArrayList<>();

    public List<String> updatedList = new ArrayList<>();
    public ArrayList<SnsUser> snsUsersList = new ArrayList<>();
    public List<Vaccine> vaccineList = new ArrayList<>();

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
        legacyDataList.clear();
        int countException = 0;
        while ((line = reader.readLine()) != null) {
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            String firstDate = values[4];
            String secondDate = values[5];
            String thirdDate = values[6];
            String fourthDate = values[7];
            String SNSUserNumber = values[0];
            countException++;
            if (validateFileLegacy(SNSUserNumber, firstDate, secondDate, thirdDate, fourthDate)) {
                StringBuilder stringToBeAdded = new StringBuilder();
                stringToBeAdded.append(values[0]).append("|").append(values[1]).append("|").append(values[2]).append("|").append(values[3])
                        .append("|").append(values[4]).append("|").append(values[5]).append("|").append(values[6]).append("|").append(values[7]);
                legacyDataList.add(stringToBeAdded.toString());
            } else {
                throw new IllegalArgumentException("Data imported from .csv file is invalid. Error at line:" +countException);
            }
        }
        updateLegacyFile();
    }


    public boolean validateFileLegacy(String SNSUserNumber, String ScheduledDateTime, String ArrivalDateTime, String NurseAdministrationDateTime, String LeavingDateTime) {

        if (SNSUserNumber.matches("^[0-9]{9}$") && isValidDate(ScheduledDateTime)
                && isValidDate(ArrivalDateTime) && isValidDate(NurseAdministrationDateTime) && isValidDate(LeavingDateTime)) {
            return true;
        } else {
            return false;
        }
    }


    public boolean isValidDate(String date) {
        try {
            String[] dateAndHour = date.split(" ");
            String[] monthDayAndYear = dateAndHour[0].split("/");
            int month = Integer.parseInt(monthDayAndYear[0]);
            int day = Integer.parseInt(monthDayAndYear[1]);
            int year = Integer.parseInt(monthDayAndYear[2]);
            String[] hourAndMinute = dateAndHour[1].split(":");
            int hour = Integer.parseInt(hourAndMinute[0]);
            int minute = Integer.parseInt(hourAndMinute[1]);

            LocalDateTime dataToCheck = LocalDateTime.of(year, month, day, hour, minute);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Update legacy file.
     *
     * @return the boolean
     */
    public List<String> updateLegacyFile() {
        updatedList.clear();
        snsUsersList =getSNSUserList();
        vaccineList=getVaccineList();
        if (!snsUsersList.isEmpty() && !vaccineList.isEmpty()) {
            for (int lineOfTheData = 0; lineOfTheData < legacyDataList.size(); lineOfTheData++) {
                String[] values;
                int posOfUser;
                int posOfVax;
                int positionInSnsUserList = 0;
                int positionInVaccinesList = 0;
                values = legacyDataList.get(lineOfTheData).split("\\|");

                posOfUser = findPosOfSNSUser(snsUsersList, values, positionInSnsUserList);
                posOfVax = findPosOfVax(vaccineList, values, positionInVaccinesList);

                if (posOfUser!=-1 && posOfVax!=-1){
                    if (checksDuplicates(updatedList, snsUsersList, vaccineList, posOfUser, posOfVax, lineOfTheData)) {
                        updatedList.add(snsUsersList.get(posOfUser).getStrName() + "|" + legacyDataList.get(lineOfTheData) + "|" + vaccineList.get(posOfVax).getVaccineType().getDescription());
                        boolean serialize = legacyDataList.size() -1  == lineOfTheData;
                        setArrival(values[4], values[0], serialize);
                        setDeparture(values[7], values[0], serialize);
                }
            }
        }
        return updatedList;
    } else

    {
        return null;
    }

}

    public ArrayList<SnsUser> getSNSUserList() {
        return company.getSnsUsersStore().getSnsUserList();
    }

    public List<Vaccine> getVaccineList() {
        return company.getVaccinesList();
    }

    public boolean checksIfVaccineListIsNotEmpty() {
        if (!company.getVaccinesList().isEmpty()) {
            return true;
        } else return false;
    }

    public boolean checksIfSNSUserListIsNotEmpty() {
        if (!company.getSnsUsersStore().getSnsUserList().isEmpty()) {
            return true;
        } else return false;
    }

    public int findPosOfSNSUser(ArrayList<SnsUser> list, String[] values, int positionInSnsUserList) {
        for (positionInSnsUserList = 0; positionInSnsUserList < list.size(); positionInSnsUserList++) {
            if (list.get(positionInSnsUserList).getSnsUserNumber() == Integer.parseInt(values[0])) {
                return positionInSnsUserList;
            }
        }
        return -1;
    }

    public int findPosOfVax(List<Vaccine> list, String[] values, int positionInVaccinesList) {
        for (positionInVaccinesList = 0; positionInVaccinesList < list.size(); positionInVaccinesList++) {
            if (list.get(positionInVaccinesList).getName().equals(values[1])) {
                return positionInVaccinesList;
            }
        }
        return -1;
    }

    public Boolean checksDuplicates(List<String> updatedList, List<SnsUser> snsUsers, List<Vaccine> vaccines, int positionInSnsUserList, int positionInVaccinesList, int lineOfTheData) {
        if (!updatedList.contains(snsUsers.get(positionInSnsUserList).getStrName() + "|" + legacyDataList.get(lineOfTheData) + "|" + vaccines.get(positionInVaccinesList).getVaccineType().getDescription())) {
            return true;
        } else return false;
    }

    private void setDeparture(String departureTime, String snsNumber, boolean serialize) {
        LocalDateTime departure;
        if (!checkTimeFormat(departureTime).equals("0")) {
            departure = LocalDateTime.parse(departureTime, DateTimeFormatter.ofPattern(checkTimeFormat(departureTime)));
            try {
                center.addDeparture(new Departure(Integer.parseInt(snsNumber), departure), serialize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setArrival(String arrivalTime, String snsNumber, boolean serialize) {
        LocalDateTime arrival;
        if (!checkTimeFormat(arrivalTime).equals("0")) {
            arrival = LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern(checkTimeFormat(arrivalTime)));
            try {
                center.addArrival(new Arrival(Integer.parseInt(snsNumber), company.getVaccineTypesStore().getVaccineTypes().get(0), arrival), serialize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String checkTimeFormat(String arrivalTime) {
        String n;
        //Only one month , two days and one hour**
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("M/dd/yyyy H:mm"));
            return "M/dd/yyyy H:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Only one month, two days and two hours
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("M/dd/yyyy HH:mm"));
            return "M/dd/yyyy HH:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Only one month, one day and one hour
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("M/d/yyyy H:mm"));
            return "M/d/yyyy H:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Only one month, one day and two hours
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("M/d/yyyy HH:mm"));
            return "M/d/yyyy HH:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Two months, one day, one hour
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("MM/d/yyyy H:mm"));
            return "MM/d/yyyy H:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Two months, one day, two hours
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm"));
            return "MM/d/yyyy HH:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Two months, two days, one hour
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("MM/dd/yyyy H:mm"));
            return "MM/dd/yyyy H:mm";
        } catch (Exception e) {
            n = "0";
        }
        //Two months, two day, two hours
        try {
            LocalDateTime.parse(arrivalTime, DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm"));
            return "MM/dd/yyyy HH:mm";
        } catch (Exception e) {
            n = "0";
        }
        return n;
    }


    public List<String> insertionSortAscending(List<LocalDateTime> list) {
        for (int j = 1; j < list.size(); j++) {
            LocalDateTime current = list.get(j);
            String copyOfCurrent = updatedList.get(j);
            int i = j-1;
            while ((i > -1) && ((list.get(i).compareTo(current)) == 1)) {
                list.set(i+1, list.get(i));
                updatedList.set(i+1, updatedList.get(i));
                i--;
            }
            list.set(i+1, current);
            updatedList.set(i+1, copyOfCurrent);
        }
        return updatedList;
    }

    public List<String> insertionSortDescending(List<LocalDateTime> list) {
        for (int j = 1; j < list.size(); j++) {
            LocalDateTime current = list.get(j);
            String copyOfCurrent = updatedList.get(j);
            int i = j-1;
            while ((i > -1) && ((list.get(i).compareTo(current)) == -1)) {
                list.set(i+1, list.get(i));
                updatedList.set(i+1, updatedList.get(i));
                i--;
            }
            list.set(i+1, current);
            updatedList.set(i+1, copyOfCurrent);
        }
        return updatedList;
    }

    /**
     * Sets list.
     *
     * @param position the position
     */
    public void setList(int position) {
        String[] values;
        listToSort.clear();
        for (String line : updatedList) {
            values = line.split("\\|");
            String date = values[position];
            String[] dateAndHour = date.split(" ");
            String[] monthDayAndYear = dateAndHour[0].split("/");
            int month = Integer.parseInt(monthDayAndYear[0]);
            int day = Integer.parseInt(monthDayAndYear[1]);
            int year = Integer.parseInt(monthDayAndYear[2]);
            String[] hourAndMinute = dateAndHour[1].split(":");
            int hour = Integer.parseInt(hourAndMinute[0]);
            int minute = Integer.parseInt(hourAndMinute[1]);

            LocalDateTime dataToBeAdded = LocalDateTime.of(year, month, day, hour, minute);
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
            LocalDateTime temp = listToSort.get(0);
            listToSort.set(0, listToSort.get(position));
            listToSort.set(position, temp);

            String temp2 = updatedList.get(0);
            updatedList.set(0, updatedList.get(position));
            updatedList.set(position, temp2);


            heapifyAscending(listToSort, position, 0);
        }
        return updatedList;
    }

    private void heapifyAscending(List<LocalDateTime> listToSort, int length, int lineOfTheHeap) {

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
            LocalDateTime temp = listToSort.get(latest);
            listToSort.set(latest, listToSort.get(lineOfTheHeap));
            listToSort.set(lineOfTheHeap, temp);
            String temp2 = updatedList.get(latest);
            updatedList.set(latest, updatedList.get(lineOfTheHeap));
            updatedList.set(lineOfTheHeap, temp2);

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
            LocalDateTime temp = listToSort.get(0);
            listToSort.set(0, listToSort.get(position));
            listToSort.set(position, temp);

            String temp2 = updatedList.get(0);
            updatedList.set(0, updatedList.get(position));
            updatedList.set(position, temp2);

            heapifyDescending(listToSort, position, 0);
        }
        return updatedList;
    }

    private void heapifyDescending(List<LocalDateTime> listToSort, int length, int lineOfTheHeap) {

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
            LocalDateTime temp = listToSort.get(earliest);
            listToSort.set(earliest, listToSort.get(lineOfTheHeap));
            listToSort.set(lineOfTheHeap, temp);
            String temp2 = updatedList.get(earliest);
            updatedList.set(earliest, updatedList.get(lineOfTheHeap));
            updatedList.set(lineOfTheHeap, temp2);

            // Recursive call to heapify until the root is the latest
            heapifyDescending(listToSort, length, earliest);
        }
    }


}
