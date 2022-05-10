package app.ui.console.utils;

import app.controller.App;
import app.controller.CreateVaccinationCenterController;
import app.controller.RegisterNewEmployeeController;
import app.controller.SpecifyNewVaccineTypeController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.HealthcareCenterDto;
import app.ui.console.MassVaccinationCenterDto;
import app.ui.console.RegisterNewEmployeeDto;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.print("\n" + prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                int value = Integer.parseInt(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public double readDoubleFromConsole(String prompt) {
        do {
            try {
                String input = readLineFromConsole(prompt);

                double value = Double.parseDouble(input);

                return value;
            } catch (NumberFormatException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message + "\n");
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("s");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
        System.out.println("");
        System.out.println("0 - Cancel");
    }

    static public Object selectsObject(List list) {
        String input;
        Integer value;
        do {
            input = Utils.readLineFromConsole("Type your option: ");
            value = Integer.valueOf(input);
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        String input = "";
        Integer value = -1;
        do {
            try {
                input = Utils.readLineFromConsole("Type your option: ");
                value = Integer.valueOf(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option");
            }
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Asks the User for its confirmation.
     *
     * @return true if the User confirms the creation of the Vaccine.
     */
    public static boolean confirmCreation() {
        System.out.println("Do you confirm this data?");
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


    public static Vaccine createVaccine(String name, int id, String brand, double dosage, int minAge, int maxAge, int timeBetweenDoses) {
        Company c = App.getInstance().getCompany();
        bootstrapOptional();
        AdministrationProcess aP = new AdministrationProcess(new ArrayList<>(Arrays.asList(new ArrayList<>(List.of(minAge)), new ArrayList<>(List.of(maxAge)))), new ArrayList<>(List.of(2)), new ArrayList<>(List.of(dosage)), new ArrayList<>(Arrays.asList(new ArrayList<>(List.of(timeBetweenDoses)))));

        Vaccine v = new Vaccine(name, id, brand, aP, c.getVaccineTypes().get(0));


        return v;
    }

    public static void bootstrapOptional() {

        SpecifyNewVaccineTypeController ctrlVt = new SpecifyNewVaccineTypeController();

        ctrlVt.saveVaccineType("COVID", "A vaccine to prevent serious infections of the Covid-19 Virus", VaccineType.vaccineTechnologies[5]);
        ctrlVt.saveVaccineType("FLU22", "A vaccine to prevent serious infections of the Flu virus related to 2022 variant", VaccineType.vaccineTechnologies[1]);
        ctrlVt.saveVaccineType("CCCCC", "Vaccine Type 3", VaccineType.vaccineTechnologies[2]);

        RegisterNewEmployeeController ctrlEmp = new RegisterNewEmployeeController();

        RegisterNewEmployeeDto dtoEmp = new RegisterNewEmployeeDto();
        dtoEmp.id = "CC-95634";
        dtoEmp.name = "João";
        dtoEmp.password = "AAA22vx";
        dtoEmp.phoneNumber = "915604427";
        dtoEmp.citizenCardNumber = "11960343 8 ZW1";
        dtoEmp.email = "joao@gmail.com";
        dtoEmp.address = "Via Diagonal / 4475-079 / Porto";
        ctrlEmp.saveCreatedEmployee(dtoEmp, "Center Coordinator");

        RegisterNewEmployeeDto dtoEmp1 = new RegisterNewEmployeeDto();
        dtoEmp1.id = "CC-92634";
        dtoEmp1.name = "Francisca";
        dtoEmp1.password = "ah56BCC";
        dtoEmp1.phoneNumber = "919700873";
        dtoEmp1.citizenCardNumber = "14268862 2 ZX8";
        dtoEmp1.email = "francisca@gmail.com";
        dtoEmp1.address = "Rua de São Tomé / 4200-489 / Porto";
        ctrlEmp.saveCreatedEmployee(dtoEmp1, "Center Coordinator");

        RegisterNewEmployeeDto dtoEmp2 = new RegisterNewEmployeeDto();
        dtoEmp2.id = "NR-91272";
        dtoEmp2.name = "Joana";
        dtoEmp2.password = "fv93ACK";
        dtoEmp2.phoneNumber = "919880654";
        dtoEmp2.citizenCardNumber = "38002291 5 ZY5";
        dtoEmp2.email = "joana@gmail.com";
        dtoEmp2.address = "Rua De Azevedo De Albuquerque / 4050-076 / Porto";
        ctrlEmp.saveCreatedEmployee(dtoEmp2, "Nurse");

        RegisterNewEmployeeDto dtoEmp3 = new RegisterNewEmployeeDto();
        dtoEmp3.id = "RC-12345";
        dtoEmp3.name = "Carla";
        dtoEmp3.password = "AAA12aa";
        dtoEmp3.phoneNumber = "912345678";
        dtoEmp3.citizenCardNumber = "15925823 5 ZX3";
        dtoEmp3.email = "carla@gmail.com";
        dtoEmp3.address = "Rua Carlos Kimbo Slice / 4440-123 / Porto";
        ctrlEmp.saveCreatedEmployee(dtoEmp3, "Receptionist");

        CreateVaccinationCenterController ctrlVc= new CreateVaccinationCenterController();
        MassVaccinationCenterDto mvcDto= new MassVaccinationCenterDto();
        mvcDto.strID= "1234";
        mvcDto.strName="CVC Matosinhos";
        mvcDto.strPhoneNumber ="915607321";
        mvcDto.strFax ="915607321";
        mvcDto.strEmail= "cvcmatosinhos@gmail.com";
        mvcDto.strClosingHour= "20";
        mvcDto.strOpeningHour= "8";
        mvcDto.strVaccinesPerSlot= "5";
        mvcDto.strSlotDuration ="20";
        mvcDto.strWebsite=  "www.cvcmatosinhos.com";
        mvcDto.strRoad =  "Rua do Amial";
        mvcDto.strZipCode= "4460-098";
        mvcDto.strLocal =  "Matosinhos";
        mvcDto.strCenterCoordinatorID ="CC-95634";
        mvcDto.strVaccineType = "COVID";

        ctrlVc.saveMassVaccinationCenter(mvcDto);

        Company c = App.getInstance().getCompany();
        c.getAuthFacade().addUserWithRole("UserDefault","user@gmail.com","123", Constants.ROLE_SNS_USER);


        HealthcareCenterDto hCc = new HealthcareCenterDto("1236", "Centro de Saude da Maia", "945372312", "csmaia@gmail.com", "945372312", "www.csmaia.com", "9", "17", "15", "3", "Rua da Escola", "4470-073", "Maia", "CC-92634", "Norte","SNS",new ArrayList<>(List.of("COVID","FLU22")));

    }
}
