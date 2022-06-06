package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUser;
import app.domain.model.Vaccine;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataFromLegacySystemController {
    private Company company = App.getInstance().getCompany();

    public ArrayList<SnsUser> getSNSUserList(){
        return company.getSnsUserList();
    }

    public List<Vaccine> getVaccines() {
        return company.getVaccinesList();
    }

    List<String> csvLegacyData = new ArrayList<>();

    public void readFile(String path) throws Exception {
        String line = null;
        BufferedReader br = new BufferedReader(new FileReader(path));
        br.readLine();
        while ((line = br.readLine()) != null){
            line = line.replaceAll("\"", "");
            String[] values = line.split(";");
            csvLegacyData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
                    + values[6] +  "_" + values[7]);
        }
    }

    public void updateLegacyFile() throws NotSerializableException {
        if (!getSNSUserList().isEmpty()){
            for (int i = 0; i < csvLegacyData.size(); i++) {
                String[] values;
                float percentage = (float)i*100/csvLegacyData.size();
                boolean flag;
                int j;
                int k;

                System.out.printf("\n%.1f%% complete...", percentage);
                values = csvLegacyData.get(i).split("_");

                for (j=0; j< getSNSUserList().size(); j++) {
                    if (getSNSUserList().get(j).getSnsUserNumber()==Integer.parseInt(values[0])){
                        break;
                    }
                }
                csvLegacyData.set(i, getSNSUserList().get(j).getStrName() +"_"+ csvLegacyData.get(i));

                for (k = 0; k < getVaccines().size(); k++) {
                    if (getVaccines().get(k).getName().equals(values[1])){
                        break;
                    }
                }
                csvLegacyData.set(i, csvLegacyData.get(i)+"_"+getVaccines().get(k).getVaccineType().getDescription());
            }
            System.out.println();
            printUpdatedLegacy(csvLegacyData);
            exportDataToFile(csvLegacyData);
        }
    }

    public void exportDataToFile(List<String> csvLegacyData) throws NotSerializableException {
        GenericClass<String> generics=new GenericClass<>();
        generics.binaryFileWrite(Constants.FILE_PATH_UPDATEDLEGACY, csvLegacyData);
    }

    public void printUpdatedLegacy(List<String> list){
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
