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

/**
 * The type Read legacy data file gui.
 */
public class ReadLegacyDataFileGUI {

    private DataFromLegacySystemController controller;

    /**
     * Sets controller.
     *
     * @param controller the controller
     */
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


    /**
     * Back.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    public void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Choose file.
     *
     * @param actionEvent the action event
     */
    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        controller.setFile(files.get(0));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("File chosen");
        alert.setHeaderText("You have chosen the file: " + files.get(0).getName() + " successfully");
        alert.setContentText("Wait while the file information is being loaded...");
        alert.showAndWait();
        showOptions();

    }

    public void showOptions() {
        try {
            controller.readFile();
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


    /**
     * Sort by arrival.
     *
     * @param actionEvent the action event
     */
    public void sortByArrival(ActionEvent actionEvent) {
        controller.setOptionArrivalOrDeparture(0);
        setVisibility();
    }

    /**
     * Sort by departure.
     *
     * @param actionEvent the action event
     */
    public void sortByDeparture(ActionEvent actionEvent) {
        controller.setOptionArrivalOrDeparture(1);
        setVisibility();
    }


    /**
     * Ascending.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void ascending(ActionEvent actionEvent) throws IOException {
        controller.setOptionAscendingOrDescending(0);
        controller.toSortedListScene(actionEvent);
    }

    /**
     * Descending.
     *
     * @param actionEvent the action event
     * @throws IOException the io exception
     */
    public void descending(ActionEvent actionEvent) throws IOException {
        controller.setOptionAscendingOrDescending(1);
        controller.toSortedListScene(actionEvent);
    }


}
