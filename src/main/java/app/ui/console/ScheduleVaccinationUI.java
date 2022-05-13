package app.ui.console;

import app.controller.ScheduleVaccinationController;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

/**
 * US010 - Register New Employee UI
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationUI implements Runnable {

    ScheduleVaccinationController ctrl = new ScheduleVaccinationController();

    private AuthFacade auth = new AuthFacade();
    public ScheduleVaccinationUI() {
    }

    public void run() {
        System.out.printf("%n----------------------%n|Vaccination Schedule|%n----------------------%n%n");
        int vaccinationCenterReceptionist = Utils.showAndSelectIndex(ctrl.getVaccinationCenterList(), "Vaccination Centers");
        String snsNumber;

        do {

            snsNumber = Utils.readLineFromConsole("Introduce SNS Number: ");

        } while (!SNSUser.validateSNSUserNumber(snsNumber));

        Utils.showList(auth.getUsers(), "Users List");

        }
    }

    /*
     Como iria funcionar? - O User dirige-se à rececionista e procede ao agendamento da vacina, a rececionista deve ter em conta,
      a data de nascimento e o tempo desde a última vacina que o user tomou.
    */