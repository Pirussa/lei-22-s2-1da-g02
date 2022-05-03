package app.controller;

import app.domain.model.*;
import app.ui.console.CreateVaccinationCenterUI;
import app.ui.console.VaccinationCenterDto;

import java.util.List;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterController {

    private Company company = App.getInstance().getCompany();
    private CreateVaccinationCenterUI ui = new CreateVaccinationCenterUI();

    public CreateVaccinationCenterController(){}

    public void createVaccinationCenter(VaccinationCenterDto dto){
       company.createVaccinationCenter(dto);
    }

    public void saveVaccinationCenter(VaccinationCenterDto dto){
        company.saveVaccinationCenter(dto);
    }

    public void getVaccinationCenters() {
        company.getVaccinationCenters();
    }

}

