package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import dto.SnsUserDto;
import mapper.SnsUserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RecordVaccineAdministrationController {

    private final Company company = App.getInstance().getCompany();

    private VaccinationCenter vaccinationCenter;

    private VaccineType vaccineType;

    private SnsUser snsUser;

    public RecordVaccineAdministrationController() {
    }

    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index);
    }

    public void setSnsUser(SnsUserDto snsUserDto) {
        SnsUserMapper snsUserMapper = new SnsUserMapper();
        snsUser = snsUserMapper.SNSUserDtoToDomain(snsUserDto);
    }

    private void setUserScheduledVaccineType() {
        vaccineType = getArrivalList().get(0).getVaccineType();
    }


    private List<Arrival> getArrivalList() {
        return new ArrayList<>(vaccinationCenter.getArrivalsList());
    }

    private int snsUserIndexInList() {
        for (int index = 0; index < company.getSnsUserList().size(); index++) {
            if (getArrivalList().get(0).getSnsNumber() == company.getSnsUserList().get(index).getSnsUserNumber()) {
                return index;
            }
        }
        return Constants.INVALID_VALUE;
    }

    public SnsUserDto getSnsUserInformation() {
        SnsUserMapper snsUserMapper = new SnsUserMapper();
        return snsUserMapper.domainToSNSUserDto(company.getSnsUserList().get(snsUserIndexInList()));
    }

    public List<Vaccine> vaccineTypeAvailableVaccines() {
        ArrayList<Vaccine> vaccinesAvailable = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType()))
                vaccinesAvailable.add(company.getVaccines().get(index));
        }
        return vaccinesAvailable;
    }

    public void removeUserFromList(List<Arrival> arrivalsList) {
        arrivalsList.remove(0);
    }


    /**
     * Gets user number of doses, for a given vaccine type.
     *
     * @return the number of doses
     */
    public int getUserNumberOfDoses() {
        if (!snsUser.getTakenVaccines().isEmpty())
            return findLastDoseOfVaccineType();
        return Constants.FIRST_DOSE;
    }

    private int findLastDoseOfVaccineType() {
        for (int index = snsUser.getTakenVaccines().size(); index > 0; index--) {
            if (vaccineType.equals(snsUser.getTakenVaccines().get(index).getVaccine().getVaccineType()))
                return index;
        }
        return Constants.FIRST_DOSE;
    }

   /* public StringBuilder vaccineAdministrationProcess(int numberOfDoses, int indexVaccine) {
        StringBuilder vaccineAdministrationProcess = new StringBuilder();
        vaccineAdministrationProcess.append("Dosage: ").append(dosageForDose(numberOfDoses, indexVaccine));
    }*/

    private Double dosageForDose(int numberOfDoses, int indexVaccine) {
        return snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getDosage().get(numberOfDoses - 1);
    }
}