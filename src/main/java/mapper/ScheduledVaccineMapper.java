package mapper;

import app.domain.model.ScheduledVaccine;
import dto.ScheduledVaccineDto;

public class ScheduledVaccineMapper {

    public ScheduledVaccine dtoToDomain(ScheduledVaccineDto scheduledVaccineDto) {
        if (scheduledVaccineDto == null) {
            return null;
        }
        return new ScheduledVaccine(scheduledVaccineDto.snsNumber, scheduledVaccineDto.vaccineType, scheduledVaccineDto.date);
    }

    public ScheduledVaccineDto domainToDto(ScheduledVaccine object) {
        if (object == null) {
            return null;
        }
        return new ScheduledVaccineDto(String.valueOf(object.getSnsNumber()), object.getVaccineType(), object.getDate());
    }

}
