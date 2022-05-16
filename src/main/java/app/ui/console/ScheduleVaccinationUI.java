package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccinationController;
import app.controller.ScheduledVaccineController;
import app.domain.model.*;
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
            String snsNumber;
            do {
                snsNumber = Utils.readLineFromConsole("Introduce SNS Number: ");
            } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.getUserIndexInUsersList(snsNumber) < 0);

            int selectedVaccinationCenterIndexInArrayList = Utils.showAndSelectIndex(Utils.getVaccinationCenterList(), "Vaccination Centers");
            VaccinationCenter selectedVaccinationCenter = company.getVaccinationCenters().get(selectedVaccinationCenterIndexInArrayList);

            VaccineType vaccineType = ScheduleVaccineUI.selectVaccineTypeUI(selectedVaccinationCenter);
            if (vaccineType == null) return;

            LocalDateTime dateTime = ScheduleVaccineUI.selectDateUI(selectedVaccinationCenter);

            if (ctrl.validateAppointment(snsNumber, vaccineType, dateTime, selectedVaccinationCenter)) {
                System.out.println();
                if (Utils.confirmCreation()) {
                    ScheduledVaccineDto scheduledVaccineDto = new ScheduledVaccineDto();
                    scheduledVaccineDto.snsNumber = snsNumber;
                    scheduledVaccineDto.vaccineType = vaccineType;
                    scheduledVaccineDto.date = dateTime;
                    ctrl.scheduleVaccine(scheduledVaccineDto, selectedVaccinationCenter);
                }
            }
        }
    }
}
    /*
     Como iria funcionar? - O User dirige-se à rececionista e procede ao agendamento da vacina, a rececionista deve ter em conta,
      a data de nascimento e o tempo desde a última vacina que o user tomou.
    */