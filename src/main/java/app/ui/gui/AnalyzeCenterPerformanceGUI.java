package app.ui.gui;

import app.controller.AnalyzeCenterPerformanceController;
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
import java.util.List;

public class AnalyzeCenterPerformanceGUI {

    private final AnalyzeCenterPerformanceController controller = new AnalyzeCenterPerformanceController();

    @FXML
    private Button analyzeBtn;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextArea timeIntervalTxt;

    @FXML
    void analyzeCenterPerformance(ActionEvent event) {
        if (checkIfTimeIntervalIsValid()){
            controller.setTimeInterval(Integer.parseInt(timeIntervalTxt.getText()));
            List<Object> listWithInformationToShow = controller.analyzeCenterPerformanceForDay();


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid time interval");
            alert.setContentText("Please enter a valid time interval. It must be a positive integer.");
            alert.showAndWait();
        }

    }

    /**
     * Gets the first date.
     *
     * @param event the event
     */
    @FXML
    void setSelectedDate(ActionEvent event) {
        controller.setSelectedDate(datePicker.getValue());
        timeIntervalTxt.setVisible(true);
        analyzeBtn.setVisible(true);
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


    private void toCenterCoordinatorMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private boolean checkIfTimeIntervalIsValid() {
        String timeInterval = timeIntervalTxt.getText();
        if (timeInterval.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(timeInterval);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

}
