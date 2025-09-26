package mftplus.controller;

import mftplus.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mftplus.model.entity.User;
import mftplus.model.repository.UserRepository;

import java.sql.SQLException;

public class LoginController {
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
    private void handleLogin() {
        String username = UsernameField.getText();
        String password = PasswordField.getText();
        User user =
                null;
        try {
            user = userRepository.login(username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (user == null) {
            MainApp.setLoggedInUser(user);
      //      MainApp.showHomePage();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Wrong username or password");
            alert.showAndWait();
        }
    }
}
