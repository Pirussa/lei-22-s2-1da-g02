package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.controller.LoadCSVController;
import app.ui.console.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
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
                            csvData.add(values[0]+"/"+values[1]+"/"+values[2]+"/"+ password);
                        }else{
                            throw new IllegalArgumentException("The CSV data is invalid, .i.e, the Name of the User has non-word character.");
                        }
                    }
                    fillSNSUserDto(csvData);
                    getListOfSNSUsers();
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

        int MAXNUMBEROFCHARSSNSUSERNUMBER = 9;
        return !values[0].isEmpty() && !values[1].isEmpty() && values[1].trim().matches("^[0-9]*$") && values[1].length()== MAXNUMBEROFCHARSSNSUSERNUMBER
                && !values[2].isEmpty() && Utils.validateEmail(values[2]);
    }

    public void fillSNSUserDto(ArrayList<String> csvData){
        LoadCSVController controller = new LoadCSVController();
        String[] values;
        for (int i = 0; i < csvData.size(); i++) {
            SNSUserDto dto = new SNSUserDto();
            values=csvData.get(i).split("/");
            dto.strName= values[0];
            dto.strSNSUserNumber = values[1];
            dto.strEmail = values[2];
            dto.strPassword = values[3];
            System.out.println();
            System.out.println(controller.createSNSUser(dto));
            System.out.println();
            if (Utils.confirmCreation()){
                controller.saveSNSUser(dto);
            } else System.out.println("Not confirmed");
        }
    }

    public void getListOfSNSUsers() {
        LoadCSVController controller = new LoadCSVController();
        if (!controller.getSNSUserList().isEmpty()) {
            for (int i = 0; i < controller.getSNSUserList().size(); i++) {
                System.out.println("\nPosition " + i + ": " + "\n" + controller.getSNSUserList().get(i));
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("There aren't any registered SNS Users.");
        }
    }
    }



