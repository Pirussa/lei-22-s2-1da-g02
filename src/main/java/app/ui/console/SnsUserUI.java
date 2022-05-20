package app.ui.console;

import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SnsUserUI implements Runnable {
    @Override
    public void run() {
        VaccinationCenter vaccinationCenter = Utils.selectVaccinationCenterUI();

        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Schedule a vaccine.", new ScheduleVaccineUI(vaccinationCenter)));



        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nSNS User Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );


    }
}
