package mftplus.controller;


import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
                    FormLoader.getFormLoader().showStage(stage, "/view/TicketPaymentView.fxml", "payment Information");
                    loginBtn.getScene().getWindow().hide();
                }catch (Exception ex){
                    Alert alert = new Alert(Alert.AlertType.ERROR, ex.getMessage());
                    alert.show();
                }
            });
        }
    }