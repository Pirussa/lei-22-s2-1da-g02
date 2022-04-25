package app.ui.console;

import app.controller.SpecifyVaccineAndAdminProcessController;
import app.domain.model.VaccineType;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * US013 - Specify Vaccine and its Administration Process UI
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class SpecifyVaccineAndAdminProcessUI implements Runnable {


    public SpecifyVaccineAndAdminProcessUI() {
    }

    private SpecifyVaccineAndAdminProcessController ctrl = new SpecifyVaccineAndAdminProcessController();

    public void run() {

        if (!ctrl.getVaccineTypesList().isEmpty()) {
            Scanner sc = new Scanner(System.in);
            VaccineAndAdminProcessDto dto = new VaccineAndAdminProcessDto();
            System.out.println("------Specify Vaccine and its Administration Process------");
            System.out.println();


            System.out.println("--What's the vaccine type?");
            ArrayList<VaccineType> vTs = ctrl.getVaccineTypesList();
            int options = 1;
            for (VaccineType vt : vTs) {
                System.out.printf("%d- %s ", options, vt);
                options++;
            }

            int option = sc.nextInt();
            sc.nextLine();
            if (option >= 1 && option <= options) {
                dto.vt = vTs.get(option - 1);
            } else {
                System.out.println("Insert a valid option");
            }


            System.out.println("--What's the vaccine id?");
            int check = 0;

            do {
                try {
                    dto.id = sc.nextInt();
                    sc.nextLine();
                    check = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Insert a valid Vaccine ID:");
                    sc.nextLine();
                }
            } while (check == 0);


            System.out.println("--What's the vaccine name?");
            dto.name = sc.next();
            sc.nextLine();


            System.out.println("--What's the vaccine brand?");
            dto.brand = sc.next();
            sc.nextLine();


            System.out.println();


            int keepGoing = 1;
            ArrayList<Integer> minAges = new ArrayList<>();
            ArrayList<Integer> maxAges = new ArrayList<>();
            ArrayList<Integer> intervalFirstDose = new ArrayList<>();
            ArrayList<Integer> intervalSecondDose = new ArrayList<>();

            do {

                System.out.println();
                System.out.println("------Define the age group------ ");
                System.out.println();
                System.out.println("Insert minimum age of the group: ");
                check = 0;
                do {
                    try {
                        minAges.add(sc.nextInt());
                        sc.nextLine();
                        check = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Insert a valid age:");
                        sc.nextLine();
                    }
                } while (check == 0);
                check = 0;
                System.out.println("Insert maximum age of the group: ");
                do {
                    try {
                        maxAges.add(sc.nextInt());
                        sc.nextLine();
                        check = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Insert a valid age:");
                        sc.nextLine();
                    }
                } while (check == 0);


                System.out.println();
                System.out.println("Insert the number of doses for the respective age group: ");
                int numberOfDoses = 0;
                check = 0;
                do {
                    try {
                        numberOfDoses = sc.nextInt();
                        dto.numberOfDoses.add(numberOfDoses);
                        sc.nextLine();
                        check = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Insert a valid number:");
                        sc.nextLine();
                    }
                } while (check == 0);


                System.out.println("Insert the dosage for the respective age group: ");
                check = 0;
                do {
                    try {
                        dto.dosage.add(sc.nextInt());
                        sc.nextLine();
                        check = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Insert a valid dosage:");
                        sc.nextLine();
                    }
                } while (check == 0);


                if (numberOfDoses == 2) {
                    System.out.println("Insert the interval (days**) between the 1st and 2nd vaccine: ");
                    check = 0;
                    do {
                        try {
                            intervalFirstDose.add(sc.nextInt());
                            intervalSecondDose.add(null);
                            sc.nextLine();
                            check = 1;
                        } catch (InputMismatchException e) {
                            System.out.println("Insert a valid dosage:");
                            sc.nextLine();
                        }
                    } while (check == 0);


                } else {
                    System.out.println("Insert the interval (days**) between the 1st and 2nd vaccine: ");
                    check = 0;
                    do {
                        try {
                            intervalFirstDose.add(sc.nextInt());
                            sc.nextLine();
                            check = 1;
                        } catch (InputMismatchException e) {
                            System.out.println("Insert a valid dosage:");
                            sc.nextLine();
                        }
                    } while (check == 0);

                    System.out.println("Insert the interval (days**) between the 2nd and 3rd vaccine: ");
                    check = 0;
                    do {
                        try {
                            intervalSecondDose.add(sc.nextInt());
                            sc.nextLine();
                            check = 1;
                        } catch (InputMismatchException e) {
                            System.out.println("Insert a valid dosage:");
                            sc.nextLine();
                        }
                    } while (check == 0);

                }

                System.out.println();
                System.out.println("Do you want to add another age group?");
                System.out.println("1 - Yes");
                System.out.println("0 - No");
                int answer = -1;
                check = 0;
                do {
                    try {
                        answer = sc.nextInt();
                        sc.nextLine();
                        check = 1;
                    } catch (InputMismatchException e) {
                        System.out.println("Insert a valid option:");
                        sc.nextLine();
                    }
                } while (check == 0);


                if (answer == 0) {
                    keepGoing = 0;
                }

            } while (keepGoing == 1);
            dto.ageGroups.add(minAges);
            dto.ageGroups.add(maxAges);
            dto.timeIntervalBetweenVaccines.add(intervalFirstDose);
            dto.timeIntervalBetweenVaccines.add(intervalSecondDose);

            if (ctrl.specifyNewVaccineAndAdminProcess(dto)) {
                if (confirmCreation(dto)){
                    ctrl.saveVaccine();

                }
            }


        } else {
            System.out.println("There are no Vaccine Types yet. Please add at least one Vaccine Type first.");
        }


    }


    /**
     * Shows the User all the data and asks for its confirmation.
     *
     * @return true if the User confirms the creation of the Vaccine.
     */
    public boolean confirmCreation(VaccineAndAdminProcessDto dto) {
        System.out.println();
        System.out.println();
        System.out.println("---- The new vaccine: ----");
        System.out.println("Name: " + dto.name);
        System.out.println("Id: " + dto.id);
        System.out.println("Brand: " + dto.brand);
        System.out.println("Vaccine Type: " + dto.vt);
        System.out.println();
        System.out.println("---- Its Administration Process: ----");
        for (int i = 0; i < dto.ageGroups.size(); i++) {
            System.out.printf("From ages %d to %d :%n", dto.ageGroups.get(0).get(i), dto.ageGroups.get(1).get(i));
            System.out.println("Number of doses:" + dto.numberOfDoses.get(i));
            System.out.println("Dosage: " + dto.dosage.get(i) + "ml");
            if (dto.numberOfDoses.get(i) == 2) {
                System.out.println("Interval between 1st and 2nd dose: " + dto.timeIntervalBetweenVaccines.get(0).get(i) + " days");
            } else {
                System.out.println("Interval between 1st and 2nd dose: " + dto.timeIntervalBetweenVaccines.get(0).get(i) + " days");
                System.out.println("Interval between 2nd and 3rd dose: " + dto.timeIntervalBetweenVaccines.get(1).get(i) + " days");

            }

        }
        System.out.println("Do you confirm this data?");
        System.out.println("1 - Yes");
        System.out.println("0 - No");
        Scanner sc = new Scanner(System.in);

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

        return sc.nextInt() == 1;
    }
}
