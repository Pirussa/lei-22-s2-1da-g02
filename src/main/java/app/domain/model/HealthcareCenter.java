package app.domain.model;

import app.controller.App;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class HealthcareCenter extends VaccinationCenter{

    private String strARS;
    private String strAGES;
    private ArrayList <String> strVaccineType;

    public HealthcareCenter(String strID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite, String strOpeningHour, String strClosingHour, String strSlotDuration,
                            String strVaccinesPerSlot, String strRoad, String strZipCode, String strLocal, String strCenterCoordinatorID, String strARS, String strAGES, ArrayList<String> strVaccineType) {
        super(strID, strName, strPhoneNumber, strEmail, strFax, strWebsite, strOpeningHour, strClosingHour, strSlotDuration, strVaccinesPerSlot, strRoad, strZipCode, strLocal, strCenterCoordinatorID);

        if ((strARS==null) || (strAGES==null) || (strVaccineType==null) || (strARS.isEmpty()) || (strAGES.isEmpty()))
            throw new IllegalArgumentException("Arguments can't be null or empty");
        this.strARS = strARS;
        this.strAGES = strAGES;
        this.strVaccineType=strVaccineType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Regional Health Administration of the Vaccination Center: " + strARS + '\n' +
                "Grouping of the Vaccination Center: " + strAGES + '\n' +
                "Vaccine Types administered in the Vaccination Center: " + strVaccineType;
    }
}


