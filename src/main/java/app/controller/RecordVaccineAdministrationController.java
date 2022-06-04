package app.controller;

import app.domain.model.*;
import app.domain.shared.Constants;
import app.domain.shared.GenericClass;
import dto.SnsUserDto;
import dto.VaccineBulletinDto;
import mapper.SnsUserMapper;
import mapper.VaccineBulletinMapper;

import java.io.NotSerializableException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

/**
 * @author Guilherme Sousa <1211073@isep.ipp.pt>
 */

public class RecordVaccineAdministrationController {

    GenericClass<VaccineBulletin> genericClass = new GenericClass<>();
    private final Company company = App.getInstance().getCompany();

    private VaccinationCenter vaccinationCenter;

    private VaccineType vaccineType;

    private Vaccine vaccine;

    private SnsUser snsUser;

    private LocalDateTime localDateTime;

    private ArrayList<Arrival> arrivalsVaccination;

    private String lotnumber;

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
        vaccineType = arrivalsVaccination.get(userIndexInList).getVaccineType();
    }

    public void setVaccine(int currentAppointment) {
        if (!snsUser.administratedVaccines().isEmpty())
            vaccine = snsUser.administratedVaccines().get(currentAppointment).getVaccine();
        else
            vaccine = vaccineTypeAvailableVaccines().get(currentAppointment);
    }

    public void setLotnumber(String setLotnumber) {
        lotnumber = setLotnumber;
    }

    public void setLocalDateTime() {
        localDateTime = LocalDateTime.now();
    }

    // Functionalities
    private int getUserAge() {
        String[] birthdateSplit = snsUser.getStrBirthDate().split("/");
        LocalDate birthdate = LocalDate.of(Integer.parseInt(birthdateSplit[2]), Integer.parseInt(birthdateSplit[1]), Integer.parseInt(birthdateSplit[0]));
        return Period.between(birthdate, LocalDate.now()).getYears();
    }

    // Get lists of some sort
    public void setArrivalList() {
        arrivalsVaccination = new ArrayList<>(vaccinationCenter.getArrivalsList());
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
        for (Arrival arrival : arrivalsVaccination) userSnsNumber.add("SNS Number - " + arrival.getSnsNumber());
        return userSnsNumber;
    }

    // Sns User related
    public SnsUserDto getSnsUserInformation(int selectedUser) {
        SnsUserMapper snsUserMapper = new SnsUserMapper();
        return snsUserMapper.domainToSNSUserDto(company.getSnsUserList().get(snsUserIndexInList(selectedUser)));
    }

    private int snsUserIndexInList(int selectedUser) {
        for (int index = 0; index < company.getSnsUserList().size(); index++) {
            if (arrivalsVaccination.get(selectedUser).getSnsNumber() == company.getSnsUserList().get(index).getSnsUserNumber()) {
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
        vaccinationCenter.getArrivalsList().remove(index);
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
        String numbers = "(.*\\d.*)";
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        int counter = 0;
        if (lotNumber.length() == Constants.LOT_NUMBER_LENGTH) {
            for (int index = 0; index < lotNumber.length(); index++) {
                if (index <= 4 && (lotNumber.matches(numbers) || lotNumber.matches(upperCaseChars) || lotNumber.matches(lowerCaseChars)))
                    counter++;
                else if (index == 5 && lotNumber.charAt(index) == '-')
                    counter++;
                else if (index >= 6 && (lotNumber.matches(numbers)))
                    counter++;
            }
        }
        return counter == Constants.LOT_NUMBER_LENGHT;
    }

    private VaccineBulletinDto snsUserAddVaccineBulletin() {
        VaccineBulletinDto vaccineBulletinDto = new VaccineBulletinDto();
        vaccineBulletinDto.vaccine = vaccine;
        vaccineBulletinDto.doseNumber = getUserNumberOfDoses() + Constants.ADD_DOSE;
        vaccineBulletinDto.dateTimeOfLastDose = localDateTime;
        vaccineBulletinDto.lotNumber = lotnumber;
        return vaccineBulletinDto;
    }

    public void registerVaccineInVaccineBulletin() {
        VaccineBulletinMapper vaccineBulletinMapper = new VaccineBulletinMapper();
        vaccinationCenter.getVaccineBulletinsAllUsers().add(vaccineBulletinMapper.VaccineBulletinDtoToDomain(snsUserAddVaccineBulletin()));
        snsUser.registerVaccine(vaccineBulletinMapper.VaccineBulletinDtoToDomain(snsUserAddVaccineBulletin()));
    }

    public boolean checkIfArrivalsListEmpty() {
        return !vaccinationCenter.getArrivalsList().isEmpty();
    }

    /**
     * Exports the list of Vaccine Bulletins to a binary file.
     * @throws NotSerializableException
     */
    public void exportDataToFile() throws NotSerializableException {
        genericClass.binaryFileWrite(Constants.FILE_PATH_VACCINE_BULLETIN, snsUser.administratedVaccines());
    }
}