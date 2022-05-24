package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;
import dto.ScheduledVaccineDto;
import dto.VaccinationCenterDto;
import mapper.ScheduledVaccineMapper;
import mapper.VaccinationCenterMapper;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Arrays;
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
    private VaccinationCenter vaccinationCenter;


    /**
     * Instantiates a new Schedule vaccine controller.
     */
    public ScheduleVaccineController() {
    }


    /**
     * Schedule vaccine if the appointment is valid
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @return True if the vaccine appointment was valid and scheduled
     */
    public boolean scheduleVaccine(ScheduledVaccineDto scheduledVaccineDto) {
        if (!validateAppointment(scheduledVaccineDto)) return false;
        vaccinationCenter.addAppointment(createScheduledVaccine(scheduledVaccineDto));
        ScheduledVaccine.addAppointment(createScheduledVaccine(scheduledVaccineDto));
        return true;
    }

    /**
     * Validate appointment boolean.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @param vaccinationCenter   the vaccination center
     * @return the boolean
     */
    public boolean validateAppointment(ScheduledVaccineDto scheduledVaccineDto) {
        if (!dataIsAllFilled(scheduledVaccineDto)) return false;
        if (!ScheduledVaccine.userIsEligibleForTheAppointment(scheduledVaccineDto)) return false;
        if (!vaccinationCenter.validateAppointmentAccordingToAgeGroupAndTimeSinceLastDose(scheduledVaccineDto, company))
            return false;

        return vaccinationCenter.centerHasAvailability(scheduledVaccineDto);

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
    public List<SnsUser> getSnsUsersList() {
        return company.getSNSUserList();
    }

    /**
     * Gets user phone number.
     *
     * @return the user phone number
     */
    public int getUserPhoneNumber() {
        for (SnsUser user : company.getSNSUserList()) {
            if (user.getStrEmail().equals(String.valueOf(authFacade.getCurrentUserSession().getUserId()))) {
                return Integer.parseInt(user.getStrPhoneNumber());
            }
        }
        return 0;
    }

    /**
     * Gets sns user number.
     *
     * @return the sns user number
     */
    public int getSnsUserNumber() {
        for (SnsUser user : company.getSNSUserList()) {
            if (user.getStrEmail().equals(String.valueOf(authFacade.getCurrentUserSession().getUserId()))) {
                return user.getSnsUserNumber();
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

    /**
     * Get vaccination center info.
     *
     * @return the vaccination center dto
     */
    public VaccinationCenterDto getVaccinationCenterInfo() {
        VaccinationCenterMapper mapper = new VaccinationCenterMapper();
        return mapper.domainToDto(vaccinationCenter);
    }

    /**
     * Sets vaccination center.
     *
     * @param index the index
     */
    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index - 1);
    }

    /**
     * Is mass vaccination center or a Healthcare center.
     *
     * @return true if it is a Mass Vaccination Center
     */
    public boolean isMassVaccinationCenter() {
        return vaccinationCenter instanceof MassVaccinationCenter;
    }


    public ArrayList<String> getVaccineType() {
        List <String> vaccineTypes ;

        if (isMassVaccinationCenter()){
            MassVaccinationCenter massVaccinationCenter = (MassVaccinationCenter) vaccinationCenter;
             vaccineTypes= new ArrayList<>(Arrays.of(massVaccinationCenter.getStrVaccineType()));
            return vaccineTypes;
        }else{
            HealthcareCenter healthcareCenter = (HealthcareCenter) vaccinationCenter;
            int index = 0;
            for (VaccineType vacineType: healthcareCenter.getVaccineTypes()) {
                vaccineTypes.add(index,vacineType.toString());
                index++;

            }

            return
        }
    }
}