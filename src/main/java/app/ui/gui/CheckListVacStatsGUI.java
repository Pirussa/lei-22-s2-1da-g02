package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import app.domain.model.VaccinationCenterStats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private TableView<VaccinationCenterStats> tableView;

    @FXML
    private TableColumn<VaccinationCenterStats, String> dateCollumn;

    @FXML
    private TableColumn<VaccinationCenterStats, String> totalVaccinatedCollumn;

    private LocalDate firstDate;
    private LocalDate lastDate;

    /**
     * Sets first date.
     *
     * @param firstDate the first date
     */
    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    /**
     * Sets last date.
     *
     * @param lastDate the last date
     */
    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    /**
     * Sets the list view with the Vaccination Statistics.
     *
     * @param firstDate the first date
     * @param lastDate  the last date
     */
    public void setStatsListView(LocalDate firstDate, LocalDate lastDate ) {
        String[][] stats = new String[controller.getVaccinationStatsListBetweenDates(firstDate, lastDate).size()][2];
        ObservableList<VaccinationCenterStats> statsList = FXCollections.observableArrayList();

        for (int position = 0; position < stats.length; position++) {
            stats[position] = controller.getVaccinationStatsListBetweenDates(firstDate, lastDate).get(position).split(";");
            statsList.add(new VaccinationCenterStats(stats[position][0], stats[position][1]));
        }

        dateCollumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        totalVaccinatedCollumn.setCellValueFactory(new PropertyValueFactory<>("totalVaccinated"));
        tableView.setItems(statsList);
    }

    /**
     * Back to main scene.
     *
     * @param event the event
     * @throws IOException the io exception
     */
    @FXML
    void backToMainScene(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/check-export-vac-stats-ui.fxml"));
        root = loader.load();

        CheckAndExportVacStatsGUI mainScene = loader.getController();
        mainScene.setFirstDate(firstDate);
        mainScene.setLastDate(lastDate);
        mainScene.setFirstDatePicker(firstDate);
        mainScene.setLastDatePicker(lastDate);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





}
