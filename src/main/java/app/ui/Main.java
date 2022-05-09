package app.ui;

import app.ui.console.MainMenuUI;
import app.ui.console.utils.Utils;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

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