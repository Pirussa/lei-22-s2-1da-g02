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
            Utils.bootstrapOptional();

            MainMenuUI menu = new MainMenuUI();

            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
