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
import java.util.Scanner;

public class LoadCSVUI implements Runnable {

    public void run() {

        System.out.println("");
        System.out.println("Do you want to load a CSV file?");
        if (Utils.confirmCreation()) {
            try{
                Scanner readPath = new Scanner(System.in);
                System.out.println("File Location: ");
                String path = readPath.nextLine();
                if (validateFileFormat(path)){
                    String line ="";
                    BufferedReader br = new BufferedReader(new FileReader(path));

                    while((line = br.readLine()) != null){
                        String[] values = line.split(",");
                        System.out.println("EX1: " + values[0] + ", EX2: " + values[1]);
                    }
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

}


//Scanner readPath = new Scanner(System.in);
//System.out.println("File Location: ");
//String path = readPath.nextLine();
//if (validateFileFormat(path)){
//    System.out.println("File Format is valid.");
//}
//else{
//    throw new IllegalArgumentException("No file found or it's not a .csv file.");
//}
