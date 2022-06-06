package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

public class CenterCoordinatorMenuController {

    private final VaccinationCenter center;

    public CenterCoordinatorMenuController() {
    center = company.getVaccinationCenterAssociatedToCoordinator( Utils.getLoggedCoordinatorId());
    }

    private final Company company = App.getInstance().getCompany();

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
