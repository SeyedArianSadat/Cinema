package mftplus.model.tools;

import mftplus.model.entity.Event;

import java.sql.ResultSet;

public class EventMapper {
    public Event eventMapper(ResultSet resultSet) throws Exception{
        return Event
                .builder()
                .eventId(resultSet.getInt("event_Id"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .eventStartDate(resultSet.getTimestamp("event_start_date").toLocalDateTime())
                .eventEndDate(resultSet.getTimestamp("event_end_date").toLocalDateTime())
                .duration(resultSet.getFloat("duration"))
                .build();
    }
}
