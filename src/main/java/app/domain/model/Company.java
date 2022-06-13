package app.domain.model;

import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.dto.*;
import app.stores.EmployeesStore;
import app.stores.VaccinationCentersStore;
import app.stores.VaccineTypesStore;
import app.ui.console.utils.Utils;
import app.mapper.ScheduledVaccineMapper;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * The type Company.
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author João Castro <1210816@isep.ipp.pt>
 * @author João Leitão <1211063@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */
public class Company implements Serializable {


    private final String designation;
    private final transient AuthFacade authFacade;

    public final GenericClass<VaccineBulletin> genericsVaccineBulletin = new GenericClass<>();
    private final GenericClass<Vaccine> genericsVaccine = new GenericClass<>();
    private final GenericClass<SnsUser> genericsSnsUsers = new GenericClass<>();


    private final ArrayList<Vaccine> vaccinesList = new ArrayList<>();
    private final List<ScheduledVaccine> appointmentsList = new ArrayList<>();

    private final VaccinationCentersStore vaccinationCentersStore = new VaccinationCentersStore();
    private final VaccineTypesStore vaccineTypesStore = new VaccineTypesStore();
    private final EmployeesStore employeesStore;

    /**
     * Gets vaccination centers store.
     *
     * @return the vaccination centers store
     */
    public VaccinationCentersStore getVaccinationCentersStore() {
        return vaccinationCentersStore;
    }

    /**
     * Gets vaccine types store.
     *
     * @return the vaccine types store
     */
    public VaccineTypesStore getVaccineTypesStore(){
        return vaccineTypesStore;
    }

    /**
     * Gets employees store.
     *
     * @return the employees store
     */
    public EmployeesStore getEmployeesStore() {
        return employeesStore;
    }

    /**
     * Instantiates a new Company.
     *
     * @param designation the designation
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

       employeesStore = new EmployeesStore(authFacade);



    }

    /**
     * Gets the designation
     *
     * @return designation designation
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Gets the Auth Facade
     *
     * @return Auth Facade
     */
    public AuthFacade getAuthFacade() {
        return authFacade;
    }


    /**
     * Gets the Vaccines that are stored in the Company.
     *
     * @return An ArrayList of Vaccines.
     */
    public List<Vaccine> getVaccinesList() {
        return vaccinesList;
    }

    /**
     * Specifies a new Vaccine and its Administration Process:
     * <p>
     * <p>
     * The method should create an Administration Process that should be validated, if so,
     * it creates a Vaccine that should be validated.
     *
     * @param dto A data transfer object with all the necessary information in order to specify both the Administration Process and the Vaccine.
     * @return true if the Vaccine is created and validated with success .
     */
    public boolean specifyNewVaccineAndAdminProcess(VaccineAndAdminProcessDto dto) {
        AdministrationProcess adminProcess = new AdministrationProcess(dto.ageGroups, dto.numberOfDoses, dto.dosage, dto.timeIntervalBetweenVaccines);
        if (adminProcess.validateAdministrationProcess()) {
            Vaccine vac = new Vaccine(dto.name, dto.id, dto.brand, adminProcess, dto.vt);
            if (vac.validateVaccine()) {
                for (Vaccine vaccine : vaccinesList) {
                    return dto.id != vaccine.getId();
                }
                return true;

            }

        }
        return false;
    }

    /**
     * Saves a Vaccine into the Company storage.
     * Company Vaccines Storage: {@link #vaccinesList}
     *
     * @param dto the dto
     */
    public void saveVaccine(VaccineAndAdminProcessDto dto) {
        AdministrationProcess adminProcess = new AdministrationProcess(dto.ageGroups, dto.numberOfDoses, dto.dosage, dto.timeIntervalBetweenVaccines);
        Vaccine vac = new Vaccine(dto.name, dto.id, dto.brand, adminProcess, dto.vt);
        vaccinesList.add(vac);
        genericsVaccine.binaryFileWrite(Constants.FILE_PATH_VACCINES, vaccinesList);
    }

    /**
     * Save vaccine.
     *
     * @param vaccine the vaccine
     */
    public void saveVaccineBs(Vaccine vaccine){
        vaccinesList.add(vaccine);
        genericsVaccine.binaryFileWrite(Constants.FILE_PATH_VACCINES, vaccinesList);
    }

    /**
     * Save vaccine test.
     *
     * @param v the v
     */
    public void saveVaccineTest(Vaccine v) {
        vaccinesList.add(v);
        genericsVaccine.binaryFileWrite(Constants.FILE_PATH_VACCINES, vaccinesList);
    }

    /**
     * The Sns users.
     */
    public ArrayList<SnsUser> snsUsersList = new ArrayList<>();

    /**
     * Gets sns user list.
     *
     * @return the sns user list
     */
    public ArrayList<SnsUser> getSnsUserList() {
        return snsUsersList;
    }

