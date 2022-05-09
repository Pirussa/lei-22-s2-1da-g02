package app.ui.console;

import app.ui.console.utils.Utils;
import com.sun.source.tree.IfTree;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadCSVUI implements Runnable {

    public void run() {

        System.out.println("");
        if (Utils.confirmCreation()) {
            try{
                Scanner readPath = new Scanner(System.in);
                System.out.println("File Location: ");
                System.out.println();
                String path = readPath.nextLine();
                ArrayList<String> csvData = new ArrayList<>();
                if (validateFileFormat(path)){
                    String line ="";
                    BufferedReader br = new BufferedReader(new FileReader(path));
                    br.readLine();
                    while((line = br.readLine()) != null){
                        String[] values = line.split(",");
                        if (validateCSVData(line, values)){
                            csvData.add("\nName: "+values[0] + " SNS User Number: " + values[1]);
                        }else{
                            throw new IllegalArgumentException("The CSV data is invalid, .i.e, the Name of the User has non-word character.");
                        }
                    }
                    System.out.println(csvData);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    public boolean validateFileFormat(String path) {
        return path.endsWith(".csv");
    }

    public boolean validateCSVData(String line, String[] values) throws IOException {
            values = line.split(",");
        return !values[0].isEmpty() && !values[1].isEmpty() && values[1].trim().matches("^[0-9]*$");
        }
    }



