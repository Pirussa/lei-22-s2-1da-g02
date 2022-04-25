package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccineType {

    /**
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String type;

    public VaccineType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return type;
    }


    public boolean validateVaccineType() {

        return type != null && type.isEmpty();
    }
}
