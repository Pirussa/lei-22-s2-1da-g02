package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;
import mapper.ScheduledVaccineMapper;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * US010 - Register New Employee Controller
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationController {

    private Company company = App.getInstance().getCompany();

    public ScheduleVaccinationController() {}


    public ArrayList<SNSUser> getSNSUsersList() {
        return company.getSNSUserList();
    }

    public boolean validateAppointment(String snsNumber, VaccineType vaccineType, LocalDateTime dateTime, VaccinationCenter selectedVaccinationCenter) {
        return true;
    }
    public boolean scheduleVaccine(ScheduledVaccineDto dto, VaccinationCenter vaccinationCenter) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        return   vaccinationCenter.addAppointment(mapper.dtoToDomain(dto));
    }
}
