package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

/**
 * UI for the Check and Export Vaccination Stats
 */
public class CheckListVacStatsGUI {

    private final CheckAndExportVaccinationStatsController controller = new CheckAndExportVaccinationStatsController();

    @FXML
    private ListView<String> statsListView;

    /**
     * Sets the list view with the Vaccination Statistics.
     *
     * @param firstDate the first date
     * @param lastDate  the last date
     */
    public void setStatsListView(LocalDate firstDate, LocalDate lastDate ) {
        ObservableList<String> list = FXCollections.observableArrayList(controller.getVaccinationStatsListBetweenDates(firstDate, lastDate));
        statsListView.setItems(list);

    }

    /**
     * Back to main scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void backToMainScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/check-export-vac-stats-ui.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
