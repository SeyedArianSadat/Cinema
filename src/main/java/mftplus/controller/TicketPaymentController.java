package mftplus.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class TicketPaymentController {


        @FXML
        private ComboBox<String> eventComboBox;

        @FXML
        private ComboBox<String> saloonComboBox;

        @FXML
        private GridPane seatGrid;

        @FXML
        private Label ticketSummaryLabel;

        @FXML
        private Label paymentSummaryLabel;

        @FXML
        private TextField cardHolderField;

        @FXML
        private TextField cardNumberField;

        @FXML
        private PasswordField cvvField;

        @FXML
        private DatePicker expiryDatePicker;

        @FXML
        private Button payButton;

        private String selectedSeat;

        @FXML
        public void initialize() {

            eventComboBox.getItems().addAll("film a", "film B", "concert X");
            saloonComboBox.getItems().addAll("saloon 1", "saloon 2");


            int rows = 5, cols = 5;
            for (int i = 1; i <= rows; i++) {
                for (int j = 1; j <= cols; j++) {
                    Button seatBtn = new Button(i + "-" + j);
                    seatBtn.setPrefSize(50, 30);

                    seatBtn.setOnAction(e -> {
                        selectedSeat = seatBtn.getText();
                        ticketSummaryLabel.setText(
                                "event: " + eventComboBox.getValue() +
                                        " | saloon " + saloonComboBox.getValue() +
                                        " | seat " + selectedSeat
                        );
                        paymentSummaryLabel.setText("200$ for a seat  " + selectedSeat);
                    });

                    seatGrid.add(seatBtn, j, i);
                }
            }


            payButton.setOnAction(e -> handlePayment());
        }

        private void handlePayment() {
            if (eventComboBox.getValue() == null||  saloonComboBox.getValue() == null || selectedSeat == null) {
                showAlert("error", "please select your ticket first");
                return;
            }
            if (cardHolderField.getText().isEmpty() || cardNumberField.getText().isEmpty()||
            cvvField.getText().isEmpty() || expiryDatePicker.getValue() == null) {
                showAlert("error", "fill all the fields");
                return;
            }


            showAlert("successful", "successful payment" + System.currentTimeMillis());
        }

        private void showAlert(String title, String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }
