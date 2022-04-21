package app.domain.model;

import app.ui.console.VaccineAndAdminProcessDto;
import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class Company {

    private String designation;
    private AuthFacade authFacade;

    public Company(String designation)
    {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }


    public boolean specifyNewVaccineType(String type) {
        if (type != null && !type.isEmpty())
            return false;

        VaccineType vaccineType = new VaccineType(type);
        return true;
    }

}
