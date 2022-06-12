package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.miscellaneous.ReadLegacyDataFile;
import app.ui.console.DataFromLegacySystemUI;

import java.io.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataFromLegacySystemController {

    private final ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
    private final Company company = App.getInstance().getCompany();

    public DataFromLegacySystemController() {
    }


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

    public void readFile(String path) throws Exception {
        readLegacyDataFile.readFile(path);
    }

    public boolean updateFile() throws NotSerializableException {
       return readLegacyDataFile.updateLegacyFile();
    }

    public void setList(int position){
        readLegacyDataFile.setList(position);
    }

    public List<String> mergeAscending(List<LocalTime> list, int begin, int end){
       return readLegacyDataFile.mergeSortAscending(list,begin,end);
    }

    public List<String> mergeDescending(List<LocalTime> list, int begin, int end){
         return readLegacyDataFile.mergeSortDescending(list,begin,end);
    }

    public List<LocalTime> getListToSort(){
        return readLegacyDataFile.listToSort;
    }

    public List<String> heapAscending(){
     return    readLegacyDataFile.heapSortAscending();
    }

    public List<String> heapDescending(){
      return readLegacyDataFile.heapSortDescending();
    }


}
