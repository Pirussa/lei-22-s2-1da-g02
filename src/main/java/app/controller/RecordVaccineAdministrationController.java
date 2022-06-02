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

    private Vaccine vaccine;

    private SnsUser snsUser;

    public RecordVaccineAdministrationController() {
    }

    // Set Methods, allow the controller to always have these objects
    public void setVaccinationCenter(int index) {
        vaccinationCenter = company.getVaccinationCenters().get(index);
    }

    public void setSnsUser(SnsUserDto snsUserDto) {
        SnsUserMapper snsUserMapper = new SnsUserMapper();
        snsUser = snsUserMapper.SNSUserDtoToDomain(snsUserDto);
    }

    public void setVaccineType(int userIndexInList) {
        vaccineType = getArrivalList().get(userIndexInList).getVaccineType();
    }

    public void setVaccine(int currentAppointment) {
        vaccine = snsUser.administratedVaccines().get(currentAppointment).getVaccine();
    }

    // Functionalities
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

    private int getUserAge() {
        String[] birthdateSplit = snsUser.getStrBirthDate().split("/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    // Get lists of some sort
    private List<Arrival> getArrivalList() {
        return new ArrayList<>(vaccinationCenter.getArrivalsList());
    }

    public List<String> vaccineTypeAvailableVaccines() {
        ArrayList<String> vaccinesAvailable = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType()))
                vaccinesAvailable.add(company.getVaccines().get(index).getName());
        }
        return vaccinesAvailable;
    }

    public List<String> fillListWithUserSnsNumber() {
        ArrayList<String> userSnsNumber = new ArrayList<>();
        for (int index = 0; index < getArrivalList().size(); index++)
            userSnsNumber.add("SNS Number - " + getArrivalList().get(index).getSnsNumber());
        return userSnsNumber;
    }

    // Sns User related
    public SnsUserDto getSnsUserInformation(int selectedUser) {
        SnsUserMapper snsUserMapper = new SnsUserMapper();
        return snsUserMapper.domainToSNSUserDto(company.getSnsUserList().get(snsUserIndexInList(selectedUser)));
    }

    private int snsUserIndexInList(int selectedUser) {
        for (int index = 0; index < company.getSnsUserList().size(); index++) {
            if (getArrivalList().get(selectedUser).getSnsNumber() == company.getSnsUserList().get(index).getSnsUserNumber()) {
                return index;
            }
        }
        return Constants.INVALID_VALUE;
    }

    public int userSuitsAgeGroup(int indexVaccine) {
        int userAge = getUserAge();
        for (int columns = 0; columns < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().size(); rows++) {
                if ((userAge > snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows + 1)) {
                    return columns;
                }
            }
        }
        return -1;
    }

    public int userFirstDoseAgeGroup(int indexVaccine) {
        int userAge = getUserAge();
        ArrayList<Vaccine> vaccines = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType()))
                vaccines.add(company.getVaccines().get(index));
        }
        for (int columns = 0; columns < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().size(); rows++) {
                if ((userAge > vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows + 1) && rows == 0) {
                    return columns;
                } else if ((userAge > vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows)) {
                    return columns;
                }
            }
        }
        return -1;
    }
    public void removeUserFromList (List < Arrival > arrivalsList) {
        arrivalsList.remove(0);
    }
    // Vaccine/Vaccine Type related

    /**
     * Gets user number of doses, for a given vaccine type.
     *
     * @return the number of doses
     */
    public int getUserNumberOfDoses() {
        if (!snsUser.administratedVaccines().isEmpty())
            return findLastDoseOfVaccineType();
        return Constants.FIRST_DOSE;
    }

    public int findLastDoseOfVaccineType() {
        for (int index = snsUser.administratedVaccines().size() - 1; index >= 0; index--) {
            if (vaccineType.equals(snsUser.administratedVaccines().get(index).getVaccine().getVaccineType()))
                return index;
        }
        return Constants.FIRST_DOSE;
    }

    private Double dosageForDose(int numberOfDoses, int indexVaccine) {
        return snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getDosage().get(numberOfDoses);
    }

    public String vaccineAdministrationProcess(int numberOfDoses, int indexVaccine) {
        return "Dosage: " + dosageForDose(numberOfDoses, indexVaccine) + "ml";
    }

    public String vaccineTypeInfo() {
        return "Vaccine Type: " + vaccineType.getCode();
    }

    public String vaccineInfo() {
        return "Vaccine: " + vaccine.getName();
    }
}