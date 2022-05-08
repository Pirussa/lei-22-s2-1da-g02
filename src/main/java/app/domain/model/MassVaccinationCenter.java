package app.domain.model;
/**
 * Creates a Mass Vaccination Center
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */

public class MassVaccinationCenter extends  VaccinationCenter{

    private String strVaccineType;

    /**
     *
     * @param strVaccineType       The mass vaccination center's administered Vaccine Types.
     */
    public MassVaccinationCenter(String strID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                                 String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot, String strRoad,
                                 String strZipCode, String strLocal, String strCenterCoordinatorID,String strVaccineType) {
        super(strID, strName, strPhoneNumber, strEmail, strFax, strWebsite, strOpeningHour, strClosingHour, strSlotDuration, strVaccinesPerSlot,
                strRoad, strZipCode, strLocal, strCenterCoordinatorID);

        if ((strVaccineType==null)||(strVaccineType.isEmpty()))
            throw new IllegalArgumentException("Arguments can't be null or empty.");
        this.strVaccineType=strVaccineType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Vaccine Types administered in the Vaccination Center: " + strVaccineType;
    }

    /**
     * Validates the Mass Vaccination Center outside the constructor, so it can be called in order to do the tests.
     *
     * @return a true or a false.
     */
    public boolean validateMassVaccinationCenters() {
        return super.validateVaccinationCenters() && strVaccineType != null  && !strVaccineType.isEmpty();
    }
}
