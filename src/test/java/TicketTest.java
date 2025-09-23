import mftplus.model.entity.*;
import mftplus.model.service.TicketService;

import java.time.LocalDateTime;

public class TicketTest {
    public static void main(String[] args) throws  Exception {
        Event event = Event.builder()
                .eventId(1).build();
        Customer customer = Customer.builder()
                .customerId(1).build();
        Seat seat = Seat.builder()
                .seatId(1).build();
        Payment payment = Payment.builder()
                .paymentId(1).build();


        Ticket ticket =
                Ticket.builder()
                        .ticketId(1)
                        .event(event)
                        .customer(customer)
                        .seat(seat)
                        .payment(payment)
                        .ticketTime(LocalDateTime.of(2002, 12, 12, 12,12))
                        .build();
        TicketService.getService().save(ticket);
    }

}


