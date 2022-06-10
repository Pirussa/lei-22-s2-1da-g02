package app.ui.gui;

import app.controller.RecordVaccineAdministrationController;
import app.domain.model.SnsUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URI;
import java.net.URL;

public class RecordVaccineAdministrationGUI {

    private final RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    ObservableList<String> vacc = FXCollections.observableArrayList(controller.vaccinationCentersAvailable());

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

    @FXML
    private ComboBox<String> vaccinationCenterList;

    @FXML
    private void initialize() {
            vaccinationCenterList.setItems(vacc);
    }
}
