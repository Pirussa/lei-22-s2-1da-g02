package mapper;

import app.domain.model.ScheduledVaccine;
import dto.ScheduledVaccineDto;

public class ScheduledVaccineMapper {

    public ScheduledVaccine dtoToDomain(ScheduledVaccineDto dto) {
        if (dto == null) {
            return null;
        }
        return new ScheduledVaccine(Integer.parseInt(dto.snsNumber), dto.vaccineType, dto.date);
    }

    public ScheduledVaccineDto domainToDto(ScheduledVaccine object) {
        if (object == null) {
            return null;
        }
        return new ScheduledVaccineDto(String.valueOf(object.getSnsNumber()), object.getVaccineType(), object.getDate());
    }

}
