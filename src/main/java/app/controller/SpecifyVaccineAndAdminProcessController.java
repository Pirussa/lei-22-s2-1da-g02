package app.controller;

import app.domain.model.AdministrationProcess;
import app.domain.model.Company;
import app.domain.model.VaccineType;
import app.ui.console.VaccineAndAdminProcessDto;

import java.util.ArrayList;

/** US013 - Specify Vaccine and its Administration Process Controller
 * @author Gustavo Jorge <1211061@isep.ipp.pt>
 */

public class SpecifyVaccineAndAdminProcessController {

    private Company company = App.getInstance().getCompany();

    public SpecifyVaccineAndAdminProcessController() {
    }

    public boolean specifyNewVaccineAndAdminProcess(VaccineAndAdminProcessDto dto) {

        if (company.specifyNewVaccineAndAdminProcess(dto)) {
            return true;
        }
        return false;
    }

    public ArrayList<VaccineType> getVaccineTypesList() {
        return company.getVaccineTypes();
    }


    public void saveVaccine() {
        company.saveVaccine();
    }
}
