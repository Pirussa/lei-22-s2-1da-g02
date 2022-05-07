package app.ui.console;

import app.domain.model.VaccineType;

public class MassVaccinationCenterDto {
    public MassVaccinationCenterDto() {
    }
    public String strID;
    public String strName;
    public String strPhoneNumber;
    public String strEmail;
    public String strFax;
    public String strWebsite;
    public String strOpeningHour;
    public String strClosingHour;
    public String strSlotDuration;
    public String strVaccinesPerSlot;
    public String strRoad;
    public String strZipCode;
    public String strLocal;
    public String strCenterCoordinatorID;
    public String strVaccineType;

    public MassVaccinationCenterDto(String strID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                                String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot,
                                String strRoad, String strZipCode, String strLocal, String strCenterCoordinatorID, String strVaccineType) {
        this.strID = strID;
        this.strName = strName;
        this.strPhoneNumber = strPhoneNumber;
        this.strEmail = strEmail;
        this.strFax = strFax;
        this.strWebsite = strWebsite;
        this.strOpeningHour = strOpeningHour;
        this.strClosingHour = strClosingHour;
        this.strSlotDuration = strSlotDuration;
        this.strVaccinesPerSlot = strVaccinesPerSlot;
        this.strRoad=strRoad;
        this.strZipCode=strZipCode;
        this.strLocal=strLocal;
        this.strCenterCoordinatorID=strCenterCoordinatorID;
        this.strVaccineType = strVaccineType;
    }

    @Override
    public String toString() {
        return "VaccinationCenterDto{" +
                "strID=" + strID +
                ", strName='" + strName + '\'' +
                ", strPhoneNumber='" + strPhoneNumber + '\'' +
                ", strEmail='" + strEmail + '\'' +
                ", strFax='" + strFax + '\'' +
                ", strWebsite='" + strWebsite + '\'' +
                ", strOpeningHour='" + strOpeningHour + '\'' +
                ", strClosingHour='" + strClosingHour + '\'' +
                ", strSlotDuration='" + strSlotDuration + '\'' +
                ", strVaccinesPerSlot='" + strVaccinesPerSlot + '\'' +
                ", strRoad='" + strRoad + '\'' +
                ", strZipCode='" + strZipCode + '\'' +
                ", strLocal='" + strLocal + '\'' +
                ", strCenterCoordinatorID='" + strCenterCoordinatorID + '\'' +
                ", strVaccineType='" + strVaccineType + '\'' +
                '}';
    }
}
