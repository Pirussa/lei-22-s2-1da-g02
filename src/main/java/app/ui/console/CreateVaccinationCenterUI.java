package app.ui.console;

import app.domain.model.VaccinationCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateVaccinationCenterUI implements Runnable{


    @Override
    public void run() {

        Scanner sc = new Scanner(System.in);
        System.out.print("------------------------------------");
        System.out.println("Choose a type of Vaccination Center:");
        System.out.println("0 - Mass Vacination Center");
        System.out.println("1 - Healthcare Center");

        int option = sc.nextInt();

        while (option!=0 || option!=1) {
            if (option == 0) {
                System.out.println("Type the requested information:");
            } else if (option == 1) {
                System.out.println("Type the requested information:");
            } else {
                throw new IllegalArgumentException("Wrong argument, type either 0 or 1.");
            }
        }




    }




}
