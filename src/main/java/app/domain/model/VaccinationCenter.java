package app.domain.model;

import app.controller.CreateVaccinationCenterController;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class VaccinationCenter{

    Employee thisCoordinator;
    Company thisCompany;

    CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

    private final int MAXCHAROFPHONENUMBER = 9;

    private String strID;
    private String strName;
    private String strPhoneNumber;
    private String strEmail;
    private String strFax;
    private String strWebsite;
    private String strOpeningHour;
    private String strClosingHour;
    private String strSlotDuration;
    private String strVaccinesPerSlot;
    private String strRoad;
    private String strZipCode;
    private String strLocal;
    private String strCenterCoordinatorID;

    private String[] strTopLevelDomain = {".com",".pt",".co.uk"};
    private String[] strEmailDomain= {"gmail","outlook","isep.ipp","protonmail","live"};
    private String strWorldWideWeb= "www";

    public VaccinationCenter(String strID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                             String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot,
                             String strRoad, String strZipCode, String strLocal, String strCenterCoordinatorID) {

        if (((strID==null) ||(strName==null) || (strPhoneNumber==null) || (strEmail==null) || (strFax==null) || (strWebsite==null) || (strOpeningHour==null) ||
                (strClosingHour==null) || (strSlotDuration==null)  || (strVaccinesPerSlot==null) || (strRoad==null) || (strZipCode==null) ||
                (strLocal==null) || (strCenterCoordinatorID==null) || (strID.isEmpty()) ||(strName.isEmpty()) || (strPhoneNumber.isEmpty() || (strEmail.isEmpty()) ||
                (strFax.isEmpty()) || (strWebsite.isEmpty()) || (strOpeningHour.isEmpty()) || (strClosingHour.isEmpty()) || (strSlotDuration.isEmpty()) ||
                (strVaccinesPerSlot.isEmpty()) ||(strRoad.isEmpty()||(strZipCode.isEmpty()||(strLocal.isEmpty()||(strCenterCoordinatorID.isEmpty())))))))
        throw new IllegalArgumentException("Arguments can't be null or empty");

        /*
        if (intID <= 0) throw new IllegalArgumentException("ID needs to be !=0 and a positive number");

        if (strPhoneNumber.strip().length() != MAXCHAROFPHONENUMBER) throw new IllegalArgumentException("Phone Number need to have exactly 9 characters.");

        if(!onlyDigits(strPhoneNumber)){
            throw new IllegalArgumentException("Phone Numbers only support integers from 0 to 9.");
        }*/
        /*
        if (!verifyEmail(strEmail,strEmailDomain, strTopLevelDomain)){
            throw new IllegalArgumentException("Email needs to have @, one of the available email domains and one of the top level domains saved.");
        }

        if (!verifyWebsite(strWebsite, strTopLevelDomain,strWorldWideWeb))
            throw new IllegalArgumentException("Website needs to star with www. and have one of the valid domains inside the domain vector.");

        if (!verifyVaccinationCenterHours(strOpeningHour,strClosingHour))
            throw new IllegalArgumentException("Hours need to be between 0 and 24, and opening hour cant be higher than closing hour.");
        */
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
    }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                ", strID=" + strID +
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
                '}';
    }

    public static boolean verifyVaccinationCenterHours(String strOpeningHour, String strClosingHour) {
        if (Integer.valueOf(strOpeningHour) > 0 || Integer.valueOf(strOpeningHour) < 24 || Integer.valueOf(strClosingHour) > 0 || Integer.valueOf(strClosingHour) < 24)
        {
            if (Integer.valueOf(strOpeningHour) < Integer.valueOf(strClosingHour))
            {
                return true;
            } else return false;
        } else return false;
    }


    public static boolean verifyWebsite(String strWebsite, String[] strDomain, String strWorldWideWeb){
        for (int i = 0; i <= strWebsite.length() ; i++) {
            if (strWebsite.startsWith(strWorldWideWeb) && strWebsite.endsWith(String.valueOf(strDomain))){
            return true;
            } else return false;
        } return false;
    }

    public static boolean verifyEmail(String strEmail, String[] strEmailDomain, String[] strDomain){
        for (int i = 0; i <= strEmail.length(); i++) {
            if (strEmail.contains("@"+strEmailDomain+strDomain)){
                return true;
            } else return false;
        } return false;
    }

    public static boolean onlyDigits(String strPhoneNumber)
    {
        // Traverse the string from
        // start to end
        for (int i = 0; i < strPhoneNumber.length(); i++) {

            // Check if character is
            // digit from 0-9
            // then return true
            // else false
            if (strPhoneNumber.charAt(i) >= '0'
                    && strPhoneNumber.charAt(i) <= '9') {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}
