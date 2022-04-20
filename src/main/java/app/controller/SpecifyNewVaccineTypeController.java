package app.controller;

<<<<<<< HEAD
import app.domain.model.Company;
=======
>>>>>>> 6c82577a240c2e95d8569e935a8c6ff64d349c98
import app.domain.model.VaccineType;

/**
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController {

<<<<<<< HEAD
    private Company company;

    public boolean createVaccineType(String type) {
        return company.specifyNewVaccineType(type);
    }

    public void saveVaccineType(String type) {
        VaccineType.saveVaccineType(type);
    }

=======
    private VaccineType vaccineType;

    public boolean validateType(String type) {
        return vaccineType.addVaccineType(type);
    }
>>>>>>> 6c82577a240c2e95d8569e935a8c6ff64d349c98
}
