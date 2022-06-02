package app.controller;


import app.domain.model.*;
import app.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.util.List;
import java.util.Objects;


/**
 * US012 - Specify Vaccine Type
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController implements Serializable {

    private  Company company = App.getInstance().getCompany();

    private static VaccineType vt;
    /**
     * Specifies a new Vaccine Type:
     * <p>
     * <p>
     * The method should create a vaccine type that should be validated.
     *
     * @param description a String to validate
     * @param
     * @param
     * @return true if the type is valid
     */
    public boolean specifyNewVaccineType(String code, String description, String technology) {
        return company.specifyNewVaccineType(code, description, technology);
    }

    public List<VaccineType> getVaccineTypes(){
        return company.getVaccineTypes();
    }

    /**
     * Saves a Vaccine Type into the Company storage.
     * Company Vaccines Storage: vaccineTypes
     */
    public void saveVaccineType(String code, String description, String technology) {
        vt = new VaccineType(code, description, technology);
        company.saveVaccineType(code, description, technology);
    }
    GenericClass<VaccineType> vaccineType = new GenericClass<>(vt);

    public void vaccineTypeExport() throws NotSerializableException {
        vaccineType.binaryFileWrite(Constants.VACCINE_TYPE_FILE_NAME,company.getVaccineTypes());
    }

    public void vaccineTypeImport()throws WriteAbortedException {
        vaccineType.binaryFileRead(Constants.VACCINE_TYPE_FILE_NAME);
    }

}