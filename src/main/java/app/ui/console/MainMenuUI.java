package app.ui.console;


import java.io.IOException;


/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class MainMenuUI {

    public MainMenuUI() {
    }

    public static void run() throws IOException {

        AdminUI za = new AdminUI();
        za.run();
//        List<MenuItem> options = new ArrayList<MenuItem>();
//        options.add(new MenuItem("Do Login", new AuthUI()));
//        options.add(new MenuItem("Know the Development Team", new DevTeamUI()));
//        int option = 0;
//        do {
//            option = Utils.showAndSelectIndex(options, "\n\nMain Menu");
//
//            if ((option >= 0) && (option < options.size())) {
//                options.get(option).run();
//            }
//        }
//        while (option != -1);
    }


}
