package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.miscellaneous.ReadLegacyDataFile;

import java.io.*;
import java.sql.SQLOutput;
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

    public void exportDataToFile(List<String> csvLegacyData) throws NotSerializableException {
        GenericClass<String> generics = new GenericClass<>();
        generics.binaryFileWrite(Constants.FILE_PATH_UPDATEDLEGACY, csvLegacyData);
    }

    public void getSortedList(String path) throws Exception {
        ReadLegacyDataFile rldf = new ReadLegacyDataFile();
        if (rldf.readFile(path)) {
            rldf.updateLegacyFile();
            if (rldf.choosePositionToSort()){
                rldf.sortListWithAlgo();
            }
        }
    }
}
