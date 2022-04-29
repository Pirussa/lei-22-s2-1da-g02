package app.domain.model;

import app.controller.App;
import jdk.internal.icu.impl.NormalizerImpl;

/**
 *
 * @author João Castro <1210816@isep.ipp.pt>
 */
public class HealthcareCenter extends VaccinationCenter{

    private String ARS;
    private String AGES;
    private int numberOfTypesOfVaccine;

    public HealthcareCenter(int coordinatorID, String name, String address, double phoneNumber, String emailAddress, double faxNumber, String websiteAddress, int openingHour, int closingHour, int slotDuration, int vaccinesPerSlot, String ARS, String AGES, int numberOfTypesOfVaccine)
    {  super(coordinatorID, name, address, phoneNumber, emailAddress, faxNumber, websiteAddress, openingHour, closingHour, slotDuration, vaccinesPerSlot);
        do
        {
            this.ARS=ARS;
            this.AGES=AGES;
            this.numberOfTypesOfVaccine=numberOfTypesOfVaccine;
        }
        while(validateHealthcareCenter()==true && validateNumberOfTypesOfVaccine()==true);
    }

    public boolean validateHealthcareCenter() {
        ;
        return !ARS.isEmpty() && !AGES.isEmpty();
    }

    public boolean validateNumberOfTypesOfVaccine()
    {
        if (numberOfTypesOfVaccine<=1){
            return false;
        } else{
            return true;
        }
    }
}


