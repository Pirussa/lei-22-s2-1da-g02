package app.controller;

import app.domain.model.*;

import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;


import mapper.ScheduledVaccineMapper;

public class ScheduledVaccineController {

    private final Company company = App.getInstance().getCompany();

    public ScheduledVaccineController() {
    }

    public boolean scheduleVaccine(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!validateAppointment(scheduledVaccineDto, vaccinationCenter)) return false;
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        vaccinationCenter.addAppointment(mapper.dtoToDomain(scheduledVaccineDto));
        return true;
    }

    public boolean validateAppointment(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!dataIsAllFilled(scheduledVaccineDto)) return false;

        return vaccinationCenter.validateAppointment(scheduledVaccineDto);
    }

    public boolean validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter center) {
        return (center.validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, company));

    }

    private boolean dataIsAllFilled(ScheduledVaccineDto scheduledVaccineDto) {
        if (!Utils.validateSNSUserNumber(scheduledVaccineDto.snsNumber)) return false;

        if (scheduledVaccineDto.vaccineType == null) return false;

        return scheduledVaccineDto.date != null;
    }



}
