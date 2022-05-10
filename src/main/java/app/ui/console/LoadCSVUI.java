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
import java.util.Objects;
import java.util.Scanner;

public class LoadCSVUI implements Runnable {

    private final int MAXNUMBEROFCHARSSNSUSERNUMBER = 9;

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
                    String delimiter=null;
                    if (br.readLine().contains(";")){
                        delimiter=";";
                    } else if (br.readLine().contains(",")){
                        delimiter=",";
                    }

                    while((line = br.readLine()) != null){
                        String password = Utils.passwordGenerator();
                        String[] values = line.split(delimiter);
                        if (validateCSVData(values)){
                            csvData.add(values[0]+" "+values[1]+" "+values[2]+" "+ password);
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

    public boolean validateCSVData(String[] values) throws IOException {

        return !values[0].isEmpty() && !values[1].isEmpty() && values[1].trim().matches("^[0-9]*$") && values[1].length()==MAXNUMBEROFCHARSSNSUSERNUMBER
                && !values[2].isEmpty() && Utils.validateEmail(values[2]);
        }


    }



