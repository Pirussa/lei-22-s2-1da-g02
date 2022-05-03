package app.domain.model;

import app.domain.shared.Constants;
import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.VaccinationCenterDto;
import app.ui.console.VaccineAndAdminProcessDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 * @author João Castro <1210816@isep.ipp.pt>
 */

public class Company {

    private static final int ID_LENGTH = 5;
    private String designation;
    private AuthFacade authFacade;

    private ArrayList<VaccineType> vaccineTypes = new ArrayList<>();

    private ArrayList<Vaccine> vaccines = new ArrayList<>();

    private static ArrayList<Employee> employees = new ArrayList<>();




    //private List<HealthcareCenter> healthcareCenters = new ArrayList<>();

    //private List<MassVaccinationCenter> massVaccinationCenters = new ArrayList<>();

    private ArrayList<String> roles = new ArrayList<>();

    private ArrayList<Employee> nurseList = new ArrayList<>();

    private ArrayList<Employee> receptionistList =new ArrayList<>();

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


    /*public List<HealthcareCenter> getHealthcareCenter(){

        return healthcareCenters;
    }

    public List<MassVaccinationCenter> getMassVaccinationCenter(){
        return massVaccinationCenters;
    }*/

    public List<VaccinationCenter> getVaccinationCenters(VaccinationCenter o){
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
            return vac.validateVaccine();
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
    private List<VaccinationCenter> vaccinationCenters = new ArrayList<>();
    /**
     * Creates a Vaccination Center
     */
    public void createVaccinationCenter(VaccinationCenterDto dto){
        VaccinationCenter vc = new VaccinationCenter(dto.intID,dto.strName, dto.strPhoneNumber, dto.strEmail,dto.strFax,dto.strWebsite,dto.strOpeningHour,
                dto.strClosingHour,dto.strSlotDuration,dto.strVaccinesPerSlot,dto.strRoad, dto.strZipCode,dto.strLocal,dto.strCenterCoordinatorID);
    }

    /**
     * Saves a VaccinationCenter
     */
    public void saveVaccinationCenter(VaccinationCenterDto dto){
        VaccinationCenter vc = new VaccinationCenter(dto.intID,dto.strName, dto.strPhoneNumber, dto.strEmail,dto.strFax,dto.strWebsite,dto.strOpeningHour,
                dto.strClosingHour,dto.strSlotDuration,dto.strVaccinesPerSlot,dto.strRoad, dto.strZipCode,dto.strLocal,dto.strCenterCoordinatorID);
        vaccinationCenters.add(vc);
    }

    public void getVaccinationCenters() {
        System.out.println(vaccinationCenters);
    }

    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        Employee emp = new Employee(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
        return true;
    }



    public void saveCreatedEmployee(RegisterNewEmployeeDto dto) {
        Employee emp = new Employee(dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber, dto.password);
        employees.add(emp);
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
     * Fills the arrays of the types of employees through the list that contains all employees.
     */

    public void fillListOfEmployeesWithAGivenRole() {
        ArrayList<Employee> emp = getEmployees();
        for (int positionArrayListEmployees = 0; positionArrayListEmployees < emp.size(); positionArrayListEmployees++) {
            if (emp.get(positionArrayListEmployees) instanceof Nurse) {
                for (int positionArrayListNurses = 0; positionArrayListNurses < nurseList.size(); positionArrayListNurses++) {
                    nurseList.add(emp.get(positionArrayListNurses));
                }
            } else if (emp.get(positionArrayListEmployees) instanceof Receptionist) {
                for (int positionArrayListReceptionist = 0; positionArrayListReceptionist < receptionistList.size(); positionArrayListReceptionist++) {
                    receptionistList.add(emp.get(positionArrayListReceptionist));
                }
            } else if (emp.get(positionArrayListEmployees) instanceof CenterCoordinator) {
                for (int positionArrayListCentreCoordinator = 0; positionArrayListCentreCoordinator < centreCoordinatorList.size(); positionArrayListCentreCoordinator++) {
                    centreCoordinatorList.add(emp.get(positionArrayListCentreCoordinator));
                }
            }
        }
    }

    public ArrayList<Employee> getNurseList(){
        return nurseList;
    }

    public ArrayList<Employee> getReceptionistList() {
        return receptionistList;
    }

    public ArrayList<Employee> getCentreCoordinatorList() {
        return centreCoordinatorList;
    }


}
