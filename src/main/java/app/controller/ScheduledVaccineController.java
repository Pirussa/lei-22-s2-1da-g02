package app.controller;

import app.domain.model.*;

import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;


import mapper.ScheduledVaccineMapper;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class ScheduledVaccineController {

    private final Company company = App.getInstance().getCompany();

    public ScheduledVaccineController() {
    }


    /**
     * Schedule vaccine if the appointment is valid
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @return True if the vaccine appointment was valid and scheduled
     */
    public boolean scheduleVaccine(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!validateAppointment(scheduledVaccineDto, vaccinationCenter)) return false;
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        vaccinationCenter.addAppointment(mapper.dtoToDomain(scheduledVaccineDto));
        return true;
    }

    /**
     * Validate appointment boolean.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @return the boolean
     */
    public boolean validateAppointment(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!dataIsAllFilled(scheduledVaccineDto)) return false;

        return vaccinationCenter.validateAppointment(scheduledVaccineDto);
    }

    /**
     * Validate appointment according to age group and time since last dose.
     *
     * @param scheduledVaccineDto The scheduled vaccine dto
     * @param center              The Vaccination Center selected by the user
     * @return true if the user´s appointment is valid
     */
    public boolean validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter center) {
        return (center.validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, company));

    }

    private boolean dataIsAllFilled(ScheduledVaccineDto scheduledVaccineDto) {
        if (!Utils.validateSNSUserNumber(scheduledVaccineDto.snsNumber)) return false;

        if (scheduledVaccineDto.vaccineType == null) return false;

        return scheduledVaccineDto.date != null;
    }
}