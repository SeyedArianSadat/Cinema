package mftplus;

import javafx.application.Application;
import javafx.stage.Stage;
import mftplus.model.tools.FormLoader;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FormLoader.getFormLoader().showStage(primaryStage, "/view/LoginView.fxml", "Login");
    }

}

