package app.ui.gui;

import app.controller.CheckAndExportVaccinationStatsController;
import app.controller.DataFromLegacySystemController;
import com.sun.glass.ui.CommonDialogs;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class ReadLegacyDataFileGUI {

    private DataFromLegacySystemController controller;

    public void setController(DataFromLegacySystemController controller) {
        this.controller = controller;
    }


    @FXML
    private Label lbSort;

    @FXML
    private Button btArrival;

    @FXML
    private Button btDeparture;




    public void back(ActionEvent event) throws IOException {
        Parent root;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        root = loader.load();

        ReadLegacyDataFileGUI mainScene = loader.getController();
        mainScene.setController(controller);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void chooseFile(ActionEvent actionEvent) {
        lbSort.setVisible(true);
        btArrival.setVisible(true);
        btDeparture.setVisible(true);

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        List<File> files = fileChooser.showOpenMultipleDialog(null);
        for (File file : files)
            System.out.println(file.getAbsolutePath());
    }

    public void sortByArrival(ActionEvent actionEvent) {
    }

    public void sortByDeparture(ActionEvent actionEvent) {
    }
}
