package app.ui.gui;

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

public class CheckListVacStatsGUI{

    @FXML
    private ListView<String> statsListView;

    @FXML
    void backToMainScene(ActionEvent event) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/check-export-vac-stats-ui.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }


    public void initialize() {
        //ObservableList<String> list = FXCollections.observableArrayList(controller.getVaccinationStatsListBetweenDates(firstDate,lastDate));
        ObservableList<String> list = FXCollections.observableArrayList("1","2","3");
        statsListView.setItems(list);
    }

}
