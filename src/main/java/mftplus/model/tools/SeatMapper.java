package mftplus.model.tools;

import mftplus.model.entity.Saloon;
import mftplus.model.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper {
    public Seat seatMapper(ResultSet resultSet) throws SQLException {
        return Seat
                .builder()
                .seatId(resultSet.getInt("seat-id"))
                //.saloon(resultSet.getObject("saloon", Saloon.class))
                .seatNumber(resultSet.getString("seat-number"))
                .isAvailable(resultSet.getBoolean("is-available"))
                .build();
    }
}
