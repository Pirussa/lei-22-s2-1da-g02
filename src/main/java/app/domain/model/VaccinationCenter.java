package app.domain.model;

public class VaccinationCenter {

    private String name;
    private String address;
    private double phoneNumber;
    private String emailAddress;
    private double faxNumber;
    private String websiteAddress;
    private int openingHour;
    private int closingHour;
    private int slotDuration;
    private int vaccinesPerSlot;
    private String coordinator;

    private final String NAMEBYOMISSION = "N/A";
    private final String ADDRESSBYOMISSION = "N/A";
    private final double PHONENUMBERBYOMISSION = 0;
    private final String EMAILADDRESSBYOMISSION = "N/A";
    private final double FAXNUMBERBYOMISSION = 0;
    private final String WEBSITEADDRESSBYOMISSION = "N/A";
    private final int OPENINGHOURBYOMISSION = 0;
    private final int CLOSINGHOURBYOMISSION = 0;
    private final int SLOTDURATIONBYOMISSION = 0;
    private final int VACCINESPERSLOTBYOMISSION = 0;
    private final String COORDINATORBYOMISSION = "N/A";

    public VaccinationCenter(String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String coordinator) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.websiteAddress = websiteAddress;
        this.openingHour = openingHour;
        this.closingHour = closingHour;
        this.slotDuration = slotDuration;
        this.vaccinesPerSlot = vaccinesPerSlot;
        this.coordinator = coordinator;
    }

    public VaccinationCenter(){
        name=NAMEBYOMISSION;
        address=ADDRESSBYOMISSION;
        phoneNumber=PHONENUMBERBYOMISSION;
        emailAddress=EMAILADDRESSBYOMISSION;
        faxNumber=FAXNUMBERBYOMISSION;
        websiteAddress=WEBSITEADDRESSBYOMISSION;
        openingHour=OPENINGHOURBYOMISSION;
        closingHour=CLOSINGHOURBYOMISSION;
        slotDuration=SLOTDURATIONBYOMISSION;
        vaccinesPerSlot=VACCINESPERSLOTBYOMISSION;
        coordinator=COORDINATORBYOMISSION;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public double getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(double faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public int getOpeningHour() {
        return openingHour;
    }

    public void setOpeningHour(int openingHour) {
        this.openingHour = openingHour;
    }

    public int getClosingHour() {
        return closingHour;
    }

    public void setClosingHour(int closingHour) {
        this.closingHour = closingHour;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

    public void setSlotDuration(int slotDuration) {
        this.slotDuration = slotDuration;
    }

    public int getVaccinesPerSlot() {
        return vaccinesPerSlot;
    }

    public void setVaccinesPerSlot(int vaccinesPerSlot) {
        this.vaccinesPerSlot = vaccinesPerSlot;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", emailAddress='" + emailAddress + '\'' +
                ", faxNumber=" + faxNumber +
                ", websiteAddress='" + websiteAddress + '\'' +
                ", openingHour=" + openingHour +
                ", closingHour=" + closingHour +
                ", slotDuration=" + slotDuration +
                ", vaccinesPerSlot=" + vaccinesPerSlot +
                ", coordinator='" + coordinator + '\'' +
                '}';
    }
}
