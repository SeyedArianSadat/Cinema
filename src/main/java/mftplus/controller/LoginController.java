package mftplus.controller;


import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import mftplus.model.service.UserService;
import mftplus.model.tools.FormLoader;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
        @FXML
        private TextField usernameTxt;

        @FXML
        private PasswordField passwordTxt;

        @FXML
        private Button loginBtn;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            loginBtn.setOnAction(e -> {
                try{
                    AppState.LoggedInUser = UserService.getService().findByUsernameAndPassword(usernameTxt.getText(), passwordTxt.getText());

                    Stage stage = new Stage();
                    FormLoader.getFormLoader().showStage(stage, "/view/EventView.fxml", "Events page");
                    loginBtn.getScene().getWindow().hide();
                }catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, "invalid username/password", ButtonType.OK);
                    alert.show();
                }
            });
        }
    }