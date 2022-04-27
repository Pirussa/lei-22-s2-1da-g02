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

   //private final String NAMEBYOMISSION = "N/A";
   //private final String ADDRESSBYOMISSION = "N/A";
   //private final double PHONENUMBERBYOMISSION = 0;
   //private final String EMAILADDRESSBYOMISSION = "N/A";
   //private final double FAXNUMBERBYOMISSION = 0;
   //private final String WEBSITEADDRESSBYOMISSION = "N/A";
   //private final int OPENINGHOURBYOMISSION = 0;
   //private final int CLOSINGHOURBYOMISSION = 0;
   //private final int SLOTDURATIONBYOMISSION = 0;
   //private final int VACCINESPERSLOTBYOMISSION = 0;
   //private final String COORDINATORBYOMISSION = "N/A";

    Company x;

    public VaccinationCenter(int coordinatorID, String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot) {
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

    public boolean validateVaccinationCenter(){
       return false;
    }
}
