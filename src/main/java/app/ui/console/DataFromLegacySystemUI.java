package app.ui.console;

import app.controller.DataFromLegacySystemController;
import app.domain.model.SnsUser;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;

import java.io.*;
import java.util.*;

public class DataFromLegacySystemUI implements Runnable{
  private final DataFromLegacySystemController controller = new DataFromLegacySystemController();

  public void run(){
    try {
    Scanner readPath = new Scanner(System.in);
    System.out.println();
    System.out.println("File Location: ");
    System.out.println();
    String path = readPath.nextLine();
    List<String> csvLegacyData = new ArrayList<>();
    String line = null;
    BufferedReader br = new BufferedReader(new FileReader(path));
    br.readLine();
    while ((line = br.readLine()) != null) {
      line = line.replaceAll("\"", "");
      String[] values = line.split(";");
      csvLegacyData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
          + values[6] +  "_" + values[7]);
    }

      if (!controller.getSNSUserList().isEmpty()){
        for (int i = 0; i < csvLegacyData.size(); i++) {
          String[] values;
          float percentage = (float)i*100/csvLegacyData.size();
          boolean flag;
          int j;
          int k;

          System.out.printf("\n%.1f%% complete...", percentage);
          values = csvLegacyData.get(i).split("_");

          for (j=0; j< controller.getSNSUserList().size(); j++) {
            if (controller.getSNSUserList().get(j).getSnsUserNumber()==Integer.parseInt(values[0])){
              break;
            }
          }
          csvLegacyData.set(i, controller.getSNSUserList().get(j).getStrName() +"_"+ csvLegacyData.get(i));

          for (k = 0; k < controller.getVaccines().size(); k++) {
            if (controller.getVaccines().get(k).getName().equals(values[1])){
              break;
            }
          }
          csvLegacyData.set(i, csvLegacyData.get(i)+"_"+controller.getVaccines().get(k).getVaccineType().getDescription());
        }
        System.out.println();
        printUpdatedLegacy(csvLegacyData);
        exportDataToFile(csvLegacyData);
      }

} catch (Exception e){
      e.printStackTrace();
    }
  }


  public void sortByArrivalTime(List<String> csvLegacyData){
    System.out.println("TODO");
    }

  public void sortByLeavingTime(List<String> csvLegacyData){
    System.out.println("TODO");
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
