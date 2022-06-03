package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;


import java.io.IOException;
import java.util.List;


public class LoginMenuGUI {

    private final AuthController controller = new AuthController();

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private TextField emailTxtField;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField pwdTxtField;

    @FXML
    private Pane tittlePane;

    @FXML
    void doLogin(ActionEvent event) throws IOException {

        if (controller.doLogin(emailTxtField.getText(), pwdTxtField.getText())) {

            List<UserRoleDTO> roles = controller.getUserRoles();
            if ((roles != null) && (!roles.isEmpty())) {
                Alert alertLoginSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertLoginSuccess.setTitle("Login Successful");
                alertLoginSuccess.setContentText("Welcome");

                alertLoginSuccess.show();
                UserRoleDTO role = roles.get(0);
                redirectToRoleUI(role, event);

            } else {
                Alert alertNoRoles = new Alert(Alert.AlertType.ERROR);
                alertNoRoles.setTitle("Login Failed");
                alertNoRoles.setContentText("User has no roles");
                alertNoRoles.showAndWait();
            }


        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Login Unsuccessful");
            alert.setContentText("Invalid Login. Try again");
            alert.showAndWait();

        }

    }

    private void redirectToRoleUI(UserRoleDTO role, ActionEvent event) throws IOException {

        switch (role.getId()) {
            case Constants.ROLE_CENTRE_COORDINATOR:
                toCenterCoordinatorScene(event);
                break;
            case Constants.ROLE_NURSE:
                new NurseGUI();
                break;

        }
    }

    private void toCenterCoordinatorScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/center-coordinator-menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void logout(ActionEvent event) {
        controller.doLogout();
    }
}