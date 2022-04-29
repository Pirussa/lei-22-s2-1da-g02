package app.domain.model;
/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */

public class MassVaccinationCenter extends  VaccinationCenter{

    private int numberOfTypesOfVaccine;

    public MassVaccinationCenter(int coordinatorID, String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, int vaccineCode, int numberOfTypesOfVaccine) {
        super(coordinatorID, name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot);
        do
        {
        this.numberOfTypesOfVaccine=numberOfTypesOfVaccine;
        } while(validateNumberOfTypesOfVaccine()==true);
    }

    public boolean validateNumberOfTypesOfVaccine()
    {
        if (numberOfTypesOfVaccine<=0||numberOfTypesOfVaccine>1){
            return false;
        } else{
            return true;
        }
    }


}
