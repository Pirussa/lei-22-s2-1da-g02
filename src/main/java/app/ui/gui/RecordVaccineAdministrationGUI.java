package app.ui.gui;

import app.controller.RecordVaccineAdministrationController;
import app.domain.shared.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    private javafx.scene.control.TextField vaccineTypeTxt;

    @FXML
    private javafx.scene.control.TextField lotNumberTxt;

    @FXML
    private javafx.scene.control.TextField dosageTxt;


    @FXML
    private CheckBox confirmCenterSelectionCheckBox;

    @FXML
    private CheckBox confirmUserSelectionCheckBox;

    @FXML
    private CheckBox confirmVaccineSelectionCheckBox;

    @FXML
    private Button recordButton;

    @FXML
    private Button cancelButton;


    public void confirmCenterSelection(ActionEvent event) {
        checkBoxVerifyCenter(event);
    }

    private void checkBoxVerifyCenter(ActionEvent event) {
        if (vaccinationCenterList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a center");
            alert.showAndWait();
            confirmCenterSelectionCheckBox.setSelected(false);
        } else {
            // Set selected Center
            setVaccinationCenter();

            // Disable the checkbox and the combo box
            disableComboBoxCenter();
            disableCheckBoxCenter();
        }
    }

    public void confirmUserSelection(ActionEvent event) {
        checkBoxVerifyUser(event);
    }

    private void checkBoxVerifyUser(ActionEvent event) {
        if (userList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select an user");
            alert.showAndWait();
            confirmUserSelectionCheckBox.setSelected(false);
        } else {
            // Set selected User
            setUser();

            // Get User´s Name
            getUserName();

            // Get User´s Age
            userAgeTxt.setText("12");

            // Disable the checkbox and the combo boxes
            disableComboBoxUser();
            disableCheckBoxUser();
        }
    }

    public void confirmVaccineSelection(ActionEvent event) {
        checkBoxVerifyVaccine(event);
    }

    private void checkBoxVerifyVaccine(ActionEvent event) {
        if (vaccineList.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must select a center");
            alert.showAndWait();
            confirmVaccineSelectionCheckBox.setSelected(false);
        } else {
            // Set selected Vaccine or Previous Vaccine
            initializeVaccine();

            // Disable the checkbox and the combo boxes
            disableComboBoxVaccine();
            disableCheckBoxVaccine();

            // Set Vaccine Type
            getVaccineTypeName();

            // Set Dosage
            getDosageQuantity();
        }
    }

    @FXML
    private void recordVaccineAdministrationConfirmed(javafx.event.ActionEvent event) throws IOException {
        if (lotNumberTxt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("You must introduce a lot number");
            alert.showAndWait();
            confirmCenterSelectionCheckBox.setSelected(false);
        } else {
            if (!controller.validateLotNumber(lotNumberTxt.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Lot Number is not valid");
                alert.showAndWait();
                confirmCenterSelectionCheckBox.setSelected(false);
            } else {
                recordVaccineAdministration(event);
                returnToNurseGUI(event);
            }
        }
    }

    @FXML
    private void returnToNurseGUI(javafx.event.ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nurse-menu.fxml"));
        root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initializeCenter() {
        ObservableList<String> vaccinationCenterNameList = FXCollections.observableArrayList(controller.vaccinationCentersAvailable());
        vaccinationCenterList.setItems(vaccinationCenterNameList);
    }

    @FXML
    private void initializeUser() {
        controller.setArrivalList();
        ObservableList<String> userSnsNumberList = FXCollections.observableArrayList(controller.fillListWithUserSnsNumber());
        userList.setItems(userSnsNumberList);
    }

    public void initializeVaccine() {
        if (controller.getUserNumberOfDoses() == Constants.FIRST_DOSE) {
            ObservableList<String> vaccineNameList = FXCollections.observableArrayList(controller.vaccineAvailableName());
            vaccineList.setItems(vaccineNameList);
            controller.setVaccine(vaccineList.getSelectionModel().getSelectedIndex());
        } else {
            int currentAppointment = controller.findLastDoseOfVaccineType();
            controller.setVaccine(currentAppointment);
            vaccineList.setValue(controller.getVaccineName());
            vaccineList.setDisable(true);
        }
    }

    private void setVaccinationCenter() {
        controller.setVaccinationCenter(vaccinationCenterList.getSelectionModel().getSelectedIndex());
    }

    private void setUser() {
        controller.setSnsUser(controller.getSnsUserInformation(userList.getSelectionModel().getSelectedIndex()));
    }

    private void getUserName() {
        if (controller.getSnsUserName() != null)
            userNameTxt.setText(controller.getSnsUserName());
    }

    private void getUserAge() {
        if (controller.getUserAge() != 0)
            userAgeTxt.setText(String.valueOf(controller.getUserAge()));
    }

    private void getVaccineTypeName() {
        controller.setVaccineType(userList.getSelectionModel().getSelectedIndex());
        vaccineTypeTxt.setText(controller.getVaccineTypeName());
    }

    private void getDosageQuantity() {
        if (controller.getUserNumberOfDoses() == Constants.FIRST_DOSE)
            dosageTxt.setText(String.valueOf(controller.dosageForDose(Constants.INVALID_VALUE, vaccineList.getSelectionModel().getSelectedIndex())));
        else
            dosageTxt.setText(String.valueOf(controller.dosageForDose(controller.getUserNumberOfDoses(), controller.findLastDoseOfVaccineType())));
    }

    private void disableComboBoxCenter() {
        vaccinationCenterList.setDisable(true);
    }

    private void disableComboBoxUser() {
        userList.setDisable(true);
    }

    private void disableComboBoxVaccine() {
        vaccineList.setDisable(true);
    }

    private void disableCheckBoxCenter() {
        confirmCenterSelectionCheckBox.setDisable(true);
    }

    private void disableCheckBoxUser() {
        confirmUserSelectionCheckBox.setDisable(true);
    }

    private void disableCheckBoxVaccine() {
        confirmVaccineSelectionCheckBox.setDisable(true);
    }

    private void recordVaccineAdministration(javafx.event.ActionEvent event) {
        controller.registerVaccineInVaccineBulletin();
    }
}
