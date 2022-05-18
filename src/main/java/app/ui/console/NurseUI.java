package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {
    @Override
    public void run() {

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Consult the users in the waiting room of a Vaccination Centre.", new ConsultUsersInTheWaitingRoomUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nSNS User Menu:");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        }
        while (option != -1);
    }


}

