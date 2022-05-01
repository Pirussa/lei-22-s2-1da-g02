package app.ui;

import app.controller.SpecifyNewVaccineTypeController;
import app.domain.model.VaccineType;
import app.ui.console.MainMenuUI;
import app.ui.console.utils.Utils;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

//Teste
public class Main {

    public static void main(String[] args) {
        try {
            bootstrapOptional();

            MainMenuUI menu = new MainMenuUI();

            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void bootstrapOptional() {
        SpecifyNewVaccineTypeController ctrl = new SpecifyNewVaccineTypeController();

        ctrl.saveVaccineType("AAAAA", "Vaccine Type 1", VaccineType.vaccineTechnologies[0]);
        ctrl.saveVaccineType("BBBBB", "Vaccine Type 2", VaccineType.vaccineTechnologies[1]);
        ctrl.saveVaccineType("CCCCC", "Vaccine Type 3", VaccineType.vaccineTechnologies[2]);

    }
}
