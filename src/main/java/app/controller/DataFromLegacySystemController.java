package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.miscellaneous.ReadLegacyDataFile;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataFromLegacySystemController {
    private final Company company = App.getInstance().getCompany();

    private ArrayList<SnsUser> getSNSUserList() {
        return company.getSnsUserList();
    }

    private List<Vaccine> getVaccines() {
        return company.getVaccinesList();
    }

    private List<String> csvLegacyData = new ArrayList<>();

    private void readFile(String path) throws Exception {
        ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.readFile(path);
    }

    /**
     * Update legacy file.
     * <p>
     * Adds names and vaccines descriptions to the list.
     *
     * @throws NotSerializableException the not serializable exception
     */
    public void updateLegacyFile() throws NotSerializableException {
        if (!getSNSUserList().isEmpty() && !getVaccines().isEmpty()) {
            for (int i = 0; i < csvLegacyData.size(); i++) {
                String[] values;
                float percentage = (float) i * 100 / csvLegacyData.size();
                boolean flag;
                int j;
                int k;

                System.out.printf("\n%.1f%% complete...", percentage);
                values = csvLegacyData.get(i).split("_");

                for (j = 0; j < getSNSUserList().size(); j++) {
                    if (getSNSUserList().get(j).getSnsUserNumber() == Integer.parseInt(values[0])) {
                        break;
                    }
                }
                csvLegacyData.set(i, getSNSUserList().get(j).getStrName() + "_" + csvLegacyData.get(i));

                for (k = 0; k < getVaccines().size(); k++) {
                    if (getVaccines().get(k).getName().equals(values[1])) {
                        break;
                    }
                }
                csvLegacyData.set(i, csvLegacyData.get(i) + "_" + getVaccines().get(k).getVaccineType().getDescription());
            }
            System.out.println();
            printUpdatedLegacy(csvLegacyData);
            exportDataToFile(csvLegacyData);
        } else {
            System.out.println("Either the SNS User list is empty or the Vaccine list is," +
                    " this makes it impossible to update the legacy " +
                    "data with the SNS User number and the Vaccine's description.");
        }
    }


    public void sortArrivalTime() throws NotSerializableException {
        if (!csvLegacyData.isEmpty()) {
            String[] values;
            String[] valuesMinusOne;
            LocalDateTime dateFormatted;
            LocalDateTime dateFormattedMinusOne;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            dateFormattedMinusOne = LocalDateTime.parse("01/01/1990 00:00", formatter);
            boolean itsFirstTime = true;


            for (int i = 1; i < csvLegacyData.size(); i++) {
                values = csvLegacyData.get(i).split("_");

                if (i - 1 >= 0) {
                    valuesMinusOne = csvLegacyData.get(i - 1).split("_");
                    dateFormattedMinusOne = LocalDateTime.parse(valuesMinusOne[6], formatter);
                    itsFirstTime = false;
                }

                dateFormatted = LocalDateTime.parse(values[6], formatter);
                if (!itsFirstTime && dateFormatted.isAfter(dateFormattedMinusOne)) {
                    continue;
                } else if (dateFormattedMinusOne.isAfter(dateFormatted)) {
                    Collections.swap(csvLegacyData, i, i - 1);
                } else {
                    continue;
                }
            }
            System.out.println(csvLegacyData);
        }
    }

    public void exportDataToFile(List<String> csvLegacyData) throws NotSerializableException {
        GenericClass<String> generics = new GenericClass<>();
        generics.binaryFileWrite(Constants.FILE_PATH_UPDATEDLEGACY, csvLegacyData);
    }

    public void printUpdatedLegacy(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }


    public List<String> getSortedList(String path) throws Exception {
        readFile(path);
        updateLegacyFile();
        sortArrivalTime();
        return null;
    }
}
