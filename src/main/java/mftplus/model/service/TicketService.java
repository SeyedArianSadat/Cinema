package mftplus.model.service;

import lombok.Getter;
import mftplus.model.entity.Ticket;
import mftplus.model.repository.TicketRepository;
import java.util.List;

public class TicketService implements Service<Ticket ,Integer> {
    @Getter
    private static TicketService service = new TicketService();

    private TicketService() {
    }

    @Override
    public void save(Ticket ticket) throws Exception {
        try (TicketRepository ticketRepository = new TicketRepository()) {
            ticketRepository.save(ticket);
        }
    }

    @Override
    public void edit(Ticket ticket) throws Exception {
        try (TicketRepository ticketRepository = new TicketRepository()) {
            ticketRepository.edit(ticket);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try (TicketRepository ticketRepository = new TicketRepository()) {
            ticketRepository.delete(id);
        }
    }

    @Override
    public List<Ticket> findAll() throws Exception {
        try (TicketRepository ticketRepository = new TicketRepository()) {
            return ticketRepository.findAll();
        }
    }

    @Override
    public Ticket findById(Integer id) throws Exception {
        try (TicketRepository ticketRepository = new TicketRepository()) {
           return ticketRepository.findById(id);
        }
    }

}