package mftplus.model.tools;

import mftplus.model.entity.Event;

import java.sql.ResultSet;

public class EventMapper {
    public Event eventMapper(ResultSet resultSet) throws Exception{
        return Event
                .builder()
                .eventId(resultSet.getInt("eventId"))
                .title(resultSet.getString("title"))
                .description(resultSet.getString("description"))
                .eventStartTime(resultSet.getTimestamp("evenStartTime").toLocalDateTime())
                .eventEndTime(resultSet.getTimestamp("evenEndTime").toLocalDateTime())
                .duration(resultSet.getFloat("duration"))
                .build();
    }
}
