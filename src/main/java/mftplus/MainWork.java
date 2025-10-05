package mftplus;

import mftplus.model.entity.*;
import mftplus.model.entity.enums.PaymentType;
import mftplus.model.repository.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainWork {
    public static void main(String[] args) {
        try {
            ArtistRepository artistRepository = new ArtistRepository();
            SaloonRepository saloonRepository = new SaloonRepository();
            SeatRepository seatRepository = new SeatRepository();
            UserRepository userRepository = new UserRepository();
            EventRepository eventRepository = new EventRepository();
            CustomerRepository customerRepository = new CustomerRepository();
            PaymentRepository paymentRepository = new PaymentRepository();
            TicketRepository ticketRepository = new TicketRepository();


            Artist artist1 = new Artist();
            artist1.setArtistId(1);
            artist1.setName("Arian");
            artist1.setFamily("Sadat");
            artist1.setCategory("Singer");
            artist1.setGenre("Pop");

            artistRepository.save(artist1);

            List<Artist> artistList = new ArrayList<>();
            artistList.add(artist1);


            Saloon saloon = new Saloon();
            saloon.setSaloonId(3);
            saloon.setName("Grand Hall");
            saloon.setAddress("Downtown");
            saloon.setCapacity(100);
            User manager = new User();
            manager.setUserId(1);
            saloon.setManager(manager);



            saloonRepository.save(saloon);


            List<Seat> seatList = new ArrayList<>();
            for (int i = 1; i <= 5; i++) {
                Seat seat = new Seat();
                seat.setSeatId(i);
                seat.setSeatNumber("A" + i);
                seat.setAvailable(true);
                seat.setSaloon(saloon);
                seatRepository.save(seat);
                seatList.add(seat);
            }
            saloon.setSeatList(seatList);




            Event event = new Event();
            event.setEventId(1);
            event.setTitle("Arian Sadat Live Concert");
            event.setDescription("A night of music and magic");
            event.setEventStartDate(LocalDateTime.of(2025, 12, 25, 20, 0));
            event.setEventEndDate(LocalDateTime.of(2025, 12, 25, 23, 0));
            event.setDuration(3);
            event.setArtistList(artistList);
            event.setSaloon(saloon);
            eventRepository.save(event);


            Customer customer = new Customer();
            customer.setCustomerId(10);
            customer.setName("Hanieh");
            customer.setFamily("Hosseinzadeh");
            customer.setPhoneNumber("0912000000");
            customer.setAge(25);
            customerRepository.save(customer);

            manager.setUserId(1);
            manager.setUsername("admin");
            manager.setPassword("1234");
            manager.setRole("Manager");


         //   userRepository.save(manager);

         //   saloonRepository.edit(saloon);



            Payment payment = new Payment();
            payment.setPaymentId(100);
            payment.setAmount(250.00);
            payment.setPaymentType(PaymentType.Card);
            payment.setPaymentTime(LocalDateTime.now());
            paymentRepository.save(payment);


            Ticket ticket = new Ticket();
            ticket.setTicketId(500);
            ticket.setEvent(event);
            ticket.setCustomer(customer);
            ticket.setSeat(seatList.get(0));
            ticket.setPayment(payment);
            ticket.setTicketTime(LocalDateTime.now());
            ticketRepository.save(ticket);


            System.out.println("=== Ticket Information ===");
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Event: " + event.getTitle());
            System.out.println("Customer: " + customer.getName() + " " + customer.getFamily());
            System.out.println("Seat: " + ticket.getSeat().getSeatNumber());
            System.out.println("Payment: " + payment.getAmount() + " (" + payment.getPaymentType() + ")");
            System.out.println("Event Starts at: " + event.getEventStartDate());
            System.out.println("Saloon: " + saloon.getName() + " (" + saloon.getAddress() + ")");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
