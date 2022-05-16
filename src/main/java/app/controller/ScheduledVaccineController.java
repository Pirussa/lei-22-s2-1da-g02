package app.controller;

import app.domain.model.Company;

import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;


import mapper.ScheduledVaccineMapper;

import java.util.List;
import java.util.Objects;

public class ScheduledVaccineController {


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

}
