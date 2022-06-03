package app.ui.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CenterCoordinatorGUI {

    private final LoginMenuGUI loginMenuGUI = new LoginMenuGUI();

    @FXML
    private Pane tittlePane;

    @FXML
    void notImplementedYet(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Can't do that yet");
        alert.setContentText("This feature is not implemented yet");
        alert.showAndWait();

    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        loginMenuGUI.logout(event);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout Successful");
        alert.setContentText("You have been logged out.");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login-menu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void checkAndExportStats(ActionEvent event) throws IOException {
        showNotCompletedAlert();
        toCheckAndExportStatsGUI(event);

    }

    private void toCheckAndExportStatsGUI(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/check-export-vac-stats-ui.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void showNotCompletedAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Not completed yet");
        alert.setContentText("This feature is not completed yet");
        alert.showAndWait();
    }
}
