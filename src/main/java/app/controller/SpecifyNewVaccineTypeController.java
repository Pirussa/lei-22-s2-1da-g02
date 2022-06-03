package app.controller;


import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import app.ui.console.utils.Utils;

import java.io.EOFException;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.io.WriteAbortedException;
import java.util.List;


/**
 * US012 - Specify Vaccine Type
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController implements Serializable {

    private Company company = App.getInstance().getCompany();

    GenericClass<VaccineType> generics = new GenericClass<>();

    /**
     * Specifies a new Vaccine Type:
     * <p>
     * <p>
     * The method should create a vaccine type that should be validated.
     *
     * @param description a String to validate
     * @param code       a String to validate
     * @param technology a String to validate
     * @return true if the type is valid
     */
    public boolean specifyNewVaccineType(String code, String description, String technology) {
        return company.specifyNewVaccineType(code, description, technology);
    }


    public List<VaccineType> getVaccineTypes() {
        return company.getVaccineTypes();
    }


    /**
     * Saves a Vaccine Type into the Company storage.
     * Company Vaccines Storage: vaccineTypes
     */
    public void saveVaccineType(String code, String description, String technology) {
        company.saveVaccineType(code, description, technology);
    }



    /**
     * Exports the list of Vaccine Types to a binary file.
     * @throws NotSerializableException
     */
    public void exportDataToFile() throws NotSerializableException {
        generics.binaryFileWrite(Constants.FILE_PATH_VACCINE_TYPES, getVaccineTypes());
    }


}