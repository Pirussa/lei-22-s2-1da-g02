package app.ui.console;

import app.controller.LoadCSVController;
import app.domain.model.SnsUser;
import app.ui.console.utils.Utils;
import dto.SNSUserDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LoadCSVUI implements Runnable {

    LoadCSVController controller = new LoadCSVController();

    public void run() {

            System.out.println("");
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
                br.mark(2000);
                if (br.readLine().contains(";")) {
                    delimiter = ";";
                } else {
                    delimiter = ",";
                    br.reset();
                }

                while ((line = br.readLine()) != null) {
                    String password = Utils.passwordGenerator();
                    line = line.replaceAll("\"", "");
                    String[] values = line.split(delimiter);
                    if (validateCSVData(values)) {
                        csvData.add(values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3] + "_" + values[4] + "_" + values[5] + "_"
                                + values[6] + "_" + values[7] + "_" + password);
                    } else {
                        throw new IllegalArgumentException("The CSV data is invalid, .i.e, the Name of the User has non-word character.");
                    }
                }
                fillSNSUserDto(csvData);

            } else {
                System.out.println();
                System.out.println("Only files ending in .csv are allowed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            if (confirmCreationCSV()){
                run();
            }
            return;
        }
    }

    public boolean validateFileFormat(String path) {
        return path.endsWith(".csv");
    }

    public boolean validateCSVData(String[] values) throws IOException {

        int MAXNUMBEROFCHARSSNSUSERNUMBER = 9;
        return !values[0].isEmpty() && Utils.validateSex(values[1]) && !values[2].isEmpty() && Utils.validateBirthDate(values[2]) &&
                !values[3].isEmpty() && SnsUser.validateAddress(values[3]) && !values[4].isEmpty() && Utils.validatePhoneNumber(values[4]) &&
                !values[5].isEmpty() && Utils.validateEmail(values[5]) && values[6].trim().matches("^[0-9]*$") && values[6].length() == MAXNUMBEROFCHARSSNSUSERNUMBER &&
                !values[7].isEmpty() && Utils.validateCitizenCardNumber(values[7]);
    }

    public void fillSNSUserDto(ArrayList<String> csvData) {
        LoadCSVController controller = new LoadCSVController();
        String[] values;
        int createCounter = 0;
        int saveCounter = 0;
        for (int i = 0; i < csvData.size(); i++) {
            SNSUserDto dto = new SNSUserDto();
            values = csvData.get(i).split("_");
            dto.strName = values[0];
            dto.strSex = values[1];
            dto.strBirthDate = values[2];
            dto.strAddress = values[3];
            dto.strPhoneNumber = values[4];
            dto.strEmail = values[5];
            dto.snsUserNumber = Integer.parseInt(values[6]);
            dto.strCitizenCardNumber = values[7];
            dto.strPassword = values[8];
            controller.createSNSUser(dto);
            createCounter++;
            if (controller.saveSNSUser(dto).equals("Not Saved because the data is duplicated")) {
                saveCounter++;
            }
        }
        System.out.printf("Saved %d Users out of %d, because %d had duplicated information.",createCounter - saveCounter, createCounter, saveCounter);
    }

    public static boolean confirmCreationCSV() {
        System.out.println("CSV Data is invalid or the CSV file does not exist.");
        System.out.println();
        System.out.println("Do you want to load another file?");
        System.out.println();
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        Scanner sc = new Scanner(System.in);
        System.out.printf("%nType your option: ");
        int check = 0;
        int option = 0;
        do {
            try {
                option = sc.nextInt();
                sc.nextLine();
                check = 1;
            } catch (InputMismatchException e) {
                System.out.println("Insert a valid option.");
                sc.nextLine();
            }
        } while (check == 0);

        return option == 1;
    }

}



