package app.miscellaneous;

import app.controller.App;
import app.controller.RecordVaccineAdministrationController;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RecoveryPeriodTimer {

    public RecoveryPeriodTimer() {

    }

    private final RecordVaccineAdministrationController recordVaccineAdministrationController = new RecordVaccineAdministrationController();

    public void printUserRecoveryTimeSMS() {
        Timer timer = new Timer();

        int recoveryPeriod = App.getInstance().getRecoveryTime();
        long recoveryPeriodMili = recoveryPeriod * 600000L;

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    recordVaccineAdministrationController.printRecoveryTime();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.schedule(timerTask, recoveryPeriodMili);
    }
}
