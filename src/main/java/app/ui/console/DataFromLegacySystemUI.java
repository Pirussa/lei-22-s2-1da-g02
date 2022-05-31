package app.ui.console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DataFromLegacySystemUI implements Runnable{

  public void run(){
    try {
    Scanner readPath = new Scanner(System.in);
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
      System.out.println("Choose the sorting algorithm.");
      System.out.println();
      System.out.println("1 - Sort by arrival time.");
      System.out.println();
      System.out.println("2 - Sort by center leaving time.");
      System.out.println();
      System.out.println("Choose the option:");
      Scanner choice = new Scanner(System.in);
        int option = choice.nextInt();
        if (option == 1) {
          sortByArrivalTime(csvLegacyData);
        } else if (option == 2) {
          sortByLeavingTime(csvLegacyData);
        } else {
          System.out.println("Option is Invalid.");
          run();
        }
} catch (Exception e){
      e.printStackTrace();
    }
  }


  public void sortByArrivalTime(List<String> csvLegacyData){

    }

  public void sortByLeavingTime(List<String> csvLegacyData){
    Collections.sort(csvLegacyData.get(5));
    System.out.println(csvLegacyData);
  }

}
