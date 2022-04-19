package app.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VaccineType {

    /**
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String type;
    private List<String> types = new ArrayList<>();

    public VaccineType(String type) {
        this.type = type;
    }

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
    }


}
