package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;
import mapper.ScheduledVaccineMapper;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Has all the info about a scheduled Vaccine
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */
public class ScheduleVaccineController {


    private final Company company = App.getInstance().getCompany();
    private final AuthFacade authFacade = company.getAuthFacade();


    public ScheduleVaccineController() {
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
        vaccinationCenter.addAppointment(createScheduledVaccine(scheduledVaccineDto));
        return true;
    }

    /**
     * Schedule vaccination if the appointment is valid
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @return True if the vaccine appointment was valid and scheduled
     */
    public boolean scheduleVaccination(ScheduledVaccineDto scheduledVaccineDto, VaccinationCenter vaccinationCenter) {
        if (!validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, vaccinationCenter))
            return false;
        vaccinationCenter.addAppointment(createScheduledVaccine(scheduledVaccineDto));
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

    /**
     * Checks if the logged user is a Receptionist
     *
     * @return true if the logged user is a Receptionist
     */
    public boolean loggedUserIsReceptionist() {
        return authFacade.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_RECEPTIONIST);
    }

    /**
     * Gets logged user email.
     *
     * @return the email
     */
    public Email getLoggedUserEmail() {
        return authFacade.getCurrentUserSession().getUserId();
    }

    /**
     * Gets SNS users list.
     *
     * @return the list
     */
    public List<SNSUser> getSnsUsersList() {
        return company.getSNSUserList();
    }

    public int getUserPhoneNumber() {
        for (SNSUser user : company.getSNSUserList()) {
            if (user.getStrEmail().equals(String.valueOf(authFacade.getCurrentUserSession().getUserId()))) {
                return Integer.parseInt( user.getStrPhoneNumber());
            }
        }
        return 0;
    }

    /**
     * Checks if company has the necessary info to schedule a Vaccine/Vaccination.
     *
     * @return true if all the arrays have at least one object each
     */
    public boolean companyHasNecessaryInfo() {
        if (company.getSNSUserList().isEmpty()) return false;
        if (company.getVaccineTypes().isEmpty()) return false;
        return !company.getVaccinationCenters().isEmpty();
    }


    /**
     * Verifies if a DTO has all the data for an appointment
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return True if the dto has all the attributes filled
     */
    private boolean dataIsAllFilled(ScheduledVaccineDto scheduledVaccineDto) {
        if (!Utils.validateSNSUserNumber(scheduledVaccineDto.snsNumber)) return false;

        if (scheduledVaccineDto.vaccineType == null) return false;

        return scheduledVaccineDto.date != null;
    }

    private ScheduledVaccine createScheduledVaccine(ScheduledVaccineDto scheduledVaccineDto) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        return mapper.dtoToDomain(scheduledVaccineDto);
    }

}