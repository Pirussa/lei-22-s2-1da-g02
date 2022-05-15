package app.controller;

import app.domain.model.Company;

import app.domain.model.ScheduledVaccine;
import app.domain.model.VaccinationCenter;
import dto.ScheduledVaccineDto;


import mapper.ScheduledVaccineMapper;

public class ScheduledVaccineController {

    private final Company  company = App.getInstance().getCompany();

    public ScheduledVaccineController() {
    }

    public boolean scheduleVaccine(ScheduledVaccineDto dto, VaccinationCenter vaccinationCenter) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();

       return   vaccinationCenter.addAppointment(mapper.dtoToDomain(dto));
    }

    public boolean validateAppointment(ScheduledVaccineDto dto, VaccinationCenter vaccinationCenter){




        return true;
    }

}
