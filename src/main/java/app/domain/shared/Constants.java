package app.domain.shared;

import java.util.ArrayList;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";

    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    public static final String ROLE_NURSE = "NURSE";

    public static final String ROLE_CENTRE_COORDINATOR = "CENTRE_COORDINATOR";
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    public ArrayList<Constants> roles = new ArrayList<>();
}
