package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import dto.SnsUserDto;
import mapper.SnsUserMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RecordVaccineAdministrationController {

    private final Company company = App.getInstance().getCompany();

    private VaccinationCenter vaccinationCenter;

    private VaccineType vaccineType;

    private Vaccine vaccine;

    private SnsUser snsUser;

    private LocalDate localDate;

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
        if (!snsUser.administratedVaccines().isEmpty())
            vaccine = snsUser.administratedVaccines().get(currentAppointment).getVaccine();
        else
            vaccine = vaccineTypeAvailableVaccines().get(currentAppointment);
    }

    // Functionalities
    private int getUserAge() {
        String[] birthdateSplit = snsUser.getStrBirthDate().split("/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    // Get lists of some sort
    private List<Arrival> getArrivalList() {
        return new ArrayList<>(vaccinationCenter.getArrivalsList());
    }

    public List<Vaccine> vaccineTypeAvailableVaccines() {
        ArrayList<Vaccine> vaccinesAvailable = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType())) {
                vaccinesAvailable.add(company.getVaccines().get(index));
            }
        }
        return vaccinesAvailable;
    }

    public List<String> vaccineAvailableName() {
        ArrayList<String> vaccinesAvailable = new ArrayList<>();
        for (int index = 0; index < company.getVaccines().size(); index++) {
            if (vaccineType.equals(company.getVaccines().get(index).getVaccineType())) {
                vaccinesAvailable.add(company.getVaccines().get(index).getName());
            }
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

    public int userFirstDoseAgeGroup(int indexVaccine) {
        int userAge = getUserAge();
        ArrayList<Vaccine> vaccines = (ArrayList<Vaccine>) vaccineTypeAvailableVaccines();
        for (int columns = 0; columns < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().size() - 1; rows++) {
                if ((userAge > vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < vaccines.get(indexVaccine).getAdminProcess().getAgeGroups().get(columns).get(rows + 1) && rows == 0) {
                    return columns;
                }
            }
        }
        return -1;
    }

    public int userSuitsAgeGroup(int indexVaccine) {
        int userAge = getUserAge();
        for (int columns = 0; columns < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(0).size(); columns++) {
            for (int rows = 0; rows < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().size() - 1; rows++) {
                if ((userAge > snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows)) && userAge < snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getAgeGroups().get(columns).get(rows + 1)) {
                    return columns;
                }
            }
        }
        return -1;
    }

    public void removeUserFromList(int index) {
        getArrivalList().remove(index);
    }
    // Vaccine/Vaccine Type related

    /**
     * Gets user number of doses, for a given vaccine type.
     *
     * @return the number of doses
     */
    public int getUserNumberOfDoses() {
        if (!snsUser.administratedVaccines().isEmpty())
            return (snsUser.administratedVaccines().get(findLastDoseOfVaccineType()).getDose());
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
        if (numberOfDoses == Constants.FIRST_DOSE)
            return vaccineTypeAvailableVaccines().get(indexVaccine).getAdminProcess().getDosage().get(Constants.FIRST_DOSE + 1);
        else
            return snsUser.administratedVaccines().get(indexVaccine).getVaccine().getAdminProcess().getDosage().get(userSuitsAgeGroup(indexVaccine));
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

    public boolean validateLotNumber(String lotNumber) {
        String alphanumeric = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWYXZ";
        String numeric = "0123456789";
        int counter = 0;
        for (int indexLotNumber = 0; indexLotNumber < lotNumber.length(); indexLotNumber++) {
            for (int index = 0; index < alphanumeric.length(); index++) {
                if (lotNumber.charAt(indexLotNumber) == alphanumeric.charAt(index) && indexLotNumber <= 4)
                    counter++;
                else if (indexLotNumber == 5 && lotNumber.charAt(indexLotNumber) == '-')
                    counter++;
                else if (indexLotNumber >= 6) {
                    for (int indexNumeric = 0; indexNumeric < numeric.length(); indexNumeric++) {
                        if (lotNumber.charAt(indexLotNumber) == numeric.charAt(index))
                            counter++;
                    }
                }
            }
        }
        return counter == Constants.LOT_NUMBER_LENGHT;
    }
}