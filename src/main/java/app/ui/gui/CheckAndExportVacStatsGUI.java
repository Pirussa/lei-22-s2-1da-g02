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
    private Pane tittlePane;

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
    public void back(ActionEvent event)  {
        notImplementedYet(event);

    }

    public void checkStatistics(ActionEvent event) {

    }

    public void exportStatistics(ActionEvent event) {

    }

    private void setVisibility(boolean visible) {
        selectLbl.setVisible(visible);
        firstDatePicker.setVisible(visible);
        lastDatePicker.setVisible(visible);

    }

}
