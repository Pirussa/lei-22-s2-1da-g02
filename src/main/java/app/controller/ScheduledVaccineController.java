package app.controller;

import app.domain.model.*;

import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;


import mapper.ScheduledVaccineMapper;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScheduledVaccineController {

    private Company company = App.getInstance().getCompany();

    public ScheduledVaccineController() {
    }

    public boolean scheduleVaccine(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!validateAppointment(scheduledVaccineDto, vaccinationCenter)) return false;
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        return vaccinationCenter.addAppointment(mapper.dtoToDomain(scheduledVaccineDto));
    }

    public boolean validateAppointment(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        List<ScheduledVaccine> appointmentsList = vaccinationCenter.getScheduledVaccineList();

        for (ScheduledVaccine appointment : appointmentsList) {

            if ((Objects.equals(appointment.getSnsNumber(), scheduledVaccineDto.snsNumber)) && (appointment.getVaccineType().equals(scheduledVaccineDto.vaccineType))) {
                return false;
            }
        }
        if (!dataIsAllFilled(scheduledVaccineDto)) return false;

        if (!centerHasAvailability(appointmentsList, scheduledVaccineDto, vaccinationCenter)) return false;

        return true;
    }

    private boolean dataIsAllFilled(ScheduledVaccineDto scheduledVaccineDto) {
        if (!Utils.validateSNSUserNumber(scheduledVaccineDto.snsNumber)) return false;

        if (scheduledVaccineDto.vaccineType == null) return false;

        if (scheduledVaccineDto.date == null) return false;

        return true;
    }

    private boolean centerHasAvailability(List<ScheduledVaccine> appointmentsList, ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter center) {

        return Utils.slotHasAvailability(Integer.parseInt(center.getStrVaccinesPerSlot()), scheduledVaccineDto.date.toLocalDate(), scheduledVaccineDto.date.toLocalTime(), appointmentsList);

    }

    public boolean validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(ScheduledVaccineDto dto, VaccinationCenter selectedVaccinationCenter) {
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
}
