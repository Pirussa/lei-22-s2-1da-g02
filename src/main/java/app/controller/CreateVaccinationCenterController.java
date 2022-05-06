package app.controller;

import app.domain.model.*;
import app.ui.console.CreateVaccinationCenterUI;
import app.ui.console.HealthcareCenterDto;
import app.ui.console.MassVaccinationCenterDto;
import app.ui.console.VaccinationCenterDto;

import java.util.ArrayList;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterController {

    private Company company = App.getInstance().getCompany();

    public CreateVaccinationCenterController(){}

    public void createMassVaccinationCenter(MassVaccinationCenterDto dto){
        company.createMassVaccinationCenter(dto);
    }

    public void saveMassVaccinationCenter(MassVaccinationCenterDto dto){
        company.saveMassVaccinationCenter(dto);
    }

    public void createHealthcareCenter(HealthcareCenterDto dto){
        company.createHealthcareCenter(dto);
    }

    public void saveHealthcareCenter(HealthcareCenterDto dto){
        company.saveHealthcareCenter(dto);
    }

    public ArrayList<VaccinationCenter> getVaccinationCenters() {
         return company.getVaccinationCenters();
    }

    public ArrayList<MassVaccinationCenter> getMassVaccinationCenters() {
        return company.getMassVaccinationCenters();
    }

    public ArrayList<HealthcareCenter> getHealthcareCenters() {
        return company.getHealthcareCenters();
    }

    public void centerCoordinatorIDList(){
        company.centerCoordinatorIDList();
    }

    public ArrayList<String> getCenterCoordinatorIDs(){
        return company.getCenterCoordinatorIDs();
    }

    public void fillListOfEmployeesWithAGivenRole(){
        company.fillListOfEmployeesWithAGivenRole();
    }

}

