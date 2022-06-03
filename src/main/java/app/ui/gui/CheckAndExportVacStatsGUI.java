package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CheckAndExportVacStatsGUI {

    private final CheckAndExportVaccinationStatsController controller = new CheckAndExportVaccinationStatsController();

    private LocalDate firstDate;

    private LocalDate lastDate;

    @FXML
    private ListView<String> statsListView = new ListView<>();

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
    public void back(ActionEvent event) throws IOException {
        toCenterCoordinatorMenu(event);

    }

    @FXML
    public void backToMainScene(ActionEvent event) throws IOException {
        toMainScene(event);
    }

    public void checkStatistics(ActionEvent event) throws IOException {
        if (hasBothDates()) {
            switch (controller.checkIfDatesAreValid(firstDate, lastDate)) {
                case 0:

                     List<String> vaccinationStatsPerDay = controller.getVaccinationStats();

                    toCheckVaccinationStatsScene(event);
                    break;
                case 1:
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("You must select both dates");
                    alert.showAndWait();
                    break;
                case 2:
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Error");
                    alert2.setContentText("The first date must be before the last date");
                    alert2.showAndWait();
                    break;
                case 3:
                    Alert alert3 = new Alert(Alert.AlertType.ERROR);
                    alert3.setTitle("Error");
                    alert3.setContentText("The first date must be after the 1st of January 2021");
                    alert3.showAndWait();
                    break;
                case 4:
                    Alert alert4 = new Alert(Alert.AlertType.ERROR);
                    alert4.setTitle("Error");
                    alert4.setContentText("There are no statistics for dates after today");
                    alert4.showAndWait();
                    break;
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please select both dates");
            alert.showAndWait();
        }
    }

    public void exportStatistics(ActionEvent event) {

    }

    private void toCheckVaccinationStatsScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/check-vac-stats.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void toCenterCoordinatorMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void toMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/check-export-vac-stats-ui.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


}
