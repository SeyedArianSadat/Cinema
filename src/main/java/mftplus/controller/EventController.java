package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Log4j
public class EventController implements Initializable {

    @FXML
    private ComboBox<String> eventComboBox,artistComboBox,seatComboBox,saloonComboBox;

    @FXML
    private DatePicker startDatePicker, endDatePicker;

    @FXML
    private Button getTicketButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        eventComboBox.getItems().addAll("Movie Night", "Concert", "Play");
        artistComboBox.getItems().addAll("Artist A", "Artist B");
        saloonComboBox.getItems().addAll("Saloon 1", "Saloon 2");
        seatComboBox.getItems().addAll("A1", "A2", "B1", "B2");
        getTicketButton.setOnAction(e -> goToTicket());

        try {
            resetForm();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"error loading data " + e.getMessage(), ButtonType.OK);
            alert.show();
        }

    }



    @FXML
    private void goToTicket() {

        if (eventComboBox.getValue() == null ||
                artistComboBox.getValue() == null ||
                saloonComboBox.getValue() == null||
                seatComboBox.getValue() == null||
                startDatePicker.getValue() == null ||
                endDatePicker.getValue() == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill in all fields!", ButtonType.OK);
            alert.show();
            return;
        }

        if (startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "End date cannot be before start date.", ButtonType.OK);
            alert.show();
            return;
        }

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TicketPaymentView.fxml"));
            Scene scene = new Scene(loader.load());

            TicketPaymentController ticketController = loader.getController();

            ticketController.setEventDetails( eventComboBox.getValue(),
                    seatComboBox.getValue(),
                    saloonComboBox.getValue(),
                    startDatePicker.getValue(),
                    endDatePicker.getValue()
            );
            Stage stage = (Stage) eventComboBox.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Ticket Details");







        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void resetForm(){
        eventComboBox.getSelectionModel().clearSelection();
        artistComboBox.getSelectionModel().clearSelection();
        seatComboBox.getSelectionModel().clearSelection();
        saloonComboBox.getSelectionModel().clearSelection();
        startDatePicker.setValue(LocalDate.now());
        endDatePicker.setValue(LocalDate.now());
    }
}