package app.domain.model;

public class HealthcareCenter extends VaccinationCenter{
    public HealthcareCenter(String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String coordinator) {
        super(name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot, coordinator);
    }

    public HealthcareCenter() {
        super();
    }
}


