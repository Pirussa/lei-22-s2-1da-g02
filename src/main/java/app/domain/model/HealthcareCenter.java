package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class HealthcareCenter extends VaccinationCenter{

    private String ARS;
    private String AGES;
    private int vaccineCode;

    public HealthcareCenter(int coordinatorID, String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String ARS, String AGES, int vaccineCode) {
        super(coordinatorID, name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot);
    }
}


