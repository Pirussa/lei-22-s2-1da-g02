package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register a vaccination center.", new ShowTextUI("You have chosen to register a vaccination center.")));
        options.add(new MenuItem("Register an Employee.", new ShowTextUI("You have chosen to register an Employee.")));
        options.add(new MenuItem("Get a list of Employees", new ShowTextUI("You have chosen to get a list of Employees.")));
        options.add(new MenuItem("Specify a new vaccine type", new SpecifyNewVaccineTypeUI()));
        options.add(new MenuItem("Specify a new vaccine and its administration process.", new SpecifyVaccineAndAdminProcessUI()));
        

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
    }
}