    /**
     * Create sns user with information from the DTO
     *
     * @param dto the dto
     * @return the created SNS User
     */
    public SnsUser createSNSUser(SnsUserDto dto) {
        return new SnsUser(dto.strName, dto.strSex, dto.strBirthDate, dto.strAddress, dto.strPhoneNumber,
                dto.strEmail, dto.snsUserNumber, dto.strCitizenCardNumber, dto.strPassword);
    }

    //test

    /**
     * Save sns user inside an array of SNS Users
     *
     * @param dto the dto
     * @return a string in order to know if the users was saved or not.
     */
    public boolean saveSNSUser(SnsUserDto dto) {
        if (snsUsersList.isEmpty()) {
            snsUsersList.add(createSNSUser(dto));
            genericsSnsUsers.binaryFileWrite(Constants.FILE_PATH_SNS_USERS, snsUsersList);
            this.authFacade.addUserWithRole(dto.strName, dto.strEmail, dto.strPassword, Constants.ROLE_SNS_USER);
            return true;
        } else {
            for (SnsUser snsUser : snsUsersList) {
                if ((Objects.equals(snsUser.getSnsUserNumber(), createSNSUser(dto).getSnsUserNumber())) ||
                        (Objects.equals(snsUser.getStrEmail(), createSNSUser(dto).getStrEmail())) ||
                        (Objects.equals(snsUser.getStrPhoneNumber(), createSNSUser(dto).getStrPhoneNumber())) ||
                        (Objects.equals(snsUser.getStrCitizenCardNumber(), createSNSUser(dto).getStrCitizenCardNumber()))) {
                    return false;
                }
            }
        }
        snsUsersList.add(createSNSUser(dto));
        genericsSnsUsers.binaryFileWrite(Constants.FILE_PATH_SNS_USERS, snsUsersList);
        this.authFacade.addUserWithRole(dto.strName, dto.strEmail, dto.strPassword, Constants.ROLE_SNS_USER);
        return true;
    }

    /**
     * Authenticate sns user.
     */
    public void authenticateSNSUser() {
        if (!snsUsersList.isEmpty()) {
            for (SnsUser snsUser : snsUsersList) {
                this.authFacade.addUserWithRole(snsUser.getStrName(), snsUser.getStrEmail(), snsUser.getStrPassword(), Constants.ROLE_SNS_USER);
            }
        }

    }

    /**
     * Adds appointment.
     *
     * @param scheduledVaccine the scheduled vaccine
     */
    public void addAppointment(ScheduledVaccine scheduledVaccine) {
        appointmentsList.add(scheduledVaccine);
    }

    /**
     * Gets appointments.
     *
     * @return the appointments
     */
    public List<ScheduledVaccine> getAppointments() {
        return appointmentsList;
    }

    /**
     * User is eligible for the appointment.
     *
     * @param scheduledVaccineDto the scheduled vaccine dto
     * @return true if the user doesn't have another appointment for the same Vaccine
     */
    public boolean userIsEligibleForTheAppointment(ScheduledVaccineDto scheduledVaccineDto) {
        ScheduledVaccineMapper mapper = new ScheduledVaccineMapper();
        ScheduledVaccine appointment = mapper.createScheduledVaccine(scheduledVaccineDto);
        for (ScheduledVaccine appointmentCheck : appointmentsList) {
            if ((appointment.getVaccineType().equals(appointmentCheck.getVaccineType()) && (appointment.getSnsNumber() == appointmentCheck.getSnsNumber())))
                return false;
        }
        return true;
    }

    /**
     * Clean appointments.
     */
    public void cleanAppointments() {
        appointmentsList.clear();
    }

    /**
     * Gets user index in users list.
     *
     * @param snsUserNumber the sns user number
     * @return the user index in users list
     */
    public int getUserIndexInUsersList(int snsUserNumber) {
        for (int position = 0; position < getSnsUserList().size(); position++) {
            if (snsUserNumber == (getSnsUserList().get(position).getSnsUserNumber())) return position;
        }
        return -1;
    }

    /**
     * Read binary file sns users.
     */
    public void readBinaryFileSnsUsers() {
        try {
            genericsSnsUsers.binaryFileRead(Constants.FILE_PATH_SNS_USERS, snsUsersList);
        } catch (EOFException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read binary file vaccines.
     */
    public void readBinaryFileVaccines() {
        try {
            genericsVaccine.binaryFileRead(Constants.FILE_PATH_VACCINES, vaccinesList);
        } catch (EOFException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read binary file vaccine bulletins.
     */
    public void readBinaryFileVaccineBulletins() {
        try {
            for (VaccinationCenter vaccinationCenter : getVaccinationCentersStore().getVaccinationCenters()) {
                genericsVaccineBulletin.binaryFileRead(Constants.FILE_PATH_VACCINE_BULLETIN, vaccinationCenter.getVaccinesAdministeredList());
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
    }
}