package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccinationController;
import app.controller.ScheduledVaccineController;
import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationUI implements Runnable {
    private final ScheduleVaccinationController ctrl = new ScheduleVaccinationController();
    private static Company company = App.getInstance().getCompany();

    public ScheduleVaccinationUI() {
    }

    public void run() {
        if (Utils.arrayListIsEmpty(company.getVaccineTypes(), company.getVaccinationCenters(), company.getSNSUserList())) {
            int snsNumber;
            do {
                snsNumber = Integer.parseInt( Utils.readLineFromConsole("Introduce SNS Number: "));
            } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.getUserIndexInUsersList(snsNumber) < 0);

            int selectedVaccinationCenterIndexInArrayList = Utils.showAndSelectIndex(company.getVaccinationCenters(), "Available Vaccination Centers: ");
            if (selectedVaccinationCenterIndexInArrayList == Constants.INVALID_VALUE) return;
            VaccinationCenter selectedVaccinationCenter = company.getVaccinationCenters().get(selectedVaccinationCenterIndexInArrayList);

            VaccineType vaccineType = ScheduleVaccineUI.selectVaccineTypeUI(selectedVaccinationCenter);
            if (vaccineType == null) return;

            LocalDateTime dateTime = ScheduleVaccineUI.selectDateUI(selectedVaccinationCenter);

            ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
            scheduledVaccineDto.snsNumber = snsNumber;
            scheduledVaccineDto.vaccineType = vaccineType;
            scheduledVaccineDto.date = dateTime;

            if (ctrl.validateAppointment(scheduledVaccineDto, selectedVaccinationCenter)) {
                ScheduleVaccineUI.printDataAboutAnAppointment(scheduledVaccineDto, selectedVaccinationCenter);
                if (Utils.confirmCreation()) {
                    ctrl.scheduleVaccine(scheduledVaccineDto, selectedVaccinationCenter);
                    System.out.printf("%n------------------------------------Scheduling completed-------------------------------------%n|You have an appointment to take a %s vaccine, at %s in %s, on %s.|%n---------------------------------------------------------------------------------------------%n", vaccineType, dateTime.toLocalTime(),Utils.formatDateToPrint(dateTime.toLocalDate()), selectedVaccinationCenter);
                } else {
                    System.out.printf("%n--------------No appointment was registered--------------%n");
                }
            } else {
                System.out.printf("%nUps, something went wrong. Please try again!%n");
                System.out.println("Common causes: You already have an appointment for that vaccine; Your slot is not available anymore. ");
            }
        }
    }
}