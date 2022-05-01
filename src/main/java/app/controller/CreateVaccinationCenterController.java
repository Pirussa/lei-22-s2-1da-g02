package app.controller;

import app.domain.model.*;
import app.ui.console.CreateVaccinationCenterUI;
import app.ui.console.VaccinationCenterDto;

import java.util.List;

public class CreateVaccinationCenterController {

    private Company company = App.getInstance().getCompany();
    private CreateVaccinationCenterUI ui = new CreateVaccinationCenterUI();

    public CreateVaccinationCenterController(){}

    public boolean createVaccinationCenter(VaccinationCenterDto dto){
        return company.createVaccinationCenter(dto);
    }

    public void saveVaccinationCenter(VaccinationCenterDto dto){
        company.saveVaccinationCenter(dto);
    }

    public List<VaccinationCenter> getVaccinationCenters() {
        return company.getVaccinationCenters();
    }

}

