package app.ui.console.utils;

import app.controller.App;
import app.controller.SpecifyNewVaccineTypeController;
import app.domain.model.AdministrationProcess;
import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;

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
        String input= "";
        Integer value = -1;
        do {
            try{
                input = Utils.readLineFromConsole("Type your option: ");
                value = Integer.valueOf(input);
            }catch (NumberFormatException e){
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

    public static AdministrationProcess createAdminProcess() {

        ArrayList<Integer> minAge = new ArrayList<>(List.of(1));
        ArrayList<Integer> maxAge = new ArrayList<>(List.of(9));
        ArrayList<ArrayList<Integer>> ageGroup = new ArrayList<>(Arrays.asList(minAge, maxAge));

        ArrayList<Integer> numberOfDoses = new ArrayList<>(List.of(2));
        ArrayList<Double> dosage = new ArrayList<>(List.of(Double.parseDouble("20.0")));
        ArrayList<Integer> intervalBetweenVac1st2nd = new ArrayList<>(List.of(15));
        ArrayList<ArrayList<Integer>> intervalBetweenVaccines = new ArrayList<>(Arrays.asList(intervalBetweenVac1st2nd));
        AdministrationProcess adminProcess = new AdministrationProcess(ageGroup, numberOfDoses, dosage, intervalBetweenVaccines);

        return adminProcess;
    }

    public static Vaccine createVaccine(String name, int id, String brand, double dosage, int minAge, int maxAge, int timeBetweenDoses) {
        Company c = App.getInstance().getCompany();
        bootstrapOptional();
        AdministrationProcess aP = new AdministrationProcess(new ArrayList<>(Arrays.asList(new ArrayList<>(List.of(minAge)), new ArrayList<>(List.of(maxAge)))),new ArrayList<>(List.of(2)),new ArrayList<>(List.of(dosage)),new ArrayList<>(Arrays.asList(new ArrayList<>(List.of(timeBetweenDoses)))));

        Vaccine v = new Vaccine(name,id,brand,aP,c.getVaccineTypes().get(0));


        return v;
    }
    public static void bootstrapOptional() {
        SpecifyNewVaccineTypeController ctrl = new SpecifyNewVaccineTypeController();

        ctrl.saveVaccineType("AAAAA", "Vaccine Type 1", VaccineType.vaccineTechnologies[0]);
        ctrl.saveVaccineType("BBBBB", "Vaccine Type 2", VaccineType.vaccineTechnologies[1]);
        ctrl.saveVaccineType("CCCCC", "Vaccine Type 3", VaccineType.vaccineTechnologies[2]);

    }
}
