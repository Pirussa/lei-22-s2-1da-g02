package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */

public class MassVaccinationCenter extends  VaccinationCenter{

    public MassVaccinationCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                                 String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot, String strRoad,
                                 String strZipCode, String strLocal, String strCenterCoordinatorID) {
        super(intID, strName, strPhoneNumber, strEmail, strFax, strWebsite, strOpeningHour, strClosingHour, strSlotDuration, strVaccinesPerSlot,
                strRoad, strZipCode, strLocal, strCenterCoordinatorID);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
