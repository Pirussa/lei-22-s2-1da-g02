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
                System.out.printf("%nIntroduce SNS Number: ");
                snsNumber = Utils.insertInt("\nIntroduce a Valid SNS Number: ");
            } while (!SNSUser.validateSNSUserNumber(snsNumber) || SNSUser.getUserIndexInUsersList(snsNumber) <= Constants.INVALID_VALUE);

            int selectedVaccinationCenterIndexInArrayList = Utils.showAndSelectIndex(company.getVaccinationCenters(), "\nAvailable Vaccination Centers: ");
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
                printAppointmentInfo(scheduledVaccineDto, selectedVaccinationCenter);
                if (Utils.confirmCreation()) {
                    ctrl.scheduleVaccine(scheduledVaccineDto, selectedVaccinationCenter);
                    System.out.printf("%n---------------------------------------|Vaccination Was Scheduled Sucessfully|---------------------------------------");
                } else {
                    System.out.printf("%n--------------No appointment was registered--------------%n");
                }
            } else {
                System.out.printf("%nUps, something went wrong. Please try again!%n");
                System.out.println("Common causes: You already have an appointment for that vaccine; Your slot is not available anymore. ");
            }
        }
    }

    private void printAppointmentInfo(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        System.out.printf("%n-------------------------%n|Appointment Information|%n-------------------------%n%n");
        System.out.printf("Given SNS Number: " + scheduledVaccineDto.snsNumber + "%n%nSelected Vaccination Center: " + vaccinationCenter + "%n%nSelected Vaccine Type: " + scheduledVaccineDto.vaccineType + "%n%nDate: " + Utils.formatDateToPrint(scheduledVaccineDto.date.toLocalDate()) + "%n%nTime: " + scheduledVaccineDto.date.toLocalTime() + "%n%n");
    }
    private void printInvalidAppointment() {
        System.out.printf("System is unable to schedule a vaccination without at least:%n- One Vaccination Center;%n- One Vaccine Type;%n- One Know System User.");
    }
    private void printValidAppointmentInfo(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
    }
}