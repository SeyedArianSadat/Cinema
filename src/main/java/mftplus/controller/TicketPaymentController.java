package mftplus.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import mftplus.model.entity.*;
import mftplus.model.entity.enums.PaymentType;
import mftplus.model.repository.PaymentRepository;
import mftplus.model.repository.TicketRepository;
import mftplus.model.service.CustomerService;
import mftplus.model.service.EventService;
import mftplus.model.service.SeatService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class TicketPaymentController {

    @FXML
    private TextField ticketIdText, eventIdText, customerIdText, seatText, saloonText,
            amountText, startDateText, endDateText;

    @FXML
    private ComboBox<String> paymentTypeComboBox;

    @FXML
    private Button saveButton, editButton, deleteButton;

    @FXML
    private TableView<Ticket> ticketTable;

    @FXML
    private TableColumn<Ticket, Integer> ticketIdColumn;

    @FXML
    private TableColumn<Ticket, String> eventIdColumn, customerIdColumn, seatColumn,
            amountColumn, paymentTypeColumn;

    private final TicketRepository ticketRepository;
    private final PaymentRepository paymentRepository;

    {
        try {
            ticketRepository = new TicketRepository();
            paymentRepository = new PaymentRepository();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
        paymentTypeComboBox.getItems().addAll("Card", "Cash", "Online");

        saveButton.setOnAction(e -> saveTicket());
        editButton.setOnAction(e -> editTicket());
        deleteButton.setOnAction(e -> deleteTicket());

        ticketTable.setOnMouseReleased(e -> selectFromTable());
        ticketTable.setOnKeyReleased(e -> selectFromTable());

        try {
            resetForm();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error Loading Data", ButtonType.OK).show();
            log.error("Error Loading Data: " + ex.getMessage());
        }
    }


    public void setEventDetails(String eventName,String seatNumber, String saloon,
                                java.time.LocalDate startDate, java.time.LocalDate endDate) {
        eventIdText.setText(eventName);
        seatText.setText(seatNumber);
        saloonText.setText(saloon);
        startDateText.setText(startDate.toString());
        endDateText.setText(endDate.toString());
    }

    private void saveTicket() {
        try {
            Payment payment = new Payment();
            payment.setAmount(Double.parseDouble(amountText.getText()));
            payment.setPaymentType(PaymentType.valueOf(paymentTypeComboBox.getValue()));
            payment.setPaymentTime(LocalDateTime.now());

            paymentRepository.save(payment);

            Ticket ticket = Ticket.builder()
                    .event(EventService.getService().findByTitle(eventIdText.getText()))
                    .customer(CustomerService.getService().findById(Integer.parseInt(customerIdText.getText())))
                    .seat(SeatService.getService().findBySeatNumber(seatText.getText()))
                    .payment(payment)
                    .ticketTime(LocalDateTime.now())
                    .build();

            ticketRepository.save(ticket);

            new Alert(Alert.AlertType.INFORMATION, "Ticket Saved Successfully\nID: " + ticket.getTicketId(), ButtonType.OK).show();
            log.info("Ticket Saved Successfully: " + ticket.getTicketId());
            resetForm();

        } catch (Exception ex) {
            log.error("Ticket Save Failed: " + ex.getMessage());
            new Alert(Alert.AlertType.ERROR, "Ticket Save Failed: " + ex.getMessage(), ButtonType.OK).show();
        }
    }
    private void editTicket() {
        try {
            int id = Integer.parseInt(ticketIdText.getText());

            Payment payment = new Payment();
            payment.setAmount(Double.parseDouble(amountText.getText()));
            payment.setPaymentType(PaymentType.valueOf(paymentTypeComboBox.getValue()));
            payment.setPaymentTime(LocalDateTime.now());
            paymentRepository.save(payment);

            Ticket ticket = Ticket.builder()
                    .ticketId(id)
                    .event(EventService.getService().findByTitle(eventIdText.getText()))
                    .customer(CustomerService.getService().findById(Integer.parseInt(customerIdText.getText())))
                    .seat(SeatService.getService().findBySeatNumber(seatText.getText()))
                    .payment(payment)
                    .ticketTime(LocalDateTime.now())
                    .build();

            ticketRepository.edit(ticket);

            new Alert(Alert.AlertType.INFORMATION, "Ticket Edited Successfully\nID: " + ticket.getTicketId(), ButtonType.OK).show();
            log.info("Ticket Edited Successfully: " + ticket.getTicketId());
            resetForm();

        } catch (Exception ex) {
            log.error("Ticket Edit Failed: " + ex.getMessage());
            new Alert(Alert.AlertType.ERROR, "Ticket Edit Failed: " + ex.getMessage(), ButtonType.OK).show();
        }
    }

    private void deleteTicket() {
        try {
            int id = Integer.parseInt(ticketIdText.getText());
            ticketRepository.delete(id);

            new Alert(Alert.AlertType.INFORMATION, "Ticket Deleted Successfully\nID: " + id, ButtonType.OK).show();
            log.info("Ticket Deleted Successfully: " + id);
            resetForm();

        } catch (Exception ex) {
            log.error("Ticket Delete Failed: " + ex.getMessage());
            new Alert(Alert.AlertType.ERROR, "Ticket Delete Failed: " + ex.getMessage(), ButtonType.OK).show();
        }
    }

    private void selectFromTable() {
        try {
            Ticket ticket = ticketTable.getSelectionModel().getSelectedItem();
            if (ticket == null) return;

            ticketIdText.setText(String.valueOf(ticket.getTicketId()));
            eventIdText.setText((ticket.getEvent().getTitle()));
            customerIdText.setText(String.valueOf(ticket.getCustomer().getCustomerId()));
            seatText.setText(ticket.getSeat().getSeatNumber());
            saloonText.setText(ticket.getSeat().getSaloon().getName());
            amountText.setText(String.valueOf(ticket.getPayment().getAmount()));
            paymentTypeComboBox.setValue(ticket.getPayment().getPaymentType().name());

        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error Selecting Ticket", ButtonType.OK).show();
            log.error("Error Selecting Ticket: " + ex.getMessage());
        }
    }

    private void resetForm() throws Exception {
        ticketIdText.clear();
        eventIdText.clear();
        customerIdText.clear();
        seatText.clear();
        saloonText.clear();
        amountText.clear();
        startDateText.clear();
        endDateText.clear();
        paymentTypeComboBox.getSelectionModel().clearSelection();

        showDataOnTable(ticketRepository.findAll());
    }

    private void showDataOnTable(List<Ticket> tickets) {
        ObservableList<Ticket> ticketObservableList = FXCollections.observableArrayList(tickets);

        ticketIdColumn.setCellValueFactory(new PropertyValueFactory<>("ticketId"));

        eventIdColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEvent().getTitle()));

        customerIdColumn.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getCustomer().getCustomerId())));

        seatColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getSeat().getSeatNumber()));
        amountColumn.setCellValueFactory(data ->
                new SimpleStringProperty(String.valueOf(data.getValue().getPayment().getAmount())));

        paymentTypeColumn.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getPayment().getPaymentType().name()));

        ticketTable.setItems(ticketObservableList);
    }
}