package mapper;


import app.domain.model.VaccinationCenter;
import dto.VaccinationCenterDto;


public class VaccinationCenterMapper {

    public VaccinationCenterDto domainToDto(VaccinationCenter vaccinationCenter) {
        if (vaccinationCenter == null) {
            return null;
        }
        VaccinationCenterDto dto = new VaccinationCenterDto(vaccinationCenter.getStrID(), vaccinationCenter.getStrName(), vaccinationCenter.getStrPhoneNumber(),vaccinationCenter.getStrEmail(),vaccinationCenter.getStrFax(),vaccinationCenter.getStrWebsite(),vaccinationCenter.getStrOpeningHour(),vaccinationCenter.getStrClosingHour(),vaccinationCenter.getStrSlotDuration(),vaccinationCenter.getStrVaccinesPerSlot(),vaccinationCenter.getStrRoad(),vaccinationCenter.getStrZipCode(),vaccinationCenter.getStrLocal(),vaccinationCenter.getStrCenterCoordinatorID());
        dto.scheduledVaccineList = vaccinationCenter.getScheduledVaccineList();
        dto.slotsPerDay = vaccinationCenter.getSlotsPerDay();
        return dto;
    }


}
