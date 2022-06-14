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

/**
 * The type Data from legacy system controller.
 */
public class DataFromLegacySystemController {

    private final ReadLegacyDataFile readLegacyDataFile = new ReadLegacyDataFile();
    private final Company company = App.getInstance().getCompany();

    /**
     * Instantiates a new Data from legacy system controller.
     */
    public DataFromLegacySystemController() {
    }


    /**
     * Gets sns user list.
     *
     * @return the sns user list
     */
    public ArrayList<SnsUser> getSNSUserList() {
        return company.getSnsUsersStore().getSnsUserList();
    }

    /**
     * Gets vaccines.
     *
     * @return the vaccines
     */
    public List<Vaccine> getVaccines() {
        return company.getVaccinesList();
    }

    /**
     * Export data to file.
     *
     * @param csvLegacyData the csv legacy data
     * @throws NotSerializableException the not serializable exception
     */
    public void exportDataToFile(List<String> csvLegacyData) throws NotSerializableException {
        GenericClass<String> generics = new GenericClass<>();
        generics.binaryFileWrite(Constants.FILE_PATH_UPDATEDLEGACY, csvLegacyData);
    }

    /**
     * Read file.
     *
     * @param path the path
     * @throws Exception the exception
     */
    public void readFile(String path) throws Exception {
        readLegacyDataFile.readFile(path);
    }

    /**
     * Update file boolean.
     *
     * @return the boolean
     * @throws NotSerializableException the not serializable exception
     */
    public List<String> updateFile() throws NotSerializableException {
       return readLegacyDataFile.updateLegacyFile();
    }

    /**
     * Set list.
     *
     * @param position the position
     */
    public void setList(int position){
        readLegacyDataFile.setList(position);
    }

    /**
     * Merge ascending list.
     *
     * @param list  the list
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    public List<String> mergeAscending(List<LocalTime> list, int begin, int end){
       return readLegacyDataFile.mergeSortAscending(list,begin,end);
    }

    /**
     * Merge descending list.
     *
     * @param list  the list
     * @param begin the begin
     * @param end   the end
     * @return the list
     */
    public List<String> mergeDescending(List<LocalTime> list, int begin, int end){
         return readLegacyDataFile.mergeSortDescending(list,begin,end);
    }

    /**
     * Get list to sort list.
     *
     * @return the list
     */
    public List<LocalTime> getListToSort(){
        return readLegacyDataFile.listToSort;
    }

    /**
     * Heap ascending list.
     *
     * @return the list
     */
    public List<String> heapAscending(){
     return    readLegacyDataFile.heapSortAscending();
    }

    /**
     * Heap descending list.
     *
     * @return the list
     */
    public List<String> heapDescending(){
      return readLegacyDataFile.heapSortDescending();
    }


}
