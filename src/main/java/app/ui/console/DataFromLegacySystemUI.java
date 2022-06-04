package app.ui.console;

import app.controller.DataFromLegacySystemController;
import app.domain.model.SnsUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    while ((line = br.readLine()) != null) {
      line = line.replaceAll("\"", "");
      String[] values = line.split(";");
      csvLegacyData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
          + values[6] +  "_" + values[7]);
    }

      if (!controller.getSNSUserList().isEmpty()){
        for (int i = 1; i < csvLegacyData.size(); i++) {
          String[] values;
          float percentage = (float)i*100/csvLegacyData.size();
          boolean flag = false;
          int j;

          System.out.printf("\n%.1f%% complete...", percentage);
          values = csvLegacyData.get(i).split("_");

          for (j=0; j< controller.getSNSUserList().size(); j++) {
            if (controller.getSNSUserList().get(j).getSnsUserNumber()==Integer.parseInt(values[0])){
              flag = true;
            } else flag =false;
            if (flag){
              break;
            }
          }
            csvLegacyData.set(i, controller.getSNSUserList().get(j).getStrName() +"_"+ csvLegacyData.get(i));
        }
        System.out.println();
        for (int t = 0; t < csvLegacyData.size(); t++) {
          System.out.println(csvLegacyData.get(t));
        }
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

}
