package app.domain.shared;

import java.util.ArrayList;

import app.domain.model.Company;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {

    private Company company;
    public static final String ROLE_ADMIN = "ADMINISTRATOR";

    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";

    public static final String ROLE_NURSE = "NURSE";

    public static final String ROLE_CENTRE_COORDINATOR = "CENTRE_COORDINATOR";
    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    public ArrayList<String> getRolesList() {
        company.getRolesList().add(Constants.ROLE_RECEPTIONIST);
        company.getRolesList().add(Constants.ROLE_NURSE);
        company.getRolesList().add(Constants.ROLE_CENTRE_COORDINATOR);
        return company.getRolesList();
    }

}
