package app.domain.model;

import app.ui.console.*;
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

    private ArrayList<VaccineType> vaccineTypes = new ArrayList<>();

    private ArrayList<Vaccine> vaccines = new ArrayList<>();

    private static ArrayList<Employee> employees = new ArrayList<>();

    private ArrayList<String> roles = new ArrayList<>();

    private ArrayList<Employee> nurseList = new ArrayList<>();

    private ArrayList<Employee> receptionistList = new ArrayList<>();

    private ArrayList<Employee> centreCoordinatorList = new ArrayList<>();

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

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


    public List<HealthcareCenter> getHealthcareCenter() {

        return healthcareCenters;
    }

    public List<MassVaccinationCenter> getMassVaccinationCenter() {
        return massVaccinationCenters;
    }

    public List<VaccinationCenter> getVaccinationCenters(VaccinationCenter o) {
        return vaccinationCenters;
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
            if (vac.validateVaccine()){
                for (Vaccine vaccine : vaccines) {
                    return dto.id != vaccine.getId();
                }


            };
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

    public void saveVaccineTest(Vaccine v){
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

    public void addNewRole() {

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
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.strVaccineType);
    }

    /**
     * Instantiates a healthcare center with information of the DTO to verify if the data is valid.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     */
    public void createHealthcareCenter(HealthcareCenterDto dto) {
        HealthcareCenter hc = new HealthcareCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.strARS, dto.strAGES,
                dto.strVaccineType);
    }
    /**
     * Saves a Mass Vaccination Center into two lists, one comprised of only Mass Vaccination Centers and another that has both kinds.
     *
     * @param dto copies the information from the DTO that has the data needed to instantiate the center.
     **/
    public void saveMassVaccinationCenter(MassVaccinationCenterDto dto) {
        MassVaccinationCenter mvc = new MassVaccinationCenter(dto.strID, dto.strName, dto.strPhoneNumber, dto.strEmail, dto.strFax, dto.strWebsite, dto.strOpeningHour,
                dto.strClosingHour, dto.strSlotDuration, dto.strVaccinesPerSlot, dto.strRoad, dto.strZipCode, dto.strLocal, dto.strCenterCoordinatorID, dto.strVaccineType);
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
                dto.strVaccineType);
        healthcareCenters.add(hc);
        vaccinationCenters.add(hc);
    }

    /**
     * Gets the list of all Center Coordinators, copies it and fills it with only the IDs, only adds IDs if those aren't already inside the list.
     */
    public void centerCoordinatorIDList(){
        ArrayList<Employee> centerCoordinators = getCentreCoordinatorList();
        for (int i = 0; i < centerCoordinators.size(); i++) {
            if (!(centerCoordinatorIDs.contains(centerCoordinators.get(i).getId()))){
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
        } else if (Objects.equals(selectedRole, "Receptionist")) {
            Employee emp = new Receptionist(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
        } else if (Objects.equals(selectedRole, "Center Coordinator")) {
            Employee emp = new CenterCoordinator(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
            employees.add(emp);
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
           boolean check = false;
            if (emp.get(positionArrayListEmployees) instanceof Nurse) {
                for (int nurseListPosition = 0; nurseListPosition < nurseList.size(); nurseListPosition++) {
                    if (emp.get(positionArrayListEmployees).getEmail().equals(nurseList.get(nurseListPosition).getEmail()) && emp.get(positionArrayListEmployees).getCitizenCardNumber().equals(nurseList.get(nurseListPosition).getCitizenCardNumber())) {
                        check = true;
                    }
                }
                if (!check) {
                    nurseList.add(emp.get(positionArrayListEmployees));
                }

            } else if (emp.get(positionArrayListEmployees) instanceof Receptionist) {
                for (int receptionistListPosition = 0; receptionistListPosition < receptionistList.size(); receptionistListPosition++) {
                    if (emp.get(positionArrayListEmployees).getEmail().equals(receptionistList.get(receptionistListPosition).getEmail()) && emp.get(positionArrayListEmployees).getCitizenCardNumber().equals(receptionistList.get(receptionistListPosition).getCitizenCardNumber())) {
                        check = true;
                    }
                }
                if (!check) {
                    receptionistList.add(emp.get(positionArrayListEmployees));
                }
            } else if (emp.get(positionArrayListEmployees) instanceof CenterCoordinator) {
                for (int centreCoordinatorListPosition = 0; centreCoordinatorListPosition < centreCoordinatorList.size(); centreCoordinatorListPosition++) {
                    if (emp.get(positionArrayListEmployees).getEmail().equals(centreCoordinatorList.get(centreCoordinatorListPosition).getEmail()) && emp.get(positionArrayListEmployees).getCitizenCardNumber().equals(centreCoordinatorList.get(centreCoordinatorListPosition).getCitizenCardNumber())) {
                        check = true;
                    }
                }
                if (!check) {
                    centreCoordinatorList.add(emp.get(positionArrayListEmployees));
                }
            }
        }
    }

    /**
     * Gets the Nurses registered in the Company.
     *
     * @return An ArrayList of Nurses.
     */
    public ArrayList<Employee> getNurseList() {
        return nurseList;
    }

    /**
     * Gets the Receptionists registered in the Company.
     *
     * @return An ArrayList of Receptionists.
     */
    public ArrayList<Employee> getReceptionistList() {
        return receptionistList;
    }

    /**
     * Gets the Centre Coordinators registered in the Company.
     *
     * @return An ArrayList of Centre Coordinator.
     */
    public ArrayList<Employee> getCentreCoordinatorList() {
        return centreCoordinatorList;
    }


}
