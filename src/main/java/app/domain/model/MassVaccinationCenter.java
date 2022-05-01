package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */

public class MassVaccinationCenter extends  VaccinationCenter{

    public MassVaccinationCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite, String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot, Address addAddress, Employee empCoordinator) {
        super(intID, strName, strPhoneNumber, strEmail, strFax, strWebsite, strOpeningHour, strClosingHour, strSlotDuration, strVaccinesPerSlot, addAddress, empCoordinator);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
