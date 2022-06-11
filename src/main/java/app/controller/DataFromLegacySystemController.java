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

    public void updateFile() throws NotSerializableException {
        readLegacyDataFile.updateLegacyFile();
    }

    public void setList(int position){
        //ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.setList(position);
    }

    public void mergeAscending(List<LocalTime> list, int begin, int end){
        //ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.mergeSortAscending(list,begin,end);
    }

    public void mergeDescending(List<LocalTime> list, int begin, int end){
        //ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.mergeSortDescending(list,begin,end);
    }

    public List<LocalTime> getListToSort(){
       // ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        return readLegacyDataFile.listToSort;
    }

    public void heapAscending(){
        //ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.heapSortAscending();
    }

    public void heapDescending(){
       // ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.heapSortDescending();
    }

    public void getPrintSortedArray(){
        //ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
        readLegacyDataFile.printSortedArray();
    }

}
