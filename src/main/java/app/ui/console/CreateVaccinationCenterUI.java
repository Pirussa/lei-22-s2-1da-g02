package app.ui.console;

import app.controller.CreateVaccinationCenterController;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import jdk.jshell.execution.Util;
import org.apache.commons.lang3.text.StrLookup;

import java.util.List;
import java.util.Scanner;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterUI{

    private final int HEALTHCARECENTEROPTION = 1;
    private final int MASSVACCINATIONCENTEROPTION = 2;

    private int intOption;
    VaccinationCenterDto dto = new VaccinationCenterDto();
    public CreateVaccinationCenterUI(){
    }

    private CreateVaccinationCenterController controller = new CreateVaccinationCenterController();
    Scanner sc = new Scanner(System.in);
    public void run(){
        dto.intID= Utils.readIntegerFromConsole("ID of the Healthcare Center: ");
        dto.strName= Utils.readLineFromConsole("Name of the Healthcare Center: ");
        dto.strPhoneNumber= Utils.readLineFromConsole("Phone Number of the Healthcare Center: ");
        dto.strEmail= Utils.readLineFromConsole("Email of the Healthcare Center: ");
        dto.strFax= Utils.readLineFromConsole("Fax Number of the Healthcare Center: ");
        dto.strWebsite= Utils.readLineFromConsole("Website address of the Healthcare Center: ");
        dto.strOpeningHour= Utils.readLineFromConsole("Opening hour of the Healthcare Center: ");
        dto.strClosingHour= Utils.readLineFromConsole("Closing hour of the Healthcare Center: ");
        dto.strSlotDuration= Utils.readLineFromConsole("Slot duration: ");
        dto.strVaccinesPerSlot= Utils.readLineFromConsole("Vaccines per slot: ");
        System.out.println("Information about the Healthcare Center Address: ");
        dto.strRoad= Utils.readLineFromConsole("Road of the Healthcare Center");
        dto.strZipCode= Utils.readLineFromConsole("Zip Code of the Healthcare Center");
        dto.strLocal= Utils.readLineFromConsole("Local of the Healthcare Center");
        System.out.println("Information about the Coordinator");
        dto.strCenterCoordinatorID= Utils.readLineFromConsole("ID of the Coordinator: ");
        controller.createVaccinationCenter(dto);
        System.out.println("--------------------");
        controller.getVaccinationCenters();
        System.out.println("Confirm Data");
        System.out.println();
        System.out.println("Yes/No");
        String confirmation = sc.nextLine();
        if (confirmation.contentEquals("Yes")||confirmation.contentEquals("yes")||confirmation.contentEquals("y")){
            controller.saveVaccinationCenter(dto);
        }
        System.out.println("Successful operation");



    /*public void run()
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
                typeHealthcareCenterData();
            }
            else if (intOption==2){
                typeMassVaccinationCenterData();
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

     */
}
}