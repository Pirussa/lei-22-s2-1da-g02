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
        // Start User Storie
        vaccineAdministrationPrompt(Constants.VACCINE_ADMINISTRATION);

        // Select Vaccination Center
        int vaccinationCenterIndexInList = Utils.selectVaccinationCenterIndex();
        controller.setVaccinationCenter(vaccinationCenterIndexInList);

        // Select User from Waiting Room List
        firstUserWaitingRoom();
        int options = Utils.selectFromList(List.of(Constants.OPTIONS), "Consult Waiting Room List");
        int selectUser = waitingRoomList(options);

        // Select a Vaccine (Verifies if it matches the Vaccine Type)
        controller.setVaccineType(selectUser);
        int vaccineHistory = controller.findLastDoseOfVaccineType();
        setDosageAndVaccine(vaccineHistory);
    }

    private int waitingRoomList(int options) {
        if (options == Constants.FIRST_USER_WAITING_ROOM) {
            int selectedUser = (Utils.selectFromList(controller.fillListWithUserSnsNumber().subList(1, controller.fillListWithUserSnsNumber().size()), "\nUsers") + 1);
            SnsUserDto snsUserDto = controller.getSnsUserInformation(selectedUser);
            controller.setSnsUser(snsUserDto);
            return selectedUser;
        } else {
            SnsUserDto snsUserDto = controller.getSnsUserInformation(Constants.FIRST_USER_WAITING_ROOM);
            controller.setSnsUser(snsUserDto);
            return Constants.FIRST_USER_WAITING_ROOM;
        }
    }

    private int userFirstDose() {
        int vaccineIndexInList;
        if (controller.findLastDoseOfVaccineType() == Constants.FIRST_DOSE) {
            do {
                vaccineIndexInList = Utils.selectFromList(controller.vaccineTypeAvailableVaccines(), "Select a Vaccine: ");
            } while (controller.userFirstDoseAgeGroup(vaccineIndexInList) == Constants.INVALID_VALUE);
            //If the user doesn´t fit in any of the age groups.
            return Constants.FIT_AGE_GROUP;
        } else {
            do {
                vaccineIndexInList = Utils.selectFromList(controller.vaccineTypeAvailableVaccines(), "Select a Vaccine: ");
            } while (controller.userSuitsAgeGroup(controller.findLastDoseOfVaccineType()) == Constants.INVALID_VALUE);
            return vaccineIndexInList;
        }
    }

    private void setDosageAndVaccine(int vaccineHistory) {
        if (vaccineHistory != Constants.FIRST_DOSE) {
            int numberOfDoses = controller.getUserNumberOfDoses();
            int currentAppointment = controller.findLastDoseOfVaccineType();
            controller.setVaccine(currentAppointment);
            vaccineAdministrationPrompt(Constants.VACCINATION);
            vaccineAndVaccineTypeInfo();
            System.out.println(controller.vaccineAdministrationProcess(numberOfDoses, currentAppointment));
        } else {
            int vaccineIndex = userFirstDose();
            if (vaccineIndex != Constants.FIT_AGE_GROUP) {
                controller.setVaccine(vaccineIndex);
                vaccineAdministrationPrompt(Constants.VACCINATION);
                vaccineAndVaccineTypeInfo();
                System.out.printf("Dosage: " + Constants.DOSAGE_FIRST_DOSE + "ml%n");
            }
        }
    }

    private void vaccineAndVaccineTypeInfo() {
        System.out.printf("%n" + controller.vaccineTypeInfo());
        System.out.println(controller.vaccineInfo());
    }

    private void firstUserWaitingRoom() {
        System.out.printf("%nUser Information: %n%n" + controller.fillListWithUserSnsNumber().get(Constants.FIRST_USER_WAITING_ROOM) + "%n%n");
    }

    private void vaccineAdministrationPrompt(int prompt) {
        if (prompt == Constants.VACCINE_ADMINISTRATION)
            System.out.printf("%n------------------------%n|Vaccine Administration|%n------------------------%n");
        else
            System.out.printf("%n-------------%n|Vaccination|%n-------------%n");
    }
}