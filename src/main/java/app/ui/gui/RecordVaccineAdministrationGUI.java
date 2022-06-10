package app.ui.gui;

import app.controller.RecordVaccineAdministrationController;
import app.domain.model.SnsUser;
import app.domain.model.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;
import app.dto.SnsUserDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class RecordVaccineAdministrationGUI {

    private final RecordVaccineAdministrationController controller = new RecordVaccineAdministrationController();

    @FXML
    private ComboBox<String> vaccinationCenterList;

    @FXML
    private ComboBox<String> userList;

    @FXML
    private ComboBox<String> vaccineList;

    @FXML
    private javafx.scene.control.TextField userNameTxt;

    @FXML
    private javafx.scene.control.TextField userAgeTxt;


    @FXML
    private void initializeCenter() {
        ObservableList<String> vaccinationCenterNameList = FXCollections.observableArrayList(controller.vaccinationCentersAvailable());
        vaccinationCenterList.setItems(vaccinationCenterNameList);
    }

    @FXML
    private void initializeUser() {
        ObservableList<String> userSnsNumberList = FXCollections.observableArrayList(controller.fillListWithUserSnsNumber());
        userList.setItems(userSnsNumberList);
    }

    @FXML
    private void initializeVaccine() {
        ObservableList<String> vaccineNameList = FXCollections.observableArrayList(controller.vaccineAvailableName());
        vaccineList.setItems(vaccineNameList);
    }

    public void selectedVaccinationCenter() {
        controller.setVaccinationCenter(vaccinationCenterList.getSelectionModel().getSelectedIndex());
    }

    public void selectedSnsUser() {
        controller.setSnsUser(controller.getSnsUserInformation(userList.getSelectionModel().getSelectedIndex()));
    }

    public void selectedVaccine() {
        controller.setVaccine(vaccineList.getSelectionModel().getSelectedIndex());
    }

    public void setUserNameTxt() {
        if (controller.getSnsUserName() != null)
            userNameTxt.setText(controller.getSnsUserName());
    }

    public void setUserAgeTxt() {
        if (controller.getSnsUserName() != null)
            userAgeTxt.setText(String.valueOf(controller.getUserAge()));
    }

    private void recordVaccineAdministrationConfirmed(javafx.event.ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nurse-menu.fxml"));
        root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void recordVaccineAdministration(javafx.event.ActionEvent event) {
        //if (controller.validateLotNumber()) {
        switch (controller.allInfoVaccinationRecord()) {
            case 0:

                break;
            case 1:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("You must select a Vaccination Center");
                alert.showAndWait();
                break;
            case 2:
                Alert alert2 = new Alert(Alert.AlertType.ERROR);
                alert2.setTitle("Error");
                alert2.setContentText("You must select an User");
                alert2.showAndWait();
                break;
            case 3:
                Alert alert3 = new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("Error");
                alert3.setContentText("You must select a Vaccine");
                alert3.showAndWait();
                break;
            case 4:
                Alert alert4 = new Alert(Alert.AlertType.ERROR);
                alert4.setTitle("Error");
                alert4.setContentText("You must introduce a Lot Number");
                alert4.showAndWait();
                break;
        }


       /* } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select both dates");
            alert.showAndWait();
        }*/
    }
}
