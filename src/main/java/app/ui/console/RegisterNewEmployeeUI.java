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
        int option = 2;
        RegisterNewEmployeeDto dto = new RegisterNewEmployeeDto();
        System.out.printf("%n-----------------------%n|Employee Registration|%n-----------------------%n%n");

        ArrayList<String> roles = new ArrayList<>();
        roles.add("Nurse");
        roles.add("Receptionist");
        roles.add("Center Coordinator");

        int index = Utils.showAndSelectIndex(roles, "Select a Role: ");
        String selectedRole = roles.get(index);

        dto.id = String.valueOf(Employee.idGenerator(selectedRole));
        dto.password = Employee.passwordGenerator();
        dto.name = Utils.readLineFromConsole("- Insert Name: ");
        dto.address = Utils.readLineFromConsole("- Insert Address (Street, Zip Code, Location): ");
        dto.phoneNumber = Utils.readLineFromConsole("- Insert Phone Number (9 chars, only numbers): ");
        dto.email = Utils.readLineFromConsole("- Insert Email (@ and . are required): ");
        dto.citizenCardNumber = Utils.readLineFromConsole("- Insert Citizen Card Number (Valid Format - XXXXXXXX X LLX): ");

        if (ctrl.registerNewEmployee(dto)) {
            showNewEmployeeData(dto, selectedRole);
            if (Utils.confirmCreation()) {
                ctrl.saveCreatedEmployee(dto, selectedRole);
                System.out.printf("%n-----------------------------%n|The Employee was registered|%n-----------------------------%n");
            } else
                System.out.printf("%n---------------------------------%n|The Employee was not registered|%n---------------------------------%n");
        } else {
            System.out.printf("%n-------------------------------%n|Invalid Data for the Employee|%n-------------------------------%n");
            showNewEmployeeData(dto, selectedRole);
        }
    }

    public void showNewEmployeeData(RegisterNewEmployeeDto dto, String selectedRole) {
        System.out.printf("%n----------------------------------------------------------------------------%n");
        System.out.printf("%n---------------------%n|Employee Given Data|%n---------------------%n%n- Selected Role: |%s|%n%n- Given Name: |%s|%n%n- Given Address: |%s|%n%n- Given Phone Number: |%s|%n%n- Given Email: |%s|%n%n- Given Citizen Card Number: |%s|%n%n- Generated ID: |%s|%n%n- Generated Password: |%s|%n%n", selectedRole, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.id, dto.password);
    }
}
