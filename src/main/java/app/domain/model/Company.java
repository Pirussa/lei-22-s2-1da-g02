package app.domain.model;

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
     * it creates a Vaccine, if the Vaccine is also validated successfully it is added to the Company storage.
     *
     * @param dto A data transfer object with all the necessary information in order to specify both the Administration Process and the Vaccine.
     * @return true if the Vaccine is created with success .
     */
    public boolean specifyNewVaccineAndAdminProcess(VaccineAndAdminProcessDto dto) {
        AdministrationProcess adminProcess = new AdministrationProcess(dto.ageGroups, dto.numberOfDoses, dto.dosage, dto.timeIntervalBetweenVaccines);
        if (adminProcess.validateAdministrationProcess()) {
            Vaccine vac = new Vaccine(dto.name, dto.id, dto.brand, adminProcess, dto.vt);
            return vac.validateVaccine();
        }
        return false;
    }

    public void saveVaccine() {

        //vaccines.add()
    }


    public boolean specifyNewVaccineType(String type) {
        if (type != null && !type.isEmpty())
            return false;

        VaccineType vaccineType = new VaccineType(type);
        vaccineTypes.add(vaccineType);
        return true;
    }


}
