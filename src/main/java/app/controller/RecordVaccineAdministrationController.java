package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import dto.SnsUserDto;
import mapper.SnsUserMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public void setUserScheduledVaccineType() {
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

    public List<String> vaccineTypeAvailableVaccines() {
        ArrayList<String> vaccinesAvailable = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType()))
                vaccinesAvailable.add(company.getVaccines().get(index).getName());
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

    public int findLastDoseOfVaccineType() {
        for (int index = snsUser.getTakenVaccines().size() - 1; index >= 0; index--) {
            if (vaccineType.equals(snsUser.getTakenVaccines().get(index).getVaccine().getVaccineType()))
                return index;
        }
        return Constants.FIRST_DOSE;
    }

    public String vaccineAdministrationProcess(int numberOfDoses, int indexVaccine) {
        return "Dosage: " + dosageForDose(numberOfDoses, indexVaccine) + "ml";
    }

    private Double dosageForDose(int numberOfDoses, int indexVaccine) {
        return snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getDosage().get(numberOfDoses);
    }

    public int userSuitsAgeGroup(int indexVaccine) {
        int userAge = getUserAge();
        for (int columns = 0; columns < snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().size() - 1; rows++) {
                if ((userAge > snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < snsUser.getTakenVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows + 1)) {
                    return columns;
                }
            }
        }
        return -1;
    }

    private int getUserAge() {
        String[] birthdateSplit = snsUser.getStrBirthDate().split("/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    private String lotNumberStructure() {
        final String alphabetLetters = "abcdefghijklmnopqrstuvwyxzABCDEFGHIJKLMNOPQRSTUVWYXZ";
        final String numbers = "0123456789";
        final String alphabetNumbers = numbers + alphabetLetters;
        StringBuilder lotNumber = new StringBuilder();
        Random generate = new Random();

        for (int index = 0; index < Constants.LOT_NUMBER_LENGHT; index++) {
            if (index <= 4)
                lotNumber.append(alphabetNumbers.charAt(generate.nextInt(alphabetNumbers.length())));
            else if (index == 5)
                lotNumber.append("-");
            else
                lotNumber.append(numbers.charAt(generate.nextInt(10)));
        }
        return String.valueOf(lotNumber);
    }

    public List<String> fillListWithUserSnsNumber() {
        ArrayList<String> userSnsNumber = new ArrayList<>();
        for (int index = 0; index < getArrivalList().size(); index++)
            userSnsNumber.add("SNS Number: " + getArrivalList().get(index).getSnsNumber());
        return userSnsNumber;
    }
}