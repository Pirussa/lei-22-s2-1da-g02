package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterUI implements Runnable {

    public CreateVaccinationCenterUI() {
    }

    public void run() {
        Scanner choice = new Scanner(System.in);
        System.out.println();
        System.out.println("--------------CHOOSE THE TYPE:--------------");
        System.out.println("0 - Mass Vaccination Center");
        System.out.println("1 - Healthcare Center");
        System.out.println("2 - Get a list of all all centers created");
        System.out.println("3 - Go Back");
        System.out.println();
        System.out.println("Choose the option:");
        System.out.println();
        int typeOfCenter = choice.nextInt();
        if (typeOfCenter == 0) {
            massVaccinationCenterUI(typeOfCenter);
        } else if (typeOfCenter == 1) {
            healthcareCenterUI(typeOfCenter);
        } else if (typeOfCenter == 2) {
            getListOfVaccinationCentersUI();
        } else if (typeOfCenter == 3) {
            return;
        } else {
            System.out.println("Option is Invalid.");
        }
    }

    public void getListOfVaccinationCentersUI() {
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        if (!controller.getVaccinationCenters().isEmpty()) {
            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                System.out.println("\nPosition " + i + ": " + "\n" + controller.getVaccinationCenters().get(i));
                System.out.println();
            }
        } else {
            System.out.println();
            System.out.println("There aren't registered centers of any kind.");
        }
    }

    public void massVaccinationCenterUI(int typeOfCenter) {
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!(controller.getCenterCoordinatorIDs().isEmpty()) && !(controller.getVaccineTypeList().isEmpty())) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            MassVaccinationCenterDto dto = new MassVaccinationCenterDto();
            dto.strID = String.valueOf(idGeneratorMass(typeOfCenter));
            dto.strName = Utils.readLineFromConsole("Name of the Mass Vaccination Center: ");
            dto.strPhoneNumber = Utils.readLineFromConsole("Phone Number of the Mass Vaccination Center (Portuguese rules apply): ");
            dto.strEmail = Utils.readLineFromConsole("Email of the Mass Vaccination Center (Needs an @, a . and a valid domain): ");
            dto.strFax = Utils.readLineFromConsole("Fax Number of the Mass Vaccination Center (Same rules as Phone Numbers): ");
            dto.strWebsite = Utils.readLineFromConsole("Website address of the Mass Vaccination Center (Needs a valid prefix and domain): ");
            dto.strOpeningHour = Utils.readLineFromConsole("Opening hour of the Mass Vaccination Center (Between 0 and 24, < Closing Hour): ");
            dto.strClosingHour = Utils.readLineFromConsole("Closing hour of the Mass Vaccination Center (Between 0 and 24, > Opening Hour): ");
            dto.strSlotDuration = Utils.readLineFromConsole("Slot duration (In minutes, no more than three numerical chars): ");
            dto.strVaccinesPerSlot = Utils.readLineFromConsole("Maximum number of vaccines per slot (No more than three numerical chars): ");
            System.out.println();
            System.out.println("Information about the Mass Vaccination Center Address: ");
            dto.strRoad = Utils.readLineFromConsole("Road of the Mass Vaccination Center: ");
            dto.strZipCode = Utils.readLineFromConsole("Zip Code of the Mass Vaccination Center (1234-123 format): ");
            dto.strLocal = Utils.readLineFromConsole("Local of the Mass Vaccination Center: ");
            System.out.println();
            System.out.println("Information about the Coordinator: ");
            System.out.println();
            System.out.println("Choose one Coordinator from the list that hasn't been chosen, type it's position.");
            System.out.println();

            for (int i = 0; i < controller.getCenterCoordinatorIDs().size(); i++) {
                System.out.println("Position " + i + ": " + controller.getCenterCoordinatorIDs().get(i));
            }
            int option = sc.nextInt();

            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                if (controller.getCenterCoordinatorIDs().get(option).equals(controller.getVaccinationCenters().get(i).getStrCenterCoordinatorID())) {
                    System.out.println();
                    throw new IllegalArgumentException("The chosen coordinator is already assigned to another center.");
                }
            }
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);

            System.out.println();
            System.out.println("Information about the Vaccine Type: ");
            System.out.println("Choose one vaccine type from the list, type it's position.");
            System.out.println();
            for (int i = 0; i < controller.getVaccineTypeList().size(); i++) {
                System.out.println("Position " + i + ": " + controller.getVaccineTypeList().get(i));
            }
            int vaccineTypeOption = sc.nextInt();
            dto.strVaccineType = controller.getVaccineTypeList().get(vaccineTypeOption).toString();

            controller.createMassVaccinationCenter(dto);
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println();
            System.out.println(dto);
            System.out.println();
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption.equals("Yes") || strOption.equals("y") || strOption.equals("YES") || strOption.equals("Y") || strOption.equals("yes")) {
                controller.saveMassVaccinationCenter(dto);
                System.out.println();
                System.out.println("The Mass Vaccination Center was saved.");
            } else {
                System.out.println();
                System.out.println("You chose not to save the Mass Vaccination Center.");
            }
        } else {
            System.out.println("Can't create a Mass Vaccination Center without a registered Center Coordinator and a Vaccine Type added onto the system.");
        }
    }

    public void healthcareCenterUI(int typeOfCenter) {
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!(controller.getCenterCoordinatorIDs().isEmpty()) && !(controller.getVaccineTypeList().isEmpty())) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            HealthcareCenterDto dto = new HealthcareCenterDto();
            dto.strID = String.valueOf(idGeneratorMass(typeOfCenter));
            dto.strName = Utils.readLineFromConsole("Name of the Healthcare Center (No Validation): ");
            dto.strPhoneNumber = Utils.readLineFromConsole("Phone Number of the Healthcare Center (Portuguese rules apply): ");
            dto.strEmail = Utils.readLineFromConsole("Email of the Healthcare Center (Needs an @, a . and a valid domain): ");
            dto.strFax = Utils.readLineFromConsole("Fax Number of the Healthcare Center (Same rules as Phone Numbers): ");
            dto.strWebsite = Utils.readLineFromConsole("Website address of the Healthcare Center (Needs a valid prefix and domain): ");
            dto.strOpeningHour = Utils.readLineFromConsole("Opening hour of the Healthcare Center (Between 0 and 24, < Closing Hour): ");
            dto.strClosingHour = Utils.readLineFromConsole("Closing hour of the Healthcare Center (Between 0 and 24, > Opening Hour): ");
            dto.strSlotDuration = Utils.readLineFromConsole("Slot duration (In minutes, no more than three numerical chars): ");
            dto.strVaccinesPerSlot = Utils.readLineFromConsole("Maximum number of vaccines per slot (No more than three numerical chars): ");
            System.out.println();
            System.out.println("Information about the Healthcare Center Address: ");
            dto.strRoad = Utils.readLineFromConsole("Road of the Healthcare Center: ");
            dto.strZipCode = Utils.readLineFromConsole("Zip Code of the Healthcare Center (1234-123 format): ");
            dto.strLocal = Utils.readLineFromConsole("Local of the Healthcare Center: ");
            dto.strARS = Utils.readLineFromConsole("Regional Health Administration of the Healthcare Center: ");
            dto.strAGES = Utils.readLineFromConsole("Grouping of the Healthcare Center: ");
            System.out.println();
            System.out.println("Information about the Coordinator");
            System.out.println();
            System.out.println("Choose one Coordinator from the list that hasn't been chosen, type it's position.");
            System.out.println();

            for (int i = 0; i < controller.getCenterCoordinatorIDs().size(); i++) {
                System.out.println("Position " + i + ": " + controller.getCenterCoordinatorIDs().get(i));
            }
            int option = sc.nextInt();

            for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                if (controller.getCenterCoordinatorIDs().get(option).equals(controller.getVaccinationCenters().get(i).getStrCenterCoordinatorID())) {
                    System.out.println();
                    throw new IllegalArgumentException("The chosen coordinator is already assigned to another center.");
                }
            }
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);

            System.out.println();
            System.out.println("Information about the Vaccine Type");
            System.out.println("Choose one vaccine type from the list, type it's position.");
            ArrayList<VaccineType> vts = controller.getVaccineTypeList();
            int optiontest = 0;

            do {
                System.out.println();
                System.out.println("Choose one:");

                for (int i = 1; i <= vts.size(); i++) {
                    System.out.println(i + " " + vts.get(i - 1));

                }
                System.out.println();
                System.out.println(0 + "- Stop");
                optiontest = sc.nextInt();
                if (optiontest != 0) {
                    dto.strVaccineType.add(controller.getVaccineTypeList().get(optiontest - 1).toString());
                    vts.remove(optiontest - 1);
                }

            } while (optiontest > 0 && vts.size() != 0);

            controller.createHealthcareCenter(dto);

            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println();
            System.out.println(dto);
            System.out.println();
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption.equals("Yes") || strOption.equals("y") || strOption.equals("YES") || strOption.equals("Y") || strOption.equals("yes")) {
                controller.saveHealthcareCenter(dto);
                System.out.println();
                System.out.println("The Healthcare Center was saved.");
            } else {
                System.out.println();
                System.out.println("You chose not to save the Healthcare Center.");
            }
        } else {
            System.out.println("Can't create a Healthcare Center without a registered Center Coordinator and a Vaccine Type added onto the system.");
        }
    }

    public static StringBuilder idGeneratorMass(int vaccinationCenterOption) {
        int ID_LENGTH = 3;
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

//
//            for (int i = 0; i < controller.getVaccineTypeList().size(); i++) {
//                System.out.println();
//                System.out.println("\nPosition " + i + ": " + controller.getVaccineTypeList().get(i));
//            }
//            System.out.println();
//            System.out.println("Type -1 to stop.");
//
//            int vaccineTypeOption = 0;
//            while (vaccineTypeOption != -1) {
//                vaccineTypeOption = sc.nextInt();
//                dto.strVaccineType.add(controller.getVaccineTypeList().get(vaccineTypeOption).toString());
//
//            }

