package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;

import java.util.Scanner;

public class CreateVaccinationCenterUI{

    private CreateVaccinationCenterController controller;

    private final int HEALTHCARECENTEROPTION = 1;
    private final int MASSVACCINATIONCENTEROPTION = 2

    private int intOption;

    public CreateVaccinationCenterUI(){
        controller = new CreateVaccinationCenterController();
    }

    public void run()
    {
        while(true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Choose the Following:");
            System.out.println("---------------------");
            System.out.println("1 - Create Healthcare Center");
            System.out.println("2 - Create Mass Vaccination Center");
            intOption = sc.nextInt();
            if ((intOption!= HEALTHCARECENTEROPTION) || (intOption!= MASSVACCINATIONCENTEROPTION)){
                System.out.println("Wrong argument, choose 1 or 2 only.");
            }
            else if (intOption==1){

            }

        }

    }


    private boolean typeHealthcareCenterData(){
        int intID= Utils.readIntegerFromConsole("ID of the Healthcare Center: ");
        String strName= Utils.readLineFromConsole("Name of the Healthcare Center: ");
        String strPhoneNumber= Utils.readLineFromConsole("Phone Number of the Healthcare Center: ");
        String strEmail= Utils.readLineFromConsole("Email of the Healthcare Center: ");
        String strFax= Utils.readLineFromConsole("Fax Number of the Healthcare Center: ");
        String strWebsite= Utils.readLineFromConsole("Website address of the Healthcare Center: ");
        String strOpeningHour= Utils.readLineFromConsole("Opening hour of the Healthcare Center: ");
        String strClosingHour= Utils.readLineFromConsole("Closing hour of the Healthcare Center: ");
        String strSlotDuration= Utils.readLineFromConsole("Slot duration: ");
        String strVaccinesPerSlot= Utils.readLineFromConsole("Vaccines per slot: ");
        String strARS= Utils.readLineFromConsole("Regional Health Administration: ");
        String strAGES= Utils.readLineFromConsole("Health Center Grouping: ");
        System.out.println("Information about the Healthcare Center Address: ");
        String strRoad= Utils.readLineFromConsole("Road of the Healthcare Center");
        String strZipCode= Utils.readLineFromConsole("Zip Code of the Healthcare Center");
        String strLocal= Utils.readLineFromConsole("Local of the Healthcare Center");
        System.out.println("Information about the Coordinator");
        String strRole= Utils.readLineFromConsole("Role of the Coordinator: ");
        int intCoordinatorID= Utils.readIntegerFromConsole("ID of the Coordinator: ");
        String strCoordinatorName= Utils.readLineFromConsole("Name of the Coordinator:");
        String strCoordinatorAddress= Utils.readLineFromConsole("Address of the Coordinator");
        int intCoordinatorPhoneNUmber= Utils.readIntegerFromConsole("Phone Number of the Coordinator");
        String strCoordinatorEmail= Utils.readLineFromConsole("Email of the Coordinator");
        int intCoordinatorCitizenCardNumber= Utils.readIntegerFromConsole("Citizen Card Number of the Coordinator");
        String strCoordinatorPassword= Utils.readLineFromConsole("Password of the Coordinator");

        return true;
    }

    private boolean typeMassVaccinationCenterData(){
        int intID= Utils.readIntegerFromConsole("ID of the Healthcare Center: ");
        String strName= Utils.readLineFromConsole("Name of the Healthcare Center: ");
        String strPhoneNumber= Utils.readLineFromConsole("Phone Number of the Healthcare Center: ");
        String strEmail= Utils.readLineFromConsole("Email of the Healthcare Center: ");
        String strFax= Utils.readLineFromConsole("Fax Number of the Healthcare Center: ");
        String strWebsite= Utils.readLineFromConsole("Website address of the Healthcare Center: ");
        String strOpeningHour= Utils.readLineFromConsole("Opening hour of the Healthcare Center: ");
        String strClosingHour= Utils.readLineFromConsole("Closing hour of the Healthcare Center: ");
        String strSlotDuration= Utils.readLineFromConsole("Slot duration: ");
        String strVaccinesPerSlot= Utils.readLineFromConsole("Vaccines per slot: ");
        System.out.println("Information about the Healthcare Center Address: ");
        String strRoad= Utils.readLineFromConsole("Road of the Healthcare Center");
        String strZipCode= Utils.readLineFromConsole("Zip Code of the Healthcare Center");
        String strLocal= Utils.readLineFromConsole("Local of the Healthcare Center");
        System.out.println("Information about the Coordinator");
        String strRole= Utils.readLineFromConsole("Role of the Coordinator: ");
        int intCoordinatorID= Utils.readIntegerFromConsole("ID of the Coordinator: ");
        String strCoordinatorName= Utils.readLineFromConsole("Name of the Coordinator:");
        String strCoordinatorAddress= Utils.readLineFromConsole("Address of the Coordinator");
        int intCoordinatorPhoneNUmber= Utils.readIntegerFromConsole("Phone Number of the Coordinator");
        String strCoordinatorEmail= Utils.readLineFromConsole("Email of the Coordinator");
        int intCoordinatorCitizenCardNumber= Utils.readIntegerFromConsole("Citizen Card Number of the Coordinator");
        String strCoordinatorPassword= Utils.readLineFromConsole("Password of the Coordinator");

        return true;
    }







}
