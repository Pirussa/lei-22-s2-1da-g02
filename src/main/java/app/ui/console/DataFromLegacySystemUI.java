package app.ui.console;

import app.domain.model.SnsUser;
import app.ui.console.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataFromLegacySystemUI implements Runnable{

    public void run(){
        try {
        Scanner readPath = new Scanner(System.in);
        System.out.println("File Location: ");
        System.out.println();
        String path = readPath.nextLine();
        ArrayList<String> csvData = new ArrayList<>();
        if (validateFileFormat(path)) {
            String line = null;
            BufferedReader br = new BufferedReader(new FileReader(path));
            String delimiter = null;
            if (br.readLine().contains(";")) {
                delimiter = ";";
            }

            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\"", "");
                String[] values = line.split(delimiter);
                csvData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
                            + values[6] +  "_" + values[7]);

            }

            System.out.println(csvData);
    }
} catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean validateFileFormat(String path) {
        return path.endsWith(".csv");
    }

}
