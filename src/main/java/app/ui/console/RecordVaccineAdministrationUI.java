package app.ui.console;

import app.controller.RecordVaccineAdministrationController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.SnsUserDto;

import java.util.List;
import java.util.Scanner;

/**
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class RecordVaccineAdministrationUI implements Runnable {

    private final RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    private final Scanner read = new Scanner(System.in);

    @Override
    public void run() {
        // Select Vaccination Center
        int vaccinationCenterIndexInList = Utils.selectVaccinationCenterIndex();
        controller.setVaccinationCenter(vaccinationCenterIndexInList);

        // Get all User information using Dto
        System.out.printf("%n" + controller.fillListWithUserSnsNumber().get(Constants.FIRST_USER_WAITING_ROOM) + "%n%n");
        int options = Utils.selectFromList(List.of(Constants.OPTIONS), "Consult Waiting Room List");
        if (options == Constants.FIRST_USER_WAITING_ROOM) Utils.selectFromList(controller.fillListWithUserSnsNumber(), "Users");
        SnsUserDto snsUserDto = controller.getSnsUserInformation();
        controller.setSnsUser(snsUserDto);

        // Select a Vaccine (Verifies if it matches the Vaccine Type)
        controller.setUserScheduledVaccineType();
        do {
            int vaccineIndexInList = Utils.showAndSelectIndex(controller.vaccineTypeAvailableVaccines(), "Select a Vaccine: ");
        } while (controller.userSuitsAgeGroup(controller.findLastDoseOfVaccineType()) == Constants.INVALID_VALUE);

        int numberOfDoses = controller.getUserNumberOfDoses();
        int currentAppointment = controller.findLastDoseOfVaccineType();
        System.out.println(controller.vaccineAdministrationProcess(numberOfDoses, currentAppointment));

    }

    private void printUserNameAndSnsNumber() {

    }
}
