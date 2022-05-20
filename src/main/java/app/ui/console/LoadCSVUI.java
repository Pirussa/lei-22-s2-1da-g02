package app.ui.console;

import app.controller.LoadCSVController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;
import dto.SNSUserDto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
                br.mark(1000);
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
                System.out.println(controller.getSNSUserList().size());
                //getListOfSNSUsers();

            } else {
                System.out.println();
                System.out.println("Only files ending in .csv are allowed.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validateFileFormat(String path) {
        return path.endsWith(".csv");
    }

    public boolean validateCSVData(String[] values) throws IOException {

        int MAXNUMBEROFCHARSSNSUSERNUMBER = 9;
        return !values[0].isEmpty() && !values[1].isEmpty() && values[1].trim().matches("^[0-9]*$") && values[1].length() == MAXNUMBEROFCHARSSNSUSERNUMBER
                && !values[2].isEmpty() && Utils.validateEmail(values[2]) && !values[3].isEmpty() && Utils.validateBirthDate(values[3]) &&
                !values[4].isEmpty() && Utils.validatePhoneNumber(values[4]) && Utils.validateSex(values[5]) && !values[6].isEmpty() && SNSUser.validateAddress(values[6])
                && !values[7].isEmpty() && Utils.validateCitizenCardNumber(values[7]);
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
            dto.snsUserNumber = Integer.parseInt(values[1]);
            dto.strEmail = values[2];
            dto.strBirthDate = values[3];
            dto.strPhoneNumber = values[4];
            dto.strSex = values[5];
            dto.strAddress = values[6];
            dto.strCitizenCardNumber = values[7];
            dto.strPassword = values[8];
            System.out.println();
            controller.createSNSUser(dto);
            createCounter++;
            System.out.println();
            if (controller.saveSNSUser(dto).equals("Not Saved because the data is duplicated")) {
                saveCounter++;
            }
        }
        System.out.printf("Saved %d Users out of %d",createCounter - saveCounter, createCounter);
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



