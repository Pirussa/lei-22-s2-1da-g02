package app.ui.gui;

import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;


import java.util.List;


public class MainMenuGUI {

    private final AuthController controller = new AuthController();


    @FXML
    private TextField emailTxtField;

    @FXML
    private Button loginBtn;

    @FXML
    private TextField pwdTxtField;

    @FXML
    private Pane tittlePane;

    @FXML
    void doLogin(ActionEvent event) {

        if (controller.doLogin(emailTxtField.getText(), pwdTxtField.getText())) {

            List<UserRoleDTO> roles = controller.getUserRoles();
            if ((roles != null) && (!roles.isEmpty())) {
                Alert alertLoginSuccess = new Alert(Alert.AlertType.INFORMATION);
                alertLoginSuccess.setTitle("Login Successful");
                alertLoginSuccess.show();
                UserRoleDTO role = roles.get(0);
                redirectToRoleUI(role);

            } else {
                Alert alertNoRoles = new Alert(Alert.AlertType.ERROR);
                alertNoRoles.setTitle("Login Failed");
                alertNoRoles.setContentText("User has no roles");
                alertNoRoles.showAndWait();
            }


        }else{

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid Login. Try again.");
            alert.showAndWait();

        }

    }


private void redirectToRoleUI(UserRoleDTO role){

    switch (role.getId()) {
        case Constants.ROLE_CENTRE_COORDINATOR:
            new CenterCoordinatorGUI();
            break;
        case Constants.ROLE_NURSE:
            new NurseGUI();
            break;

    }
}


}
