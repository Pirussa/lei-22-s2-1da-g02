package app.ui.gui;

import app.controller.DataFromLegacySystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReadLegacyDataFileGUI {

    private DataFromLegacySystemController controller;

    public void setController(DataFromLegacySystemController controller) {
        this.controller = controller;
        controller.setControllerInfo(controller);
    }


    private int optionArrivalOrDeparture;
    private int optionAscendingOrDescending;


    @FXML
    private Label lbSort;

    @FXML
    private Button btArrival;

    @FXML
    private Button btDeparture;

    @FXML
    private Label lbChoice;

    @FXML
    private Button btAscending;

    @FXML
    private Button btDescending;




    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        try {
            controller.readFile(files.get(0).getAbsolutePath());
            lbSort.setVisible(true);
            btArrival.setVisible(true);
            btDeparture.setVisible(true);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No file selected");
            alert.setHeaderText("No file was select");
            alert.setContentText("Please select a file");
            alert.showAndWait();
        }


    }

    private void setVisibility() {
        lbChoice.setVisible(true);
        btAscending.setVisible(true);
        btDescending.setVisible(true);
    }


    public void sortByArrival(ActionEvent actionEvent) {
        controller.setOptionArrivalOrDeparture(0);
        setVisibility();
    }

    public void sortByDeparture(ActionEvent actionEvent) {
        controller.setOptionArrivalOrDeparture(1);
        setVisibility();
    }


    public void ascending(ActionEvent actionEvent) throws IOException {
        controller.setOptionAscendingOrDescending(0);
        controller.toSortedListScene(actionEvent);
    }

    public void descending(ActionEvent actionEvent) throws IOException {
        controller.setOptionAscendingOrDescending(1);
        controller.toSortedListScene(actionEvent);
    }



}
