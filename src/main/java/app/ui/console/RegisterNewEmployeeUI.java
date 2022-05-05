package app.ui.console;

import app.controller.RegisterNewEmployeeController;
import app.controller.App;
import app.domain.model.Employee;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RegisterNewEmployeeUI implements Runnable {

    public RegisterNewEmployeeUI() {
    }

    public void run() {

        Scanner read = new Scanner(System.in);
        RegisterNewEmployeeController ctrl = new RegisterNewEmployeeController();
        int option;
        RegisterNewEmployeeDto dto = new RegisterNewEmployeeDto();
        System.out.printf("%n--Employee Registration--%n%n");

        ArrayList<String> roles = new ArrayList<>();
        roles.add("Nurse");
        roles.add("Receptionist");
        roles.add("Center Coordinator");

        //do {
            int index = Utils.showAndSelectIndex(roles, "Select a Role: ");
            String selectedRole = roles.get(index);

            dto.id = String.valueOf(Employee.idGenerator(selectedRole));
            dto.password = Employee.passwordGenerator();

            dto.name = Utils.readLineFromConsole("Insert Name: ");
            dto.address = Utils.readLineFromConsole("Insert Address (Street, Zip Code, location): ");
            dto.phoneNumber = Utils.readLineFromConsole("Insert Phone Number (9 chars, only numbers): ");
            dto.email = Utils.readLineFromConsole("Insert Email (@ and . are required): ");
            dto.citizenCardNumber = Utils.readLineFromConsole("Insert Citizen Card Number (12 chars, 10 numbers and 2 letters): ");

            if (ctrl.registerNewEmployee(dto)) {
                showNewEmployeeData(dto, selectedRole);
                if (Utils.confirmCreation()) {
                    ctrl.saveCreatedEmployee(dto, selectedRole);
                    System.out.printf("%n%nThe Employee was registered.");
                } else
                    System.out.println("The Employee was not registered.");
            } else {
                System.out.println("Invalid Data for the Employee, please fix the issue if you want to register an Employee.");
                showNewEmployeeData(dto, selectedRole);
            }
       // } while (!ctrl.registerNewEmployee(dto));
    }
    public void showNewEmployeeData(RegisterNewEmployeeDto dto, String selectedRole) {
        System.out.printf("**New Employee Data**%n%nSelected Role: %s%n%nGiven Name: %s%n%nGiven Address: %s%n%nGiven Phone Number: %s%n%nGiven Email: %s%n%nGiven Citizen Card Number: %s%n%nGenerated ID: %s%n%nGenerated Password: %s%n%n", selectedRole, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.id, dto.password);
    }
}
