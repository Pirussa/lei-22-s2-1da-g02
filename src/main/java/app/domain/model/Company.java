package app.domain.model;

import app.domain.shared.Constants;
import dto.*;
import mapper.ScheduledVaccineMapper;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author João Castro <1210816@isep.ipp.pt>
 * @author João Leitão <1211063@isep.ipp.pt>
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class Company {

    private static final int ID_LENGTH = 5;
    private String designation;
    private AuthFacade authFacade;

    /**
     * List with all the appointments
     */


    /**
     * List that stores the Vaccine Types
     */
    private ArrayList<VaccineType> vaccineTypes = new ArrayList<>();

    /**
     * List that stores the Vaccines
     */
    private ArrayList<Vaccine> vaccines = new ArrayList<>();

    /**
     * List that stores the Employees
     */
    private static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * List that stores the Nurses
     */
    private ArrayList<Employee> nurseList = new ArrayList<>();

    /**
     * List that stores the Receptinonists
     */
    private ArrayList<Employee> receptionistList = new ArrayList<>();

    /**
     * List that stores the Center Coordinators
     */
    private ArrayList<Employee> centreCoordinatorList = new ArrayList<>();


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
     * @return designation
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
     * Gets the Vaccine Types that are stored in the Company.
     *
     * @return An ArrayList of Vaccine Types.
     */
    public ArrayList<VaccineType> getVaccineTypes() {
        return vaccineTypes;
    }


    /**
     * Gets the Vaccines that are stored in the Company.
     *
     * @return An ArrayList of Vaccines.
     */
    public ArrayList<Vaccine> getVaccines() {
        return vaccines;
    }


    /**
     * Gets the Healthcare Centers that are stored in the Company.
     *
     * @return A List of Healthcare Centers.
     */
    public List<HealthcareCenter> getHealthcareCenter() {
        return healthcareCenters;
    }

    /**
     * Gets the Mass Vaccination Centers that are stored in the Company.
     *
     * @return A List of Mass Vaccination Centers.
     */
    public List<MassVaccinationCenter> getMassVaccinationCenter() {
        return massVaccinationCenters;
    }

    private  List<ScheduledVaccine> appointmentsList = new ArrayList<>();


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
                for (Vaccine vaccine : vaccines) {
                    return dto.id != vaccine.getId();
                }


            }
            ;
        }
        return false;
    }


    /**
     * Saves a Vaccine into the Company storage.
     * Company Vaccines Storage: {@link #vaccines}
     */
    public void saveVaccine(VaccineAndAdminProcessDto dto) {
        AdministrationProcess adminProcess = new AdministrationProcess(dto.ageGroups, dto.numberOfDoses, dto.dosage, dto.timeIntervalBetweenVaccines);
        Vaccine vac = new Vaccine(dto.name, dto.id, dto.brand, adminProcess, dto.vt);
        vaccines.add(vac);
    }

    public void saveVaccineTest(Vaccine v) {
        vaccines.add(v);
    }

    /**
     * Specifies a new Vaccine Type:
     * <p>
     * <p>
     * The method should create a vaccine type that should be validated, if so, it returns true
     *
     * @param code a String to validate
     * @return true if the type is valid
     */
    public boolean specifyNewVaccineType(String code, String description, String technology) {
        VaccineType vt = new VaccineType(code, description, technology);
        return vt.validateVaccineType();
    }

    /**
     * Saves a Vaccine Type into the Company storage.
     * Company Vaccines Storage: {@link #vaccineTypes}
     **/
    public void saveVaccineType(String code, String description, String technology) {
        VaccineType vt = new VaccineType(code, description, technology);
        vaccineTypes.add(vt);
    }

    //START
    private ArrayList<VaccinationCenter> vaccinationCenters = new ArrayList<>();
    private ArrayList<MassVaccinationCenter> massVaccinationCenters = new ArrayList<>();
    private ArrayList<HealthcareCenter> healthcareCenters = new ArrayList<>();
    private ArrayList<String> centerCoordinatorIDs = new ArrayList<>();

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
     * Saves a Mass Vaccination Center into two lists, one comprised of only Mass Vaccination Centers and another that has both kinds.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     **/
    public void saveMassVaccinationCenter(MassVaccinationCenterDto dto) {
        MassVaccinationCenter mvc = new MassVaccinationCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.vaccineType);
        massVaccinationCenters.add(mvc);
        vaccinationCenters.add(mvc);
    }

    /**
     * Saves a Healthcare Center into two lists, one comprised of only Healthcare Centers and another that has both kinds.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     **/
    public void saveHealthcareCenter(HealthcareCenterDto dto) {
        HealthcareCenter hc = new HealthcareCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.strARS, dto.strAGES,
                dto.vaccineTypes);
        healthcareCenters.add(hc);
        vaccinationCenters.add(hc);
    }

    /**
     * Gets the list of all Center Coordinators, copies it and fills it with only the IDs, only adds IDs if those aren't already inside the list.
     */
    public void centerCoordinatorIDList() {
        ArrayList<Employee> centerCoordinators = getCentreCoordinatorList();
        for (int i = 0; i < centerCoordinators.size(); i++) {
            if (!(centerCoordinatorIDs.contains(centerCoordinators.get(i).getId()))) {
                centerCoordinatorIDs.add(centerCoordinators.get(i).getId());
            }
        }
    }

    /**
     * Gets a list of Vaccination Centers
     *
     * @return a list of Vaccination Centers
     */
    public ArrayList<VaccinationCenter> getVaccinationCenters() {
        return vaccinationCenters;
    }

    /**
     * Gets a list of Mass Vaccination Centers
     *
     * @return a list of Mass Vaccination Centers
     */
    public ArrayList<MassVaccinationCenter> getMassVaccinationCenters() {
        return massVaccinationCenters;
    }

    /**
     * Gets a list of Healthcare Centers
     *
     * @return a list of Healthcare Centers
     */
    public ArrayList<HealthcareCenter> getHealthcareCenters() {
        return healthcareCenters;
    }

    /**
     * Gets a list of Center Coordinators IDs
     *
     * @return a list of Center Coordinators IDs
     */
    public ArrayList<String> getCenterCoordinatorIDs() {
        return centerCoordinatorIDs;
    }
    //END

    /**
     * Generates a new id for each created Employee.
     *
     * @return new Employee generated id
     */
    public static StringBuilder idGenerator() {
        int numberOfEmployees = getEmployees().size() + Constants.NEW_EMPLOYEE;
        if (numberOfEmployees > 0) {
            int lenght = (int) (Math.log10(numberOfEmployees) + 1);
            StringBuilder generatedID = new StringBuilder("");
            generatedID.append("0".repeat(Math.max(0, ID_LENGTH - lenght)));
            return generatedID.append(String.valueOf(numberOfEmployees));
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
     * @param selectedRole Selected role for the new Employee by the user
     *                     Company Vaccines Storage: {@link #employees}
     */

    public void saveCreatedEmployee(RegisterNewEmployeeDto dto, String selectedRole) {
        if (Objects.equals(selectedRole, "Nurse")) {
            Employee emp = new Nurse(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
            this.authFacade.addUserWithRole(dto.name, dto.email, dto.password, Constants.ROLE_NURSE);
        } else if (Objects.equals(selectedRole, "Receptionist")) {
            Employee emp = new Receptionist(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
            this.authFacade.addUserWithRole(dto.name, dto.email, dto.password, Constants.ROLE_RECEPTIONIST);
        } else if (Objects.equals(selectedRole, "Center Coordinator")) {
            Employee emp = new CenterCoordinator(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
            this.authFacade.addUserWithRole(dto.name, dto.email, dto.password, Constants.ROLE_CENTRE_COORDINATOR);
        }
    }

    /**
     * Gets the Employees registered in the Company.
     *
     * @return An ArrayList of Employees.
     */
    public static ArrayList<Employee> getEmployees() {
        return employees;
    }

    /**
     * Fills the array lists with the types of employees through the list that contains all employees.
     */

    public void fillListOfEmployeesWithAGivenRole() {
        ArrayList<Employee> emp = getEmployees();
        for (int positionArrayListEmployees = 0; positionArrayListEmployees < emp.size(); positionArrayListEmployees++) {
       boolean check= false;
            if (emp.get(positionArrayListEmployees) instanceof Nurse) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, nurseList);

            } else if (emp.get(positionArrayListEmployees) instanceof Receptionist) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, receptionistList);

            } else if (emp.get(positionArrayListEmployees) instanceof CenterCoordinator) {
                fillListOfEmployeesChecker(emp, positionArrayListEmployees, check, centreCoordinatorList);
            }
        }
    }

    private void fillListOfEmployeesChecker(ArrayList<Employee> emp, int positionArrayListEmployees, boolean check, ArrayList<Employee> listToBeFilled) {
        for (int listToBeFilledPosition = 0; listToBeFilledPosition < listToBeFilled.size(); listToBeFilledPosition++) {
            if(emp.get(positionArrayListEmployees).getEmail().equals(listToBeFilled.get(listToBeFilledPosition).getEmail()) && emp.get(positionArrayListEmployees).getCitizenCardNumber().equals(listToBeFilled.get(listToBeFilledPosition).getCitizenCardNumber())){
                check = true;
            }
        }
        if(!check){
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
    public ArrayList<Employee> getCentreCoordinatorList() {
        fillListOfEmployeesWithAGivenRole();
        return centreCoordinatorList;
    }

    //START
    ArrayList<SnsUser> snsUsers = new ArrayList<>();

    /**
     * Gets sns user list.
     *
     * @return the sns user list
     */
    public ArrayList<SnsUser> getSnsUserList() {
        return snsUsers;
    }

    /**
     * Create sns user with information from the DTO
     *
     * @param dto the dto
     * @return the created SNS User
     */
    public SnsUser createSNSUser(SNSUserDto dto) {
        return new SnsUser(dto.strName, dto.strSex, dto.strBirthDate, dto.strAddress, dto.strPhoneNumber,
                dto.strEmail, dto.snsUserNumber, dto.strCitizenCardNumber, dto.strPassword);
    }

    /**
     * Save sns user inside an array of SNS Users
     *
     * @param dto the dto
     * @return a string in order to know if the users was saved or not.
     */
    public String saveSNSUser(SNSUserDto dto) {
        boolean flag = false;
        if (snsUsers.isEmpty()) {
            snsUsers.add(createSNSUser(dto));
            this.authFacade.addUserWithRole(dto.strName, dto.strEmail, dto.strPassword, Constants.ROLE_SNS_USER);
            return "Saved";
        } else {
            for (int i = 0; i < snsUsers.size(); i++) {
                if (!(Objects.equals(snsUsers.get(i).getSnsUserNumber(), createSNSUser(dto).getSnsUserNumber())) &&
                        !(Objects.equals(snsUsers.get(i).getStrEmail(), createSNSUser(dto).getStrEmail())) &&
                        !(Objects.equals(snsUsers.get(i).getStrPhoneNumber(), createSNSUser(dto).getStrPhoneNumber())) &&
                        !(Objects.equals(snsUsers.get(i).getStrCitizenCardNumber(), createSNSUser(dto).getStrCitizenCardNumber()))) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                snsUsers.add(createSNSUser(dto));
                this.authFacade.addUserWithRole(dto.strName, dto.strEmail, dto.strPassword, Constants.ROLE_SNS_USER);
                return "Saved";
            } else {
                return "Not Saved because the data is duplicated";
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

    public void cleanAppointments(){
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

}