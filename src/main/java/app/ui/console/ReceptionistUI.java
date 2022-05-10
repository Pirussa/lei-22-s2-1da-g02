package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {

    @Override
    public void run() {
        List<MenuItem> options = new ArrayList<>();

        options.add(new MenuItem("Schedule a vaccination.", new CreateVaccinationCenterUI()));
        options.add(new MenuItem("Register the arrival of a SNS user to take the vaccine.", new MainMenuUI()));

        options.add(new MenuItem("Logout.",new MainMenuUI() ));





        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }
}
