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
    controller.readFile(path);
    controller.updateLegacyFile();
    controller.sortArrivalTime();


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
