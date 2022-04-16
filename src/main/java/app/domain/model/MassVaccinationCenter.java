package app.domain.model;

public class MassVaccinationCenter extends  VaccinationCenter{
    public MassVaccinationCenter(String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String coordinator) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot, coordinator);
    }

    public MassVaccinationCenter() {
        super();
    }
}
