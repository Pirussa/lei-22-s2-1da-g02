package app.domain.model;

import java.util.Random;

public class VaccineType {

    /**
     * Represents a Vaccine for a virus.
     *
     * @author Pedro Monteiro <1211076@isep.ipp.pt>
     */

    private String description;
    private String code;
    private String vaccineTechnology;


    /**
     * Creates a vaccine type with the following attributes:
     *
     * @param description         The vaccine's type.
     */
    public VaccineType(String description) {
        this.description = description;
        code = generateCode();
    }


    @Override
    public String toString() {
        return description;
    }

    /**
     * Validates a Vaccine Type.
     *
     * @return true if the type is valid
     */
    public boolean validateVaccineType() {
        return description != null && !description.isEmpty();
    }

    // code is composed by 3 letters and 2 numbers
    public String generateCode() {
        Random generate = new Random();
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "Y", "X", "W", "Z"};
        String code =  "";

        for (int i = 0; i < 3; i++)
            code += (letters[generate.nextInt(26)]);

        for (int i = 0; i < 2; i++)
            code += String.valueOf(generate.nextInt(9));


        return code;
    }
}
