package app.domain.model;

import app.controller.CreateVaccinationCenterController;
import java.util.Objects;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class  VaccinationCenter{

    CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

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

    private static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;
    private static final int STARTING_NUMBER_PORTUGUESE_PHONE = 9;
    private static final int FIRST_SECOND_NUMBER_PORTUGUESE_PHONE = 1;
    private static final int SECOND_SECOND_NUMBER_PORTUGUESE_PHONE = 2;
    private static final int THIRD_SECOND_NUMBER_PORTUGUESE_PHONE = 3;
    private static final int FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE = 6;
    private static String[] strTopLevelDomain = {".com",".pt",".co.uk"};
    private static String strWorldWideWeb= "www.";

    public VaccinationCenter(String strID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                             String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot,
                             String strRoad, String strZipCode, String strLocal, String strCenterCoordinatorID) {

        if (((strID==null) ||(strName==null) || (strPhoneNumber==null) || (strEmail==null) || (strFax==null) || (strWebsite==null) || (strOpeningHour==null) ||
                (strClosingHour==null) || (strSlotDuration==null)  || (strVaccinesPerSlot==null) || (strRoad==null) || (strZipCode==null) ||
                (strLocal==null) || (strCenterCoordinatorID==null) || (strID.isEmpty()) ||(strName.isEmpty()) || (strPhoneNumber.isEmpty() || (strEmail.isEmpty()) ||
                (strFax.isEmpty()) || (strWebsite.isEmpty()) || (strOpeningHour.isEmpty()) || (strClosingHour.isEmpty()) || (strSlotDuration.isEmpty()) ||
                (strVaccinesPerSlot.isEmpty()) ||(strRoad.isEmpty()||(strZipCode.isEmpty()||(strLocal.isEmpty()||(strCenterCoordinatorID.isEmpty())))))))
        throw new IllegalArgumentException("Arguments can't be null or empty.");

        if(!validatePhoneNumberAndFax(strPhoneNumber)){
            throw new IllegalArgumentException("Only supports the Portuguese format, .e.i, 933398881.");
        }

        if(!validatePhoneNumberAndFax(strFax)){
            throw new IllegalArgumentException("Only supports the Portuguese format, .e.i, 933398881.");
        }

        if (!validateEmail(strEmail)){
            throw new IllegalArgumentException("Needs an @, a . and a valid domain,");
        }

        if (!validateWebsite(strWebsite, strTopLevelDomain,strWorldWideWeb))
            throw new IllegalArgumentException("Needs a valid prefix and domain.");

        if (!validateVaccinationCenterHours(strOpeningHour,strClosingHour))
            throw new IllegalArgumentException("Between 0 and 24, Opening Hour < Closing Hour.");

        if (!validateZipCode(strZipCode))
            throw new IllegalArgumentException("Zip Code format is invalid.");

        if (!validateSlotDuration(strSlotDuration)){
            throw new IllegalArgumentException("No more than three numerical chars.");
        }

        if (!validateVaccinesPerSlot(strVaccinesPerSlot)){
            throw new IllegalArgumentException("No more than three numerical chars.");
        }

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

    public String getStrCenterCoordinatorID() {
        return strCenterCoordinatorID;
    }

    @Override
    public String toString() {
        return "ID of the Vaccination Center: " + strID + '\n' +
                "Name of the Vaccination Center: " + strName + '\n' +
                "Phone Number of the Vaccination Center: " + strPhoneNumber+ '\n' +
                "Email of the Vaccination Center: " + strEmail + '\n' +
                "Fax of the Vaccination Center: " + strFax + '\n' +
                "Website of the Vaccination Center: " + strWebsite + '\n' +
                "Opening Hour of the Vaccination Center: " + strOpeningHour + '\n' +
                "Closing Hour of the Vaccination Center: " + strClosingHour + '\n' +
                "Slot Duration of the Vaccination Center: " + strSlotDuration + '\n' +
                "Maximum number of Vaccines per slot of the Vaccination Center: " + strVaccinesPerSlot + '\n' +
                "Road of the Vaccination Center: " + strRoad + '\n' +
                "Zip Code of the Vaccination Center: " + strZipCode + '\n' +
                "Local of the Vaccination Center: " + strLocal + '\n' +
                "Center Coordinator of the Vaccination Center: " + strCenterCoordinatorID + '\n';
    }

    public boolean validateVaccinationCenterHours(String strOpeningHour, String strClosingHour) {
        if (Integer.parseInt(strOpeningHour) >= 0 && Integer.parseInt(strOpeningHour) < 24 && Integer.parseInt(strClosingHour) > 0 && Integer.parseInt(strClosingHour) <= 24)
        {
            if (Integer.parseInt(strOpeningHour) < Integer.parseInt(strClosingHour))
            {
                return true;
            } else return false;
        } else return false;
    }


    public boolean validateWebsite(String strWebsite, String[] strTopLevelDomain, String strWorldWideWeb){

        for (int position = 0; position < strTopLevelDomain.length; position++) {
            if (strWebsite.startsWith(strWorldWideWeb) && strWebsite.endsWith(strTopLevelDomain[position]))
                return true;
        }
        return false;
    }

    public boolean validateEmail(String email) {
        if (!email.contains("@") && !email.contains("."))
            return false;

        String[] emailSplitter = email.split("@");
        String[] validEmailDomain = {"gmail.com", "hotmail.com", "isep.ipp.pt", "sapo.pt", "outlook.com"};

        for (int position = 0; position < validEmailDomain.length; position++) {
            if (Objects.equals(emailSplitter[1], validEmailDomain[position]))
                return true;
        }
        return false;
    }

    public boolean validatePhoneNumberAndFax(String strPhoneNumberOrFaxNumber) {

        if (strPhoneNumberOrFaxNumber.length() == NUMBER_OF_PHONE_NUMBER_DIGITS && Integer.parseInt(strPhoneNumberOrFaxNumber) % 1 == 0) {
            int ch1 = Integer.parseInt(String.valueOf(strPhoneNumberOrFaxNumber.charAt(0)));
            if (ch1 != STARTING_NUMBER_PORTUGUESE_PHONE)
                return false;

            int ch2 = Integer.parseInt(String.valueOf(strPhoneNumberOrFaxNumber.charAt(1)));
            if (ch2 != FIRST_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != SECOND_SECOND_NUMBER_PORTUGUESE_PHONE &&
                    ch2 != THIRD_SECOND_NUMBER_PORTUGUESE_PHONE && ch2 != FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean validateZipCode(String strZipCode){
        if (strZipCode.matches("^[0-9]{4}(?:-[0-9]{3})?$")){
            return true;
        } else{
            return false;
        }
    }

    public boolean validateSlotDuration(String strSlotDuration){
        if (strSlotDuration.matches("[0-9]{1,3}")){
            return true;
        } else return false;
    }

    public boolean validateVaccinesPerSlot(String strVaccinesPerSlot){
        if (strVaccinesPerSlot.matches("[0-9]{1,3}")){
            return true;
        } else return false;
    }

    public boolean validateVaccinationCenters() {
        return  strName != null && strID != null && strPhoneNumber != null && strEmail != null && strFax != null &&
                strWebsite != null && strOpeningHour != null && strClosingHour != null && strSlotDuration != null && strVaccinesPerSlot != null &&
                strRoad != null && strZipCode != null && strLocal != null && strCenterCoordinatorID != null &&
                !strName.isEmpty() && !strID.isEmpty() && !strPhoneNumber.isEmpty() && !strEmail.isEmpty() && !strFax.isEmpty() &&
                !strWebsite.isEmpty() && !strOpeningHour.isEmpty() && !strClosingHour.isEmpty() && !strSlotDuration.isEmpty() && !strVaccinesPerSlot.isEmpty() &&
                !strRoad.isEmpty() && !strZipCode.isEmpty() && !strLocal.isEmpty() && !strCenterCoordinatorID.isEmpty()  && validatePhoneNumberAndFax(strPhoneNumber)
                && validatePhoneNumberAndFax(strFax) && validateEmail(strEmail) && validateWebsite(strWebsite, strTopLevelDomain,strWorldWideWeb) &&
                validateVaccinationCenterHours(strOpeningHour,strClosingHour) && validateZipCode(strZipCode) && validateSlotDuration(strSlotDuration) &&
                validateVaccinesPerSlot(strVaccinesPerSlot);
    }
}
