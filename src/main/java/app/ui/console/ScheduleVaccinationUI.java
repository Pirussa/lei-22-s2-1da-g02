package app.ui.console;


import app.controller.ScheduleVaccinationController;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationUI implements Runnable{

    public ScheduleVaccinationUI() {}

    public void run() {
        ScheduleVaccinationController ctrl = new ScheduleVaccinationController();

    }
}
