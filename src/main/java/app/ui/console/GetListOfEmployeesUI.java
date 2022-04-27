package app.ui.console;

import app.controller.GetListOfEmployeesController;
import app.controller.App;
import app.domain.model.Company;
import app.domain.model.Employee;
import app.domain.shared.Constants;

import java.util.ArrayList;


/**
 * US011 - Get a list of Employees with a given role.
 *
 * @author João Leitão <1211063@isep.ipp.pt>
 */

public class GetListOfEmployeesUI implements Runnable {

    public GetListOfEmployeesUI() {
    }

    private GetListOfEmployeesController ctrl = new GetListOfEmployeesController();

    public void run() {
    }
}
