package app.domain.model;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class HealthcareCenter extends VaccinationCenter{

    private String strARS;
    private String strAGES;

    public HealthcareCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite, String strOpeningHour,
                            String strClosingHour, String strSlotDuration, String strVaccinesPerSlot, String strARS, String strAGES, Address addAddress, Employee empCoordinator) {
        super(intID, strName, strPhoneNumber, strEmail, strFax, strWebsite, strOpeningHour, strClosingHour, strSlotDuration, strVaccinesPerSlot, addAddress, empCoordinator);

        if ((strARS==null) || (strAGES==null) || (strARS.isEmpty()) || (strAGES.isEmpty()))
            throw new IllegalArgumentException("Arguments can't be null or empty");

        this.strARS=strARS;
        this.strAGES=strAGES;
    }

    @Override
    public String toString() {
        return super.toString() +
                "strARS='" + strARS + '\'' +
                ", strAGES='" + strAGES + '\'' +
                '}';
    }
}


