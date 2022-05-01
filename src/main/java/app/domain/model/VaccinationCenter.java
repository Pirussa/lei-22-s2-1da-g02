package app.domain.model;

import app.controller.CreateVaccinationCenterController;


public class VaccinationCenter{

    Employee thisCoordinator;
    Address thisAddress;
    Company thisCompany;

    CreateVaccinationCenterController controller = new CreateVaccinationCenterController();

    private final int MAXCHAROFPHONENUMBER = 9;

    private int intID;
    private String strName;
    private String strPhoneNumber;
    private String strEmail;
    private String strFax;
    private String strWebsite;
    private String strOpeningHour;
    private String strClosingHour;
    private String strSlotDuration;
    private String strVaccinesPerSlot;
    private Address addAddress;
    private Employee empCoordinator;

    public VaccinationCenter(int intID, String strName, String strPhoneNumber, String strEmail, String strFax, String strWebsite,
                             String strOpeningHour, String strClosingHour, String strSlotDuration, String strVaccinesPerSlot,
                             Address addAddress, Employee empCoordinator) {

        if (((strName==null) || (strPhoneNumber==null) || (strEmail==null) || (strFax==null) || (strWebsite==null) || (strOpeningHour==null) ||
                (strClosingHour==null) || (strSlotDuration==null)  || (strVaccinesPerSlot==null) || (addAddress==null) || (empCoordinator==null) ||
                 (strName.isEmpty()) || (strPhoneNumber.isEmpty() || (strEmail.isEmpty()) ||
                (strFax.isEmpty()) || (strWebsite.isEmpty()) || (strOpeningHour.isEmpty()) || (strClosingHour.isEmpty()) || (strSlotDuration.isEmpty()) ||
                (strVaccinesPerSlot.isEmpty()))))
        throw new IllegalArgumentException("Arguments can't be null or empty");

        if (intID <= 0) throw new IllegalArgumentException("ID needs to be !=0 and a positive number");

        if (strPhoneNumber.length()>=MAXCHAROFPHONENUMBER) throw new IllegalArgumentException("Phone Number can't have more than 9 characters");

        this.intID = intID;
        this.strName = strName;
        this.strPhoneNumber = strPhoneNumber;
        this.strEmail = strEmail;
        this.strFax = strFax;
        this.strWebsite = strWebsite;
        this.strOpeningHour = strOpeningHour;
        this.strClosingHour = strClosingHour;
        this.strSlotDuration = strSlotDuration;
        this.strVaccinesPerSlot = strVaccinesPerSlot;
        this.addAddress = addAddress;
        this.empCoordinator = empCoordinator;

    }

    @Override
    public String toString() {
        return "VaccinationCenter{" +
                ", strName='" + strName + '\'' +
                ", strPhoneNumber='" + strPhoneNumber + '\'' +
                ", strEmail='" + strEmail + '\'' +
                ", strFax='" + strFax + '\'' +
                ", strWebsite='" + strWebsite + '\'' +
                ", strOpeningHour='" + strOpeningHour + '\'' +
                ", strClosingHour='" + strClosingHour + '\'' +
                ", strSlotDuration='" + strSlotDuration + '\'' +
                ", strVaccinesPerSlot='" + strVaccinesPerSlot + '\'' +
                ", addAddress=" + addAddress +
                ", empCoordinator=" + empCoordinator +
                '}';
    }
}
