package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import dto.HealthcareCenterDto;
import dto.MassVaccinationCenterDto;

import java.io.NotSerializableException;
import java.util.ArrayList;
import java.util.List;

/**
 * Coordinates the passage of information between the UI and the model
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class CreateVaccinationCenterController {

    private Company company = App.getInstance().getCompany();

    GenericClass<VaccinationCenter> generics = new GenericClass<>();

    public CreateVaccinationCenterController(){}

    /**
     * Calls the createMassVaccinationCenter method inside the company
     *
     */
    public void createMassVaccinationCenter(MassVaccinationCenterDto dto){
        company.createMassVaccinationCenter(dto);
    }

    /**
     * Calls the saveMassVaccinationCenter method inside the company
     *
     */
    public void saveMassVaccinationCenter(MassVaccinationCenterDto dto){
        company.saveMassVaccinationCenter(dto);
    }

    /**
     * Calls the createHealthcareCenter method inside the company
     *
     */
    public void createHealthcareCenter(HealthcareCenterDto dto){
        company.createHealthcareCenter(dto);
    }

    /**
     * Calls the saveHealthcareCenter method inside the company
     *
     */
    public void saveHealthcareCenter(HealthcareCenterDto dto){
        company.saveHealthcareCenter(dto);
    }

    /**
     * Gets a list of vaccination centers
     *
     * @return a List of Vaccination Centers
     */
    public ArrayList<VaccinationCenter> getVaccinationCenters() {
         return company.getVaccinationCenters();
    }

    /**
     * Gets a list of mass vaccination centers
     *
     * @return a List of Mass Vaccination Centers
     */
    public ArrayList<MassVaccinationCenter> getMassVaccinationCenters() {
        return company.getMassVaccinationCenters();
    }

    /**
     * Gets a list of healthcare centers
     *
     * @return a List of Healthcare Centers
     */
    public ArrayList<HealthcareCenter> getHealthcareCenters() {
        return company.getHealthcareCenters();
    }

    /**
     * Calls the centerCoordinatorIDList method inside the company.
     *
     */
    public void centerCoordinatorIDList(){
        company.centerCoordinatorIDList();
    }

    /**
     * Gets a list of all the vaccine types.
     *
     * @return a List of all Vaccine Types.
     */
    public List<VaccineType> getVaccineTypeList(){
        return company.getVaccineTypes();
    }

    /**
     * Gets a list of center coordinator's IDs.
     *
     * @return a List of Center Coordinator's IDs.
     */
    public ArrayList<String> getCenterCoordinatorIDs(){
        return company.getCenterCoordinatorIDs();
    }

    /**
     * Calls the fillListOfEmployeesWithAGivenRole method inside the company.
     *
     */
    public void fillListOfEmployeesWithAGivenRole(){
        company.fillListOfEmployeesWithAGivenRole();
    }

}

