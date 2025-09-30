package mftplus.model.repository;
import mftplus.model.entity.Ticket;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.TicketMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository implements Repository<Ticket, Integer>, AutoCloseable {
    private final Connection  connection;
    private PreparedStatement preparedStatement;
    private final TicketMapper ticketMapper= new TicketMapper();

    public TicketRepository()throws SQLException {
        connection=ConnectionProvider.getProvider().getOracleConnection();
    }
    @Override
    public void save(Ticket ticket) throws Exception {
        ticket.setTicketId(ConnectionProvider.getProvider().getNextId("ticket_seq"));
        preparedStatement=connection.prepareStatement(
                "insert into TICKETS(ticket_id,event_id,customer_id,seat_id,payment_id,ticket_Time) values(TICKET_SEQ.nextval,?,?,?,?,?)"
        );

        preparedStatement.setInt(1,ticket.getEvent().getEventId());
        preparedStatement.setInt(2,ticket.getCustomer().getCustomerId());
        preparedStatement.setInt(3,ticket.getSeat().getSeatId());
        preparedStatement.setInt(4,ticket.getPayment().getPaymentId());
        preparedStatement.setDate(5,Date.valueOf(ticket.getTicketTime().toLocalDate()));
        preparedStatement.execute();
    }
    @Override
    public void edit(Ticket ticket) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update TICKETS set event_id=?,customer_id=?,seat_id=?,payment_id=?,ticket_Time=? where ticket_id=?"
        );
        preparedStatement.setInt(1,ticket.getEvent().getEventId());
        preparedStatement.setInt(2,ticket.getCustomer().getCustomerId());
        preparedStatement.setInt(3,ticket.getSeat().getSeatId());
        preparedStatement.setInt(4,ticket.getPayment().getPaymentId());
        preparedStatement.setDate(5,Date.valueOf(ticket.getTicketTime().toLocalDate()));
        preparedStatement.execute();
    }
    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from TICKETS where ticket_id=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();
    }
    @Override
    public List<Ticket> findAll() throws Exception {
        List<Ticket> tickets = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from TICKETS order by ticket_id ");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Ticket ticket =ticketMapper.ticketMapper(resultSet);
            tickets.add(ticket);
        }
        return tickets;
    }
    @Override
    public Ticket findById(Integer id) throws Exception {
        Ticket ticket =null;

        preparedStatement=connection.prepareStatement("select * from TICKETS where ticket_id=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            ticket = ticketMapper.ticketMapper(resultSet);
        }
        return ticket;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
