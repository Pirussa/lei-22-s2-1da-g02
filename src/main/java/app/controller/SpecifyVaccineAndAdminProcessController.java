package app.controller;

import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import dto.VaccineAndAdminProcessDto;

import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.List;

/**
 * US013 - Specify Vaccine and its Administration Process Controller
 *
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class SpecifyVaccineAndAdminProcessController {

    private Company company = App.getInstance().getCompany();
    GenericClass<Vaccine> generics = new GenericClass<>();

    public SpecifyVaccineAndAdminProcessController() {
    }

    /**
     * Specifies a new Vaccine and its Administration Process:
     * <p>
     * <p>
     * The method should create an Administration Process that should be validated, if so,
     * it creates a Vaccine that should be validated.
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


    /**
     * Gets the list with all the Vaccine Types available
     *
     * @return an ArrayList with all the Vaccine Types that are available to choose.
     */
    public List<VaccineType> getVaccineTypesList() {
        return company.getVaccineTypes();
    }

    /**
     * Saves a Vaccine Type into the Company storage.
     */
    public void saveVaccine(VaccineAndAdminProcessDto dto) {
        company.saveVaccine(dto);
    }

    /**
     * Exports the list of Vaccine Types to a binary file.
     * @throws NotSerializableException
     */
    public void exportDataToFile() throws NotSerializableException {
        generics.binaryFileWrite(Constants.FILE_PATH_VACCINES, company.getVaccines());
    }
}
