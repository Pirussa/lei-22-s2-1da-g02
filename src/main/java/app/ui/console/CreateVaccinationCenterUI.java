package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;
import org.apache.commons.lang3.text.StrLookup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        int typeOfCenter=choice.nextInt();
        if (typeOfCenter==0){
            massVaccinationCenterUI();
        } else if (typeOfCenter==1){
            healthcareCenterUI();
        } else {
            System.out.println("Option is Invalid.");
        }
    }

    public void massVaccinationCenterUI(){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!controller.getCenterCoordinatorIDs().isEmpty()) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            VaccinationCenterDto dto = new VaccinationCenterDto();
            dto.intID = Utils.readIntegerFromConsole("ID of the Healthcare Center (Only Integers): ");
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
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);
            controller.createVaccinationCenter(dto);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println(dto);
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption == "yes" || strOption == "y" || strOption == "YES" || strOption == "Y" || strOption == "Yes") {
                controller.saveVaccinationCenter(dto);
                /*for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                    System.out.println();
                    System.out.println("\nPosition" + i + ": " + controller.getVaccinationCenters().get(i));
                }
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("The Vaccination Center was saved into the list as you can see.");
            */} else {
                run();
            }
        } else {
            System.out.println("Can't create a Vaccination Center without a registered Center Coordinator.");
        }

}

    public void healthcareCenterUI(){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!controller.getCenterCoordinatorIDs().isEmpty()) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            VaccinationCenterDto dto = new VaccinationCenterDto();
            dto.intID = Utils.readIntegerFromConsole("ID of the Healthcare Center (Only Integers): ");
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
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);
            controller.createVaccinationCenter(dto);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println(dto);
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption == "yes" || strOption == "y" || strOption == "YES" || strOption == "Y" || strOption == "Yes") {
                controller.saveVaccinationCenter(dto);
              /* for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                    System.out.println();
                    System.out.println("\nPosition" + i + ": " + controller.getVaccinationCenters().get(i));
                }
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("The Vaccination Center was saved into the list as you can see.");
            */} else {
                run();
            }
        } else {
            System.out.println("Can't create a Vaccination Center without a registered Center Coordinator.");
        }
    }

    public void vaccinationCenterUI(){
        CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
        controller.fillListOfEmployeesWithAGivenRole();
        controller.centerCoordinatorIDList();
        if (!controller.getCenterCoordinatorIDs().isEmpty()) {

            Scanner sc = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            VaccinationCenterDto dto = new VaccinationCenterDto();
            dto.intID = Utils.readIntegerFromConsole("ID of the Healthcare Center (Only Integers): ");
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
            dto.strCenterCoordinatorID = controller.getCenterCoordinatorIDs().get(option);
            controller.createVaccinationCenter(dto);
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("----------------------PLEASE VERIFY THE DATA----------------------------------------");
            System.out.println(dto);
            System.out.println("----------------------CONFIRM DATA? (Y/N)-------------------------------------------");
            String strOption = sc1.nextLine();
            if (strOption == "yes" || strOption == "y" || strOption == "YES" || strOption == "Y" || strOption == "Yes") {
                controller.saveVaccinationCenter(dto);
               /* for (int i = 0; i < controller.getVaccinationCenters().size(); i++) {
                    System.out.println();
                    System.out.println("\nPosition" + i + ": " + controller.getVaccinationCenters().get(i));
                }
                System.out.println("---------------------------------------------------------------------------------");
                System.out.println("The Vaccination Center was saved into the list as you can see.");
            */} else {
                run();
            }
        } else {
            System.out.println("Can't create a Vaccination Center without a registered Center Coordinator.");
        }
    }
}

