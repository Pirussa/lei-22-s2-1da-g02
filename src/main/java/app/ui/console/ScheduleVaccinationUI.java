package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccinationController;
import app.domain.model.*;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.Objects;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationUI implements Runnable {

    ScheduleVaccinationController ctrl = new ScheduleVaccinationController();

    private static Company company = App.getInstance().getCompany();

    public ScheduleVaccinationUI() {
    }

    public void run() {
        System.out.printf("%n----------------------%n|Schedule Vaccination|%n----------------------%n%n");
        String snsNumber;

        do {

            snsNumber = Utils.readLineFromConsole("Introduce SNS Number: ");

        } while (!SNSUser.validateSNSUserNumber(Objects.requireNonNull(snsNumber)) || SNSUser.userExists(snsNumber) < 0);

        int vaccinationCenterReceptionist = Utils.showAndSelectIndex(Utils.getVaccinationCenterList(), "Vaccination Centers");
        VaccinationCenter vaccinationCenter = company.getVaccinationCenters().get(vaccinationCenterReceptionist);
    }
}
    /*
     Como iria funcionar? - O User dirige-se à rececionista e procede ao agendamento da vacina, a rececionista deve ter em conta,
      a data de nascimento e o tempo desde a última vacina que o user tomou.
    */