package app.domain.model;

import app.ui.console.VaccineAndAdminProcessDto;

public class Vaccine {


    /**
     * Represents a Vaccine for a virus.
     *
     * @author Gustavo Jorge <1211061@isep.ipp.pt>
     */

    private String name;

    private int id;

    private String brand;

    private AdministrationProcess adminProcess;

    private VaccineType vt;

    /**
     * Creates a vaccine with the following attributes:
     *
     * @param name         The vaccine's name.
     * @param id           The vaccine's ID.
     * @param brand        The brand that created the vaccine.
     * @param adminProcess The Administration Process related to the vaccine.
     * @param vt           The Vaccine Type of the vaccine (e.g.: Covid-19, Flu) - The disease related to the vaccine.
     */

    public Vaccine(String name, int id, String brand, AdministrationProcess adminProcess, VaccineType vt) {
        this.name = name;
        this.id = id;
        this.brand = brand;
        this.adminProcess = adminProcess;
        this.vt = vt;
    }

    /**
     * Validates a Vaccine.
     *
     * @return true if the Vaccine is validated
     */
    public boolean validateVaccine() {
        if (name.isEmpty() || brand.isEmpty() || id == 0) return true;
        return true;
    }


}
