package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;

/**
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController {

    private Company company;

    public boolean createVaccineType(String type) {
        return company.specifyNewVaccineType(type);
    }

    public void saveVaccineType(String type) {
        VaccineType.saveVaccineType(type);
    }

}
