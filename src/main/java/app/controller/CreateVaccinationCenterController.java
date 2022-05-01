package app.controller;

import app.domain.model.*;

public class CreateVaccinationCenterController {

    private Company company = App.getInstance().getCompany();

    private VaccinationCenter vcVaccinationCenter;
    private HealthcareCenter hcHealthcareCenter;
    private MassVaccinationCenter mvcMassVaccinationCenter;

    public boolean newHealthcareCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite, String strOpeningHour,
                                       String strClosingHour, String strSlotDuration, String strVaccinesPerSlot, String strARS, String strAGES, String strRoad,
                                       String strZipCode, String strLocal, String strRole, int intCoordinatorID, String strCoordinatorName, String strCoordinatorAddress,
                                       int intCoordinatorPhoneNUmber, String strCoordinatorEmail, int intCoordinatorCitizenCardNumber, String strCoordinatorPassword)

}

