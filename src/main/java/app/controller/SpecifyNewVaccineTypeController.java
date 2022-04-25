package app.controller;


import app.domain.model.Company;


/**
 * US012 - Specify Vaccine Type
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController {

    private Company company = App.getInstance().getCompany();


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

    /**
     * Saves a Vaccine Type into the Company storage.
     * Company Vaccines Storage: vaccineTypes
     */
    public void saveVaccineType(String code, String description, String technolgy) {
        company.saveVaccineType(code, description, technolgy);
    }


}
