package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class VaccinationCenter{

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
    private int coordinatorID;

    public VaccinationCenter(int coordinatorID, String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot)
        {
            this.coordinatorID = coordinatorID;
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
        }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                "coordinator ID='" + coordinatorID + '\'' +
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
                '}';
    }

    public boolean validateStrings(){
        ;
        return !name.isEmpty() && !address.isEmpty() && !emailAddress.isEmpty() && !websiteAddress.isEmpty();
    }

    public boolean validateIntegersAndDoubles(){
        ;
        return !(coordinatorID==0) && !(phoneNumber==0) && !(faxNumber==0) && !(openingHour==0) && !(closingHour==0) && !(slotDuration==0) && !(vaccinesPerSlot==0);
    }

    public String getName() {
        return name;
    }

    public String getAddress(){return address;}

    public String getEmailAddress(){return emailAddress;}

    public String getWebsiteAddress(){return websiteAddress;}

}
