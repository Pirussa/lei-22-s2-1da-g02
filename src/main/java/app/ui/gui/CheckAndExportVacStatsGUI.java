package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class CheckAndExportVacStatsGUI {

    private final CheckAndExportVaccinationStatsController controller = new CheckAndExportVaccinationStatsController();

    private LocalDate firstDate;

    private LocalDate lastDate;


    @FXML
    private DatePicker firstDatePicker;

    @FXML
    private DatePicker lastDatePicker;

    @FXML
    private Label selectLbl;

    @FXML
    private Pane mainMenuPane;

    @FXML
    void getFirstDate(ActionEvent event) {
        firstDate = firstDatePicker.getValue();
    }

    @FXML
    void getLastDate(ActionEvent event) {
        lastDate = lastDatePicker.getValue();
    }

    boolean hasBothDates() {
        return firstDate != null && lastDate != null;
    }


    @FXML
    void notImplementedYet(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Can't do that yet");
        alert.setContentText("This feature is not implemented yet");
        alert.showAndWait();

    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        if (mainMenuPane.isVisible()) {
            //TODO: Fazer o back, maybe só chamando a cena anterior de novo
            toCenterCoordinatorMenu(event);
        } else {
            mainMenuPane.setVisible(true);
        }


    }

    public void checkStatistics(ActionEvent event) {
        if (hasBothDates()) {
            if (controller.checkIfDatesAreValid(firstDate, lastDate)) {
                List<String> vaccinationStatsList = controller.getVaccinationStats();

                //Descobrir como imprimir esta lista em condições
                printVaccinationStats(vaccinationStatsList);

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Invalid dates. Please try again.");
                alert.showAndWait();
            }


            setMainMenuVisibility(false);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select both dates");
            alert.showAndWait();
        }
    }

    private void printVaccinationStats(List<String> vaccinationStatsList) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Vaccination Statistics not ready yet");
        alert.setContentText(vaccinationStatsList.toString());
        alert.showAndWait();
    }
    

    public void exportStatistics(ActionEvent event) {

    }

    private void setMainMenuVisibility(boolean visible) {
        mainMenuPane.setVisible(visible);

    }

    private void toCenterCoordinatorMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
