package mftplus.model.tools;

import mftplus.model.entity.Saloon;
import mftplus.model.entity.Seat;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SeatMapper {
    public Seat seatMapper(ResultSet resultSet) throws SQLException {
        return Seat
                .builder()
                .seatId(resultSet.getInt("seat_id"))
                .saloon(Saloon.builder().saloonId(resultSet.getInt("saloon_id")).build())
                .seatNumber(resultSet.getString("seat_number"))
                .isAvailable(resultSet.getBoolean("is_available"))
                .build();
    }
}
