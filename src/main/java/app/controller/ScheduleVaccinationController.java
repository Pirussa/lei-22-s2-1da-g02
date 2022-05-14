package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.util.ArrayList;

/**
 * US010 - Register New Employee Controller
 *
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class ScheduleVaccinationController {

    private Company company = App.getInstance().getCompany();

    public ScheduleVaccinationController() {}

    public ArrayList<String> getVaccinationCenterList() {
        ArrayList<String> getVaccinationCenterNameList = new ArrayList<>();

        for (int position = 0; position < company.getVaccinationCenters().size(); position++) {
            getVaccinationCenterNameList.add(company.getVaccinationCenters().get(position).getStrName());
        }

        return getVaccinationCenterNameList;
    }
    public ArrayList<SNSUser> getSNSUsersList() {
        return company.getSNSUserList();
    }
}
