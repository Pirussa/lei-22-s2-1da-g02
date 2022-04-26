package app.domain.model;

import app.ui.console.RegisterNewEmployeeDto;
import app.ui.console.VaccineAndAdminProcessDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class Company {

    private String designation;
    private AuthFacade authFacade;

    private ArrayList<VaccineType> vaccineTypes = new ArrayList<>();

    private ArrayList<Vaccine> vaccines = new ArrayList<>();

    private ArrayList<Employee> employees = new ArrayList<>();

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
     */
    public void saveVaccineType(String code, String description, String technology) {
        VaccineType vt = new VaccineType(code, description, technology);
        vaccineTypes.add(vt);
    }
    public void addNewRole() {

    }
    public boolean registerNewEmployee(RegisterNewEmployeeDto dto) {
        Employee emp = new Employee(dto.role, dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber);
        return true;
    }

    public void saveCreatedEmployee(RegisterNewEmployeeDto dto) {
        Employee emp = new Employee(dto.role, dto.id, dto.name, dto.address, dto.phoneNumber, dto.email, dto.citizenCardNumber);
        employees.add(emp);
    }
}
