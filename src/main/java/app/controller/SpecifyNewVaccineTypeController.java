package app.controller;


import app.domain.model.Company;


/** US012 - Specify Vaccine Type
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController {

    private Company company = App.getInstance().getCompany();


    /**
     * Specifies a new Vaccine Type:
     * <p>
     * <p>
     * The method should create a vaccine type that should be validated, if so, it returns true
     *
     * @param type a String to validate
     * @return true if the type is valid
     */
    public boolean specifyNewVaccineType(String type){
        return company.specifyNewVaccineType(type);
    }

    /**
     * Saves a Vaccine Type into the Company storage.
     * Company Vaccines Storage: vaccineTypes
     */
    public void saveVaccineType(String type) {
        company.saveVaccineType(type);
    }




}
