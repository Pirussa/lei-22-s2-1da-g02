package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

/**
 * The Center coordinator menu controller.
 * @author Gustavo Jorge
 * @author Pedro Monteiro
 */
public class CenterCoordinatorMenuController {

    private final VaccinationCenter center;

    /**
     * Instantiates a new Center coordinator menu controller.
     */
    public CenterCoordinatorMenuController() {
    center = company.getVaccinationCenterAssociatedToCoordinator( Utils.getLoggedCoordinatorId());
    }

    private final Company company = App.getInstance().getCompany();

    /**
     * Checks if the company has enough info for vaccination stats.
     *
     * @return the int related to the error or success of the operation
     */
    public int companyHasEnoughInfoForVaccinationStats() {

        if (company.getVaccinationCenters().isEmpty()) {
            return 1;
        }

        if (center.getVaccinesAdministeredList().isEmpty()) {
            return 2;
        }

        return 0;
    }


}
