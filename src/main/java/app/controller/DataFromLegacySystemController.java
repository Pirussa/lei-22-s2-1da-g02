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

    public ArrayList<SnsUser> getSNSUserList() {
        return company.getSnsUserList();
    }

    public List<Vaccine> getVaccines() {
        return company.getVaccinesList();
    }


    //public void sortArrivalTime() throws NotSerializableException {
    //    if (!csvLegacyData.isEmpty()) {
    //        String[] values;
    //        String[] valuesMinusOne;
    //        LocalDateTime dateFormatted;
    //        LocalDateTime dateFormattedMinusOne;
    //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
    //        dateFormattedMinusOne = LocalDateTime.parse("01/01/1990 00:00", formatter);
    //        boolean itsFirstTime = true;
//
//
    //        for (int i = 1; i < csvLegacyData.size(); i++) {
    //            values = csvLegacyData.get(i).split("_");
//
    //            if (i - 1 >= 0) {
    //                valuesMinusOne = csvLegacyData.get(i - 1).split("_");
    //                dateFormattedMinusOne = LocalDateTime.parse(valuesMinusOne[6], formatter);
    //                itsFirstTime = false;
    //            }
//
    //            dateFormatted = LocalDateTime.parse(values[6], formatter);
    //            if (!itsFirstTime && dateFormatted.isAfter(dateFormattedMinusOne)) {
    //                continue;
    //            } else if (dateFormattedMinusOne.isAfter(dateFormatted)) {
    //                Collections.swap(csvLegacyData, i, i - 1);
    //            } else {
    //                continue;
    //            }
    //        }
    //    }
    //}

    public void exportDataToFile(List<String> csvLegacyData) throws NotSerializableException {
        GenericClass<String> generics = new GenericClass<>();
        generics.binaryFileWrite(Constants.FILE_PATH_UPDATEDLEGACY, csvLegacyData);
    }



    public void getSortedList(String path) throws Exception {
        ReadLegacyDataFile rldf = new ReadLegacyDataFile();
        rldf.readFile(path);
        rldf.updateLegacyFile();
        rldf.setArrivalDateList();
        rldf.bubbleSortAscending();
    }
}
