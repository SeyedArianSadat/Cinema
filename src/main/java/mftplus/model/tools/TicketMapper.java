package mftplus.model.tools;

import mftplus.model.entity.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketMapper {
    public Ticket TicketMapper(ResultSet resultSet) throws SQLException {
        return Ticket
                .builder()
                .ticketId(resultSet.getInt("Ticket_id"))
                .event(Event.builder().eventId(resultSet.getInt("event_id")).build())
                .customer(Customer.builder().customerId(resultSet.getInt("customer_id")).build())
                .seat(Seat.builder().seatId(resultSet.getInt("Seat_id")).build())
                .payment(Payment.builder().paymentId(resultSet.getInt("payment_id")).build())
                .ticketTime(resultSet.getTimestamp("ticketTime").toLocalDateTime())
                .build();
    }
}







