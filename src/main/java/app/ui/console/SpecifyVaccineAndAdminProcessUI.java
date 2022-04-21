package app.ui.console;

import app.controller.SpecifyVaccineAndAdminProcessController;

import java.util.Scanner;


/**
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class SpecifyVaccineAndAdminProcessUI implements Runnable {

     private SpecifyVaccineAndAdminProcessController ctrl;


    public SpecifyVaccineAndAdminProcessUI() {
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("------Specify Vaccine and its Administration Process------");
        System.out.println();
        System.out.println("What's the vaccine name?");
        String name = sc.next();
        sc.nextLine();
        VaccineAndAdminProcessDto dto = new VaccineAndAdminProcessDto();

        int keepGoing = 1;

        do {

            System.out.println();
            System.out.println("------Define the age group------ ");
            System.out.println();
            System.out.println("Insert minimum age of the group: ");
            int minAge = sc.nextInt();
            sc.nextLine();
            System.out.println("Insert maximum age of the group: ");
            int maxAge = sc.nextInt();

            dto.ageGroups.add(minAge, maxAge);

            System.out.println();
            System.out.println("Insert the number of doses for the respective age group: ");
            int numberOfDoses = sc.nextInt();
            dto.numberOfDoses.add(numberOfDoses);
            System.out.println();
            System.out.println("Insert the dosage for the respective age group: ");
            dto.numberOfDoses.add(sc.nextInt());


            if(numberOfDoses == 2){
                System.out.println();
                System.out.println("Insert the interval (days**) between the 1st and 2nd vaccine: ");
                dto.timeIntervalBetweenVaccines.add(sc.nextInt());
            }else{
                System.out.println("Insert the interval (days**) between the 1st and 2nd vaccine: ");

                System.out.println("Insert the interval (days**) between the 2nd and 3rd vaccine: ");
                dto.timeIntervalBetweenVaccines.add(sc.nextInt(),sc.nextInt());
            }


            System.out.println();
            System.out.println("Do you want to add another age group?");
            System.out.println("1 - Yes");
            System.out.println("0 - No");
            int answer = sc.nextInt();
            sc.nextLine();

            if (answer == 0) {
                keepGoing = 0;
            }

        } while (keepGoing == 1);


    }
}
