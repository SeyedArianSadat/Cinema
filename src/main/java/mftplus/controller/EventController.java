package mftplus.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import mftplus.model.entity.*;
import mftplus.model.service.*;


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
        try {
        for (Event event : EventService.getService().findAll()) {
            eventComboBox.getItems().add(event.getTitle());
        };

        for (Artist artist : ArtistService.getService().findAll()) {
            artistComboBox.getItems().add(artist.getName());
        }

        for (Saloon saloon : SaloonService.getService().findAll()) {
            saloonComboBox.getItems().add(saloon.getName());
        }
        for(Seat seat : SeatService.getService().findAll()) {
            seatComboBox.getItems().add(seat.getSeatNumber());
        }
        getTicketButton.setOnAction(e -> goToTicket());


            resetForm();
        } catch (Exception e) {
            e.printStackTrace();
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