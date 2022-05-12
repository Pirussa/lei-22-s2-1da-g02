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
import java.text.DateFormat;
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

    /**
     * Validates Employee phone number.
     *
     * @param phoneNumber The Employee´s phone number.
     * @return true if Employee phone number is validated
     */

    static public boolean validatePhoneNumber(String phoneNumber) {

        if (phoneNumber.length() == Constants.NUMBER_OF_PHONE_NUMBER_DIGITS && Integer.parseInt(phoneNumber) % 1 == 0) {
            int ch1 = Integer.parseInt(String.valueOf(phoneNumber.charAt(0)));
            if (ch1 != Constants.STARTING_NUMBER_PORTUGUESE_PHONE)
                return false;

            int ch2 = Integer.parseInt(String.valueOf(phoneNumber.charAt(1)));
            if (ch2 != Constants.FIRST_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != Constants.SECOND_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != Constants.THIRD_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != Constants.FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Validates Employee citizen card number.
     *
     * @param citizenCardNumber The Employee´s citizen card number.
     * @return true if Employee citizen card number is validated
     */

    static public boolean validateCitizenCardNumber(String citizenCardNumber) {
        String noBlankSpotsCitizenCardNumber = citizenCardNumber.replaceAll("\\s", "");
        int sum = 0;
        if (noBlankSpotsCitizenCardNumber.length() != Constants.NUMBER_OF_CITIZEN_CARD_DIGITS)
            return false;

        boolean secondDigit = true;

        for (int digit = 0; digit < noBlankSpotsCitizenCardNumber.length(); digit++) {
            String toUpperCase = String.valueOf(noBlankSpotsCitizenCardNumber.charAt(digit)).toUpperCase();
            int value = getValueFromCitizenCardNumberDigit(toUpperCase);

            if (secondDigit) {
                value *= 2;

                if (value >= 10)
                    value -= 9;
            }
            sum += value;
            secondDigit = !secondDigit;
        }
        return (sum % Constants.FIRST_SECOND_DIGIT_CC) == 0;
    }

    /**
     * Gets the value of each char in citizen card number.
     *
     * @param letter char from the citizen card number.
     * @return integer with the correspondent value of a char
     */
    static public int getValueFromCitizenCardNumberDigit(String letter) {
        switch (letter) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "A":
                return 10;
            case "B":
                return 11;
            case "C":
                return 12;
            case "D":
                return 13;
            case "E":
                return 14;
            case "F":
                return 15;
            case "G":
                return 16;
            case "H":
                return 17;
            case "I":
                return 18;
            case "J":
                return 19;
            case "K":
                return 20;
            case "L":
                return 21;
            case "M":
                return 22;
            case "N":
                return 23;
            case "O":
                return 24;
            case "P":
                return 25;
            case "Q":
                return 26;
            case "R":
                return 27;
            case "S":
                return 28;
            case "T":
                return 29;
            case "U":
                return 30;
            case "V":
                return 31;
            case "W":
                return 32;
            case "X":
                return 33;
            case "Y":
                return 34;
            case "Z":
                return 35;
        }
        throw new IllegalArgumentException("Invalid Value in the Document.");
    }

    /**
     * Validates Employee email.
     *
     * @param email The Employee´s email.
     * @return true if Employee email is validated
     */

    static public boolean validateEmail(String email) {
        if (!email.contains("@") && !email.contains("."))
            return false;

        String[] emailSplitter = email.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt", "outlook.com"};

        for (int position = 0; position < validEmailDomain.length; position++) {
            if (Objects.equals(emailSplitter[1], validEmailDomain[position]))
                return true;
        }
        return false;
    }

    /**
     * Validates Employee address.
     *
     * @param address The Employee´s address.
     * @return true if Employee address is validated
     */

    static public boolean validateAddress(String address) {
        String[] splitAddress = address.split("/");
        if (splitAddress.length != 3)
            return false;

        String zipCode = splitAddress[1].trim();
        if (zipCode.length() != 8 || zipCode.charAt(4) != '-')
            return false;

        return true;
    }
    static public boolean validateSex(String strSex){
        if (strSex.equals("Male")||strSex.equals("Female")||strSex.isEmpty()){
            return true;
        } else return false;
    }

    static public boolean validateBirthDate(String strBirthDate) {
        String dateFormat= "dd/MM/yyyy";
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(strBirthDate);
        } catch (ParseException e) {
            return false;
        }
        return true;
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


    static public String passwordGenerator() {
        final int PASSWORD_LENGTH = 7;
        final String alphabetLetters = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ0123456789";
        StringBuilder password = new StringBuilder();
        Random generate = new Random();
        ArrayList<String> randomAlphanumerics = new ArrayList<>();
        StringBuilder employeePassword = new StringBuilder();

        for (int position = 0; position < PASSWORD_LENGTH; position++) {
            if (position <= 2)
                password.append(Character.toUpperCase(alphabetLetters.charAt(generate.nextInt(25))));
            else if (position <= 4)
                password.append(String.valueOf(generate.nextInt(9)));
            else
                password.append(alphabetLetters.charAt(generate.nextInt(alphabetLetters.length())));
        }

        for (int position = 0; position < PASSWORD_LENGTH; position++) {
            int index = (generate.nextInt(password.length()));
            char passwordAux = password.charAt(index);
            employeePassword.append(passwordAux);
            password.deleteCharAt(index);
        }
        return String.valueOf(employeePassword);
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
