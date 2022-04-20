package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccineType {

    /**
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String type;
    private static List<VaccineType> types = new ArrayList<>();

    public VaccineType(String type) {
        this.type = type;
    }

    public static void saveVaccineType(String type) {
        VaccineType vaccineType = new VaccineType(type);
        types.add(vaccineType);
    }


}
