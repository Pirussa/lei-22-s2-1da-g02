package app.controller;

import app.domain.model.VaccineType;

/**
 *
 * @author Pedro Monteiro <1211076@isep.ipp.pt>
 */

public class SpecifyNewVaccineTypeController {

    private VaccineType vaccineType;

    public boolean validateType(String type) {
        return vaccineType.addVaccineType(type);
    }
}
