package app.controller;

import app.domain.model.AdministrationProcess;
import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.ui.console.VaccineAndAdminProcessDto;

import java.util.ArrayList;

/** US013 - Specify Vaccine and its Administration Process Controller
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class SpecifyVaccineAndAdminProcessController {

    private Company company = App.getInstance().getCompany();

    public SpecifyVaccineAndAdminProcessController() {
    }

    /**
     * Specifies a new Vaccine and its Administration Process:
     * <p>
     * <p>
     * The method should create an Administration Process that should be validated, if so,
     * it creates a Vaccine, if the Vaccine is also validated successfully it is added to the Company storage.
     *
     * @param dto A data transfer object with all the necessary information in order to specify both the Administration Process and the Vaccine.
     * @return true if the Vaccine is created and validated with success.
     */
    public boolean specifyNewVaccineAndAdminProcess(VaccineAndAdminProcessDto dto) {

        if (company.specifyNewVaccineAndAdminProcess(dto)) {
            return true;
        }
        return false;
    }


    /** Gets the list with all the Vaccine Types available
     *
     * @return an ArrayList with all the Vaccine Types that are available to choose.
     */
    public ArrayList<VaccineType> getVaccineTypesList() {
        return company.getVaccineTypes();
    }


    public void saveVaccine(VaccineAndAdminProcessDto dto) {
        company.saveVaccine(dto);
    }
}
