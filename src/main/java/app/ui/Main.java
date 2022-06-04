package app.ui;

import app.controller.App;
import app.domain.model.Company;
import app.ui.console.MainMenuUI;
import app.ui.console.utils.Utils;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class Main {

    public static void main(String[] args) {
        Company company = App.getInstance().getCompany();
        Utils.bootstrap();
        company.authenticateSNSUser();
        company.authenticateEmployees();
        try {

            company.registerDailyTotalOfPeopleVaccinated();

            MainMenuUI menu = new MainMenuUI();

            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}