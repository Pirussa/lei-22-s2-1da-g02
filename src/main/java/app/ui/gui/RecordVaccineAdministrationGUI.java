package app.ui.gui;

import app.controller.RecordVaccineAdministrationController;
import app.domain.model.SnsUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import javafx.fxml.FXML;

public class RecordVaccineAdministrationGUI {

    private final RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    @FXML

    private VaccinationCenter vaccinationCenter;

    private Vaccine vaccine;

    private VaccineType vaccineType;

    private SnsUser snsUser;

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public SnsUser getSnsUser() {
        return snsUser;
    }


}
