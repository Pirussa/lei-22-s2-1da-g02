package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * The type Check and export vac stats gui.
 */
public class CheckAndExportVacStatsGUI {

    private final CheckAndExportVaccinationStatsController controller = new CheckAndExportVaccinationStatsController();

    private LocalDate firstDate;

    private LocalDate lastDate;

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    @FXML
    private DatePicker firstDatePicker;

    @FXML
    private DatePicker lastDatePicker;

    public void setFirstDatePicker(LocalDate firstDate) {
        this.firstDatePicker.setValue(firstDate);
    }

    public void setLastDatePicker(LocalDate lastDate) {
        this.lastDatePicker.setValue(lastDate);
    }

    @FXML
    private Button okBtn;
    @FXML
    private TextArea askFileNameTxt;

    /**
     * Gets the first date.
     *
     * @param event the event
     */
    @FXML
    void getFirstDate(ActionEvent event) {
        firstDate = firstDatePicker.getValue();
    }

    /**
     * Gets the last date.
     *
     * @param event the event
     */
    @FXML
    void getLastDate(ActionEvent event) {
        lastDate = lastDatePicker.getValue();
    }

    /**
     * Checks if both dates were selected.
     *
     * @return the boolean
     */
    boolean hasBothDates() {
        return firstDate != null && lastDate != null;
    }

    /**
     * Goes to the previous scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        toCenterCoordinatorMenu(event);
    }

    /**
     * Check statistics.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void checkStatistics(ActionEvent event) throws IOException {
        if (hasBothDates()) {
            switch (controller.checkIfDatesAreValid(firstDate, lastDate)) {
                case 0:
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

    /**
     * Export statistics.
     *
     * @param event the event
     */
    public void exportStatistics(ActionEvent event) {


        String fileName = askFileNameTxt.getText();
        if (!fileName.isEmpty()) {
            if (controller.exportVaccinationStats(fileName, firstDate, lastDate)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("The available statistics from " + firstDate + " to " + lastDate + " were exported successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("The statistics were not exported successfully");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setContentText("Please enter a file name");
            alert.showAndWait();
        }
    }

    /**
     * Export statistics option.
     *
     * @param event the event
     */
    public void exportStatisticsOption(ActionEvent event) {
        if (hasBothDates()) {
            switch (controller.checkIfDatesAreValid(firstDate, lastDate)) {
                case 0:
                    askFileNameTxt.setVisible(true);
                    okBtn.setVisible(true);
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

    private void toCheckVaccinationStatsScene(ActionEvent event) throws IOException {
        Parent root;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/check-vac-stats.fxml"));
        root = loader.load();

        CheckListVacStatsGUI nextSceneUi = loader.getController();

        nextSceneUi.setFirstDate(firstDate);
        nextSceneUi.setLastDate(lastDate);
        nextSceneUi.setStatsListView(firstDate, lastDate);

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


}
