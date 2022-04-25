package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccineType {

    /**
     * Represents a Vaccine for a virus.
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String type;


    /**
     * Creates a vaccine type with the following attributes:
     *
     * @param type         The vaccine's type.
     */
    public VaccineType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }

    /**
     * Validates a Vaccine Type.
     *
     * @return true if the type is valid
     */
    public boolean validateVaccineType() {
        return type != null && !type.isEmpty();
    }
}
