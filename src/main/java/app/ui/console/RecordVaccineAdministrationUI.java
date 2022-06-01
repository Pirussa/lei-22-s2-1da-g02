package app.ui.console;

import app.controller.RecordVaccineAdministrationController;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.SnsUserDto;

/**
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class RecordVaccineAdministrationUI implements Runnable {

    private final RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    @Override
    public void run() {
        // Select Vaccination Center
        int vaccinationCenterIndexInList = Utils.selectVaccinationCenterIndex();
        controller.setVaccinationCenter(vaccinationCenterIndexInList);

        // Get all User information using Dto
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

    private void printVaccineAdministrationProcess() {
        System.out.printf("%n----------------------------%n|Administration Information|%n----------------------------%n%n");
        //System.out.printf();
    }
}
