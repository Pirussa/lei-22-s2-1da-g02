package app.domain.model;

import app.controller.CreateVaccinationCenterController;

import java.util.Arrays;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class VaccinationCenter{

    Employee thisCoordinator;
    Company thisCompany;

    CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

    private final int MAXCHAROFPHONENUMBER = 9;

    private int intID;
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

    private String[] strDomain= {"com","pt","co.uk"};

    public VaccinationCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                             String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot,
                             String strRoad, String strZipCode, String strLocal, String strCenterCoordinatorID) {

        if (((strName==null) || (strPhoneNumber==null) || (strEmail==null) || (strFax==null) || (strWebsite==null) || (strOpeningHour==null) ||
                (strClosingHour==null) || (strSlotDuration==null)  || (strVaccinesPerSlot==null) || (strRoad==null) || (strZipCode==null) ||
                (strLocal==null) || (strCenterCoordinatorID==null) || (strName.isEmpty()) || (strPhoneNumber.isEmpty() || (strEmail.isEmpty()) ||
                (strFax.isEmpty()) || (strWebsite.isEmpty()) || (strOpeningHour.isEmpty()) || (strClosingHour.isEmpty()) || (strSlotDuration.isEmpty()) ||
                (strVaccinesPerSlot.isEmpty()) ||(strRoad.isEmpty()||(strZipCode.isEmpty()||(strLocal.isEmpty()||(strCenterCoordinatorID.isEmpty())))))))
        throw new IllegalArgumentException("Arguments can't be null or empty");

        if (intID <= 0) throw new IllegalArgumentException("ID needs to be !=0 and a positive number");

        if (strPhoneNumber.strip().length() != MAXCHAROFPHONENUMBER) throw new IllegalArgumentException("Phone Number need to have exactly 9 characters.");

        if(!onlyDigits(strPhoneNumber)){
            throw new IllegalArgumentException("Phone Numbers only support integers from 0 to 9.");
        }

        if (!verifyEmail(strEmail)){
            throw new IllegalArgumentException("Email needs to have at least one @ and one .");
        }

        if (!verifyWebsite(strWebsite,strDomain))
            throw new IllegalArgumentException("Website needs to star with www. and have one of the valid domains inside the domain vector.");

        this.intID = intID;
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
                ", intID=" + intID +
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


    public static boolean verifyWebsite(String strWebsite, String[] strDomain){
        for (int i = 0; i <= strWebsite.length() ; i++) {
            if (strWebsite.contains("www.") && strWebsite.contains("."+ Arrays.stream(strDomain).findAny())){
            return true;
            } else return false;
        } return false;
    }

    public static boolean verifyEmail(String strEmail){
        for (int i = 0; i <= strEmail.length(); i++) {
            if (strEmail.contains("@") && strEmail.contains(".")){
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
