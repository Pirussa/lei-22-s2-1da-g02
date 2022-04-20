package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccineType {

    /**
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String type;
<<<<<<< HEAD
    private static List<VaccineType> types = new ArrayList<>();
=======
    private List<String> types = new ArrayList<>();
>>>>>>> 6c82577a240c2e95d8569e935a8c6ff64d349c98

    public VaccineType(String type) {
        this.type = type;
    }

<<<<<<< HEAD
    public static void saveVaccineType(String type) {
        VaccineType vaccineType = new VaccineType(type);
        types.add(vaccineType);
=======
    public boolean addVaccineType(String type) {
        /*
        • Verificar se é um nome válido, meaning se é null ou blanket
        • Verificar se já existe
        • Adicionar
         */

        if (type == null || type.isBlank()) {
            return false;
        }

        for (String s : types)
            if (type.equals(s))
                return false;

        types.add(type);
        return true;
>>>>>>> 6c82577a240c2e95d8569e935a8c6ff64d349c98
    }


}
