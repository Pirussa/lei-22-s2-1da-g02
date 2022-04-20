package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class MassVaccinationCenter extends  VaccinationCenter{
    public MassVaccinationCenter(String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String coordinator) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot, coordinator);
    }

    public MassVaccinationCenter() {
        super();
    }
}
