package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.ui.console.utils.Utils;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterUI implements Runnable {

    public CreateVaccinationCenterUI() {
    }

    public void run() {
        Scanner choice = new Scanner(System.in);
        System.out.println("--------------CHOOSE THE TYPE:--------------");
        System.out.println("0 - Mass Vaccination Center");
        System.out.println("1 - Healthcare Center");
        System.out.println("2 - Get a list of all all centers created");
        System.out.println("3 - Go Back");
        int typeOfCenter=choice.nextInt();
        if (typeOfCenter==0){
            massVaccinationCenterUI(typeOfCenter);
        } else if (typeOfCenter==1){
            healthcareCenterUI(typeOfCenter);
        } else if (typeOfCenter==2){
            getListOfVaccinationCentersUI();
        } else if (typeOfCenter==3){
            return;
        } else {
            System.out.println("Option is Invalid.");
        }
    }

    public void getListOfVaccinationCentersUI(){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        if (!controller.getVaccinationCenters().isEmpty()){
            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                System.out.println();
                System.out.println("\nPosition " + i + ": " + controller.getVaccinationCenters().get(i));
            }
        } else{
            System.out.println();
            System.out.println("There aren't registered centers of any kind");
        }
    }

    public void massVaccinationCenterUI(int typeOfCenter){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!controller.getCenterCoordinatorIDs().isEmpty()) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            MassVaccinationCenterDto dto = new MassVaccinationCenterDto();
            dto.strID = String.valueOf(idGeneratorMass(typeOfCenter));
            dto.strName = Utils.readLineFromConsole("Name of the Healthcare Center (No Validation): ");
            dto.strPhoneNumber = Utils.readLineFromConsole("Phone Number of the Healthcare Center (9 Chars, only numbers): ");
            dto.strEmail = Utils.readLineFromConsole("Email of the Healthcare Center (Needs to have @ and .): ");
            dto.strFax = Utils.readLineFromConsole("Fax Number of the Healthcare Center(No Validation): ");
            dto.strWebsite = Utils.readLineFromConsole("Website address of the Healthcare Center(Needs to have www. and one of the domain inside the vector): ");
            dto.strOpeningHour = Utils.readLineFromConsole("Opening hour of the Healthcare Center: ");
            dto.strClosingHour = Utils.readLineFromConsole("Closing hour of the Healthcare Center: ");
            dto.strSlotDuration = Utils.readLineFromConsole("Slot duration: ");
            dto.strVaccinesPerSlot = Utils.readLineFromConsole("Vaccines per slot: ");
            System.out.println("Information about the Healthcare Center Address: ");
            dto.strRoad = Utils.readLineFromConsole("Road of the Healthcare Center: ");
            dto.strZipCode = Utils.readLineFromConsole("Zip Code of the Healthcare Center: ");
            dto.strLocal = Utils.readLineFromConsole("Local of the Healthcare Center: ");
            System.out.println("Information about the Coordinator");
            System.out.println("Choose one Coordinator from the list, type the position you want.");

            for (int i = 0; i < controller.getCenterCoordinatorIDs().size(); i++) {
                System.out.println();
                System.out.println("\nPosition " + i + ": " + controller.getCenterCoordinatorIDs().get(i));
            }
            int option = sc.nextInt();

            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                if (controller.getCenterCoordinatorIDs().get(option).equals(controller.getVaccinationCenters().get(i).getStrID())) {
                    return;
                }
            }
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);
            controller.createMassVaccinationCenter(dto);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println(dto);
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption.equals("Yes") || strOption.equals("y") || strOption.equals("YES")  || strOption.equals("Y")  || strOption.equals("yes")) {
                controller.saveMassVaccinationCenter(dto);
                for (int i = 0; i < controller.getMassVaccinationCenters().size(); i++) {
                    System.out.println();
                    System.out.println("\nPosition" + i + ": " + controller.getMassVaccinationCenters().get(i));
                }
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("The Vaccination Center was saved into the list as you can see.");
            } else {
                run();
            }
        } else {
            System.out.println("Can't create a Vaccination Center without a registered Center Coordinator.");
        }

    }

    public void healthcareCenterUI(int typeOfCenter){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!controller.getCenterCoordinatorIDs().isEmpty()) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            HealthcareCenterDto dto = new HealthcareCenterDto();
            dto.strID = String.valueOf(idGeneratorMass(typeOfCenter));
            dto.strName = Utils.readLineFromConsole("Name of the Healthcare Center (No Validation): ");
            dto.strPhoneNumber = Utils.readLineFromConsole("Phone Number of the Healthcare Center (9 Chars, only numbers): ");
            dto.strEmail = Utils.readLineFromConsole("Email of the Healthcare Center (Needs to have @ and .): ");
            dto.strFax = Utils.readLineFromConsole("Fax Number of the Healthcare Center(No Validation): ");
            dto.strWebsite = Utils.readLineFromConsole("Website address of the Healthcare Center(Needs to have www. and one of the domain inside the vector): ");
            dto.strOpeningHour = Utils.readLineFromConsole("Opening hour of the Healthcare Center: ");
            dto.strClosingHour = Utils.readLineFromConsole("Closing hour of the Healthcare Center: ");
            dto.strSlotDuration = Utils.readLineFromConsole("Slot duration: ");
            dto.strVaccinesPerSlot = Utils.readLineFromConsole("Vaccines per slot: ");
            System.out.println("Information about the Healthcare Center Address: ");
            dto.strRoad = Utils.readLineFromConsole("Road of the Healthcare Center: ");
            dto.strZipCode = Utils.readLineFromConsole("Zip Code of the Healthcare Center: ");
            dto.strLocal = Utils.readLineFromConsole("Local of the Healthcare Center: ");
            dto.strARS = Utils.readLineFromConsole("ARS of the Healthcare Center: ");
            dto.strAGES = Utils.readLineFromConsole("AGES of the Healthcare Center: ");
            System.out.println("Information about the Coordinator");
            System.out.println("Choose one Coordinator ID from the list, type the position you want.");

            for (int i = 0; i < controller.getCenterCoordinatorIDs().size(); i++) {
                System.out.println();
                System.out.println("\nPosition " + i + ": " + controller.getCenterCoordinatorIDs().get(i));
            }
            int option = sc.nextInt();

            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                if (controller.getCenterCoordinatorIDs().get(option).equals(controller.getVaccinationCenters().get(i).getStrID())) {
                    return;
                }
            }
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);
            controller.createHealthcareCenter(dto);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println();
            System.out.println(dto);
            System.out.println();
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption.equals("Yes") || strOption.equals("y") || strOption.equals("YES")  || strOption.equals("Y")  || strOption.equals("yes")) {
                controller.saveHealthcareCenter(dto);
                for (int i = 0; i < controller.getHealthcareCenters().size(); i++) {
                    System.out.println();
                    System.out.println("\nPosition" + i + ": " + controller.getHealthcareCenters().get(i));
                }
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("The Healthcare Center was saved into the list as you can see.");
            } else {
                run();
            }
        } else {
            System.out.println("Can't create a Vaccination Center without a registered Center Coordinator.");
        }
    }

    public static StringBuilder idGeneratorMass(int vaccinationCenterOption) {
        int ID_LENGTH=3;
        StringBuilder orderedID = new StringBuilder();
        Random generate = new Random();

        for (int position = 0; position < ID_LENGTH; position++) {
            orderedID.append(String.valueOf(generate.nextInt(9)));
        }

        switch (vaccinationCenterOption) {
            case 0:
                orderedID = new StringBuilder("MVC-" + orderedID);
                break;
            case 1:
                orderedID = new StringBuilder("HC-" + orderedID);
                break;
        }
        return orderedID;
    }
}

