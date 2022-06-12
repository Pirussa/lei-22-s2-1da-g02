package app.domain.model;

import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.dto.*;
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


    private final GenericClass<Employee> genericsEmployee = new GenericClass<>();
    private final ArrayList<Vaccine> vaccinesList = new ArrayList<>();
    private final ArrayList<Employee> employees = new ArrayList<>();
    private final ArrayList<Employee> nurseList = new ArrayList<>();
    private final ArrayList<Employee> receptionistList = new ArrayList<>();
    private final ArrayList<Employee> centerCoordinatorList = new ArrayList<>();
    private final List<ScheduledVaccine> appointmentsList = new ArrayList<>();

    private final VaccinationCentersStore vaccinationCentersStore = new VaccinationCentersStore();
    private final VaccineTypesStore vaccineTypesStore = new VaccineTypesStore();

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
     * Instantiates a new Company.
     *
     * @param designation the designation
     */
    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
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


    private final ArrayList<String> centerCoordinatorIDs = new ArrayList<>();

    /**
     * Instantiates a mass vaccination center with information of the DTO to verify if the data is valid.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     */
    public void createMassVaccinationCenter(MassVaccinationCenterDto dto) {
        MassVaccinationCenter mvc = new MassVaccinationCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.vaccineType);
    }

    /**
     * Instantiates a healthcare center with information of the DTO to verify if the data is valid.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     */
    public void createHealthcareCenter(HealthcareCenterDto dto) {
        HealthcareCenter hc = new HealthcareCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.strARS, dto.strAGES,
                dto.vaccineTypes);
    }

    /**
     * Gets the list of all Center Coordinators, copies it and fills it with only the IDs, only adds IDs if those aren't already inside the list.
     */
    public void centerCoordinatorIDList() {
        ArrayList<Employee> centerCoordinators = getCenterCoordinatorList();
        for (Employee centerCoordinator : centerCoordinators) {
            if (!(centerCoordinatorIDs.contains(centerCoordinator.getId()))) {
                centerCoordinatorIDs.add(centerCoordinator.getId());
            }
        }
    }

    /**
     * Gets a list of Center Coordinators IDs
     *
     * @return a list of Center Coordinators IDs
     */
    public ArrayList<String> getCenterCoordinatorIDs() {
        return centerCoordinatorIDs;
    }

    /**
     * Generates a new id for each created Employee.
     *
     * @return new Employee generated id
     */
    public StringBuilder idGenerator() {
        int numberOfEmployees = getEmployees().size() + Constants.NEW_EMPLOYEE;
        if (numberOfEmployees > 0) {
            int lenght = (int) (Math.log10(numberOfEmployees) + 1);
            StringBuilder generatedID = new StringBuilder();
            generatedID.append("0".repeat(Math.max(0, Constants.ID_LENGTH - lenght)));
            return generatedID.append(numberOfEmployees);
        } else
            return new StringBuilder("00001");
    }

    /**
     * Register an Employee in the company storage
     *
     * @param dto A data transfer object with all the necessary information about the new Employee
     * @return true if the new Employee data is valid
     */
    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        Employee emp = new Employee(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
        return emp.validateEmployee();
    }

    /**
     * Saves an Employee into the Company storage.
     *
     * @param dto          A data transfer object with all the necessary information about the new Employee
     * @param selectedRole Selected role for the new Employee by the user                     Company Vaccines Storage: {@link #employees}
     * @throws NotSerializableException the not serializable exception
     */
    public void saveCreatedEmployee(RegisterNewEmployeeDto dto, String selectedRole) throws NotSerializableException {
        if (Objects.equals(selectedRole, "Nurse")) {
            Employee emp = new Nurse(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);

            genericsEmployee.binaryFileWrite(Constants.FILE_PATH_EMPLOYEES, getEmployees());
            this.authFacade.addUserWithRole(dto.name, String.valueOf(dto.email), dto.password, Constants.ROLE_NURSE);
        } else if (Objects.equals(selectedRole, "Receptionist")) {
            Employee emp = new Receptionist(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
            this.authFacade.addUserWithRole(dto.name, String.valueOf(dto.email), dto.password, Constants.ROLE_RECEPTIONIST);
            genericsEmployee.binaryFileWrite(Constants.FILE_PATH_EMPLOYEES, getEmployees());
        } else if (Objects.equals(selectedRole, "Center Coordinator")) {
            Employee emp = new CenterCoordinator(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
            this.authFacade.addUserWithRole(dto.name, String.valueOf(dto.email), dto.password, Constants.ROLE_CENTRE_COORDINATOR);
            genericsEmployee.binaryFileWrite(Constants.FILE_PATH_EMPLOYEES, getEmployees());
        }
    }

    /**
     * Authenticate employees.
     */
    public void authenticateEmployees() {
        if (!employees.isEmpty()) {
            for (Employee emp : employees) {
                if (emp instanceof Nurse) {
                    this.authFacade.addUserWithRole(emp.getName(), emp.getEmail(), emp.getPassword(), Constants.ROLE_NURSE);
                } else if (emp instanceof Receptionist) {
                    this.authFacade.addUserWithRole(emp.getName(), String.valueOf(emp.getEmail()), emp.getPassword(), Constants.ROLE_RECEPTIONIST);
                } else if (emp instanceof CenterCoordinator) {
                    this.authFacade.addUserWithRole(emp.getName(), String.valueOf(emp.getEmail()), emp.getPassword(), Constants.ROLE_CENTRE_COORDINATOR);
                }
            }

        }

    }

    /**
     * Gets the Employees registered in the Company.
     *
     * @return An ArrayList of Employees.
     */
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Fills the array lists with the types of employees through the list that contains all employees.
     */
    public void fillListOfEmployeesWithAGivenRole() {
        ArrayList<Employee> emp = getEmployees();
        for (int positionArrayListEmployees = 0; positionArrayListEmployees < emp.size(); positionArrayListEmployees++) {
            boolean check = false;
            if (emp.get(positionArrayListEmployees) instanceof Nurse) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, nurseList);

            } else if (emp.get(positionArrayListEmployees) instanceof Receptionist) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, receptionistList);

            } else if (emp.get(positionArrayListEmployees) instanceof CenterCoordinator) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, centerCoordinatorList);
            }
        }
    }

    private void fillListOfEmployeesChecker(ArrayList<Employee> emp, int positionArrayListEmployees, boolean check, ArrayList<Employee> listToBeFilled) {
        for (Employee employee : listToBeFilled) {
            if (emp.get(positionArrayListEmployees).getEmail().equals(employee.getEmail()) && emp.get(positionArrayListEmployees).getCitizenCardNumber().equals(employee.getCitizenCardNumber())) {
                check = true;
                break;
            }
        }
        if (!check) {
            listToBeFilled.add(emp.get(positionArrayListEmployees));
        }
    }

    /**
     * Gets the Nurses registered in the Company.
     *
     * @return An ArrayList of Nurses.
     */
    public ArrayList<Employee> getNurseList() {
        fillListOfEmployeesWithAGivenRole();
        return nurseList;
    }

    /**
     * Gets the Receptionists registered in the Company.
     *
     * @return An ArrayList of Receptionists.
     */
    public ArrayList<Employee> getReceptionistList() {
        fillListOfEmployeesWithAGivenRole();
        return receptionistList;
    }

    /**
     * Gets the Centre Coordinators registered in the Company.
     *
     * @return An ArrayList of Centre Coordinator.
     */
    public ArrayList<Employee> getCenterCoordinatorList() {
        fillListOfEmployeesWithAGivenRole();
        return centerCoordinatorList;
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
     * Gets coordinator id.
     *
     * @param email the email
     * @return the coordinator id
     */
    public String getCoordinatorId(Email email) {

        for (Employee centerCoordinator : getEmployees()) {
            if (String.valueOf(email).equals(centerCoordinator.getEmail())) {
                return centerCoordinator.getId();
            }
        }

        return "";
    }

    /**
     * Verifies if the Employees are duplicated.
     *
     * @param registerNewEmployeeDto : An Employee.
     * @return true if the Employees are duplicated, or false if they are not.
     */
    public boolean duplicatedEmployee(RegisterNewEmployeeDto registerNewEmployeeDto) {
        for (int index = 0; index < getEmployees().size(); index++) {
            if (getEmployees().get(index).getEmail().equals(registerNewEmployeeDto.name))
                return false;
        }
        return true;
    }

    /**
     * Read binary file employees.
     */
    public void readBinaryFileEmployees() {
        try {
            genericsEmployee.binaryFileRead(Constants.FILE_PATH_EMPLOYEES, employees);
        } catch (EOFException e) {
            e.printStackTrace();
        }
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