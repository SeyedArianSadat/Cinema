package mftplus.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import mftplus.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mftplus.model.entity.User;
import mftplus.model.repository.UserRepository;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private TextField UsernameField;

    @FXML
    private PasswordField PasswordField;

    private final UserRepository userRepository;

    {
        try {
            userRepository = new UserRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void onLoginClicked(ActionEvent event) {
        String username = UsernameField.getText();
        String password = PasswordField.getText();
        try {
            User user = userRepository.login(username, password);
            if (user.equals("admin") && user.getPassword().equals("1234")) {
                System.out.println("logged in");

                MainApp.setLoggedInUser(user);
                //      MainApp.showHomePage();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Database error" + e.getMessage());
            alert.showAndWait();
        }

    }
}