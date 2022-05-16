package app.controller;

import app.domain.model.*;
import dto.ScheduledVaccineDto;
import mapper.ScheduledVaccineMapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * US010 - Register New Employee Controller
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationController {

    private Company company = App.getInstance().getCompany();

    public ScheduleVaccinationController() {
    }


    public ArrayList<SNSUser> getSNSUsersList() {
        return company.getSNSUserList();
    }

    public boolean validateAppointment(ScheduledVaccineDto dto, VaccinationCenter selectedVaccinationCenter) {
        SNSUser snsUser = company.getSNSUserList().get(SNSUser.getUserIndexInUsersList(dto.snsNumber));

        if (!snsUser.getTakenVaccines().isEmpty()) {
            for (TakenVaccine takenVaccine : snsUser.getTakenVaccines()) {
                if (dto.vaccineType.equals(takenVaccine.getVaccine().getVaccineType())) {
                    if (!validateAppointmentAccordingToAdminProcess(snsUser, dto, takenVaccine)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validateAppointmentAccordingToAdminProcess(SNSUser snsUser, ScheduledVaccineDto dto, TakenVaccine takenVaccine) {
        int days = (int) Duration.between(dto.date, takenVaccine.getDateTime()).toDays();

        int doseNumber = takenVaccine.getDose();
        AdministrationProcess administrationProcess = takenVaccine.getVaccine().getAdminProcess();
        ArrayList<ArrayList<Integer>> timeIntervalBetweenDoses = administrationProcess.getTimeIntervalBetweenVaccines();
        if (!validateAgeGroup(snsUser, administrationProcess)) {
            return false;
        }

        int ageGroupColumn = getUserAgeGroup(snsUser, administrationProcess);
        int timeIntervalBetweenUserDose = timeIntervalBetweenDoses.get(doseNumber).get(ageGroupColumn);
        if (doseNumber == administrationProcess.getNumberOfDoses().get(ageGroupColumn)) return false;

        return timeIntervalBetweenUserDose <= days;
    }

    private int getUserAgeGroup(SNSUser snsUser, AdministrationProcess administrationProcess) {

        String[] birthDateComponents = snsUser.getStrBirthDate().split("/");
        LocalDate birthDate = LocalDate.of(Integer.parseInt(birthDateComponents[2]), Integer.parseInt(birthDateComponents[1]), Integer.parseInt(birthDateComponents[0]));
        int userAgeInDays = (int) Duration.between(LocalDate.now(), birthDate).toDays();
        int userAge = userAgeInDays / 365;
        for (int columns = 0; columns < administrationProcess.getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < administrationProcess.getAgeGroups().size(); rows++) {
                if ((userAge > administrationProcess.getAgeGroups().get(columns).get(rows)) && userAge < administrationProcess.getAgeGroups().get(columns).get(rows + 1)) {
                    return columns;
                }
            }
        }
        return -1;

    }

    private boolean validateAgeGroup(SNSUser snsUser, AdministrationProcess administrationProcess) {
        return getUserAgeGroup(snsUser, administrationProcess) >= 0;
    }

    public boolean scheduleVaccine(ScheduledVaccineDto dto, VaccinationCenter vaccinationCenter) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        return vaccinationCenter.addAppointment(mapper.dtoToDomain(dto));
    }
}