package app.domain.shared;


import app.domain.model.Company;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {

    private Company company;
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_SNS_USER = "SNS_USER";
    public static final String ROLE_CENTRE_COORDINATOR = "CENTRE_COORDINATOR";
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    public static final String PARAMS_TIME = "Time";

    public static final String ID_FIRST_EMPLOYEE = "Company.Designation";

    public static final String SUGGESTED_VACCINE_TYPE_ONGOING_OUTBREAK = "COVID";

    public static final String PATH_SMS_MESSAGE = "SMS.txt";
    public static final int PASSWORD_LENGTH = 7;

    public static final int ID_LENGTH = 5;

    public static final int NUMBER_OF_PHONE_NUMBER_DIGITS = 9;

    public static final int STARTING_NUMBER_PORTUGUESE_PHONE = 9;

    public static final int FIRST_SECOND_NUMBER_PORTUGUESE_PHONE = 1;

    public static final int SECOND_SECOND_NUMBER_PORTUGUESE_PHONE = 2;

    public static final int THIRD_SECOND_NUMBER_PORTUGUESE_PHONE = 3;

    public static final int FOURTH_SECOND_NUMBER_PORTUGUESE_PHONE = 6;

    public static final int NUMBER_OF_CITIZEN_CARD_DIGITS = 12;

    public static final int FIRST_SECOND_DIGIT_CC = 10;

    public static final int NUMBER_OF_DAYS_FOR_VACCINATION_SCHEDULE = 365;

    public static final int OPENING_CLOSING_HOURS = 2;

    public static final int FIRST_USER_WAITING_ROOM = 0;

    public static final int FIRST_DOSE = -1;

    public static final int INVALID_VALUE = -1;

    public static final int NEW_EMPLOYEE = 1;

    public static final int SEPTEMBER = 10;

    public static final int LOT_NUMBER_LENGHT = 8;

    public static final int FIT_AGE_GROUP = -1;

    public static final int DOSAGE_FIRST_DOSE = 35;

    /**
     * Names for the binary files.
     */
    public static final String FILE_PATH_VACCINE_TYPES = "vaccinetypes";

    public static final String FILE_PATH_SNS_USERS = "snsusers";

    public static final String FILE_PATH_EMPLOYEES = "employees";

    public static final String FILE_PATH_VACCINATION_CENTERS = "vaccinationcenters";

    public static final String FILE_PATH_ARRIVALS = "arrivals";

    public static final String FILE_PATH_VACCINES = "vaccine";

    public static final String FILE_PATH_APPOINTMENTS = "appointments";

    public static final String FILE_PATH_UPDATEDLEGACY = "updatedlegacy";

    public static final int VACCINE_ADMINISTRATION = 0;

    public static final int VACCINATION = 1;

    public static final String[] OPTIONS = {"Yes", "No"};

}
