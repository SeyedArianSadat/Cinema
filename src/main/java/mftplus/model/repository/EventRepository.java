package mftplus.model.repository;

import mftplus.model.entity.Event;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.EventMapper;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements Repository<Event,Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final EventMapper eventMapper=new EventMapper();

    public EventRepository() throws SQLException {
        connection= ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Event event) throws Exception {
        event.setEventId(ConnectionProvider.getProvider().getNextId("event_seq"));
       preparedStatement=connection.prepareStatement(
               "insert into events (event_id,title,description,event_start_date,event_end_date,duration,saloon_id)" +
               " values (event_seq.nextval,?,?,?,?,?,?)"
       );

       preparedStatement.setString(1,event.getTitle());
       preparedStatement.setString(2,event.getDescription());
       preparedStatement.setTimestamp(3,Timestamp.valueOf(event.getEventStartDate()));
       preparedStatement.setTimestamp(4,Timestamp.valueOf(event.getEventEndDate()));
       preparedStatement.setFloat(5,event.getDuration());
       preparedStatement.setNull(6,java.sql.Types.INTEGER);
       preparedStatement.executeUpdate();

    }

    @Override
    public void edit(Event event) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update events set title=?,description=?,event_start_date=?,event_end_date=?,duration=?,saloon_id=? where events_id=?"
        );
        preparedStatement.setString(1,event.getTitle());
        preparedStatement.setString(2,event.getDescription());
        preparedStatement.setTimestamp(3,Timestamp.valueOf(event.getEventStartDate()));
        preparedStatement.setTimestamp(4,Timestamp.valueOf(event.getEventEndDate()));
        preparedStatement.setFloat(5,event.getDuration());
        preparedStatement.setNull(6,java.sql.Types.INTEGER);
        preparedStatement.setInt(7,event.getEventId());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from events where id=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.execute();

    }

    @Override
    public List<Event> findAll() throws Exception {
        List<Event> eventList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from events order by event_start_date");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Event event = eventMapper.eventMapper(resultSet);
            eventList.add(event);
        }
        return eventList;
    }

    @Override
    public Event findById(Integer id) throws Exception {
        Event event = null;

        preparedStatement=connection.prepareStatement("select * from events where id=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
        event = eventMapper.eventMapper(resultSet);
    }
    return event;

    }
    public Event findByTitle(String title) throws Exception {
        Event event = null;
        preparedStatement=connection.prepareStatement("select * from events where title=?");
        preparedStatement.setString(1,title);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            event = eventMapper.eventMapper(resultSet);
        }
        return event;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
