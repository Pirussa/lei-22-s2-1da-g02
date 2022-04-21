package app.domain.model;

import app.ui.console.VaccineAndAdminProcessDto;

public class Vaccine {


    /**
     *
     * @author Gustavo Jorge <1211061@isep.ipp.pt>
     */

    private String name;

    private AdministrationProcess adminProcess;

    public Vaccine(VaccineAndAdminProcessDto dto) {
        this.name = name;
        this.adminProcess = adminProcess;
    }



}
