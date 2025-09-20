package mftplus.model.repository;

import mftplus.model.entity.Seat;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SeatMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeatRepository implements Repository<Seat, Integer> ,AutoCloseable{

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final SeatMapper seatMapper = new SeatMapper();

    public SeatRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Seat seat) throws Exception {
        seat.setSeatId(ConnectionProvider.getProvider().getNextId("seat_seq"));
        preparedStatement = connection.prepareStatement("insert into SEATS(SEAT_ID,SEAT_NUMBER,IS_AVAILABLE)values(?,?,?)");
        preparedStatement.setInt(1, seat.getSeatId());
        preparedStatement.setString(2, seat.getSeatNumber());
        preparedStatement.setBoolean(3, seat.isAvailable());
        preparedStatement.execute();
    }

    @Override
    public void edit(Seat seat) throws Exception {
        preparedStatement = connection.prepareStatement("update SEATS set SEAT_ID=?,SEAT_NUMBER=?,IS_AVAILABLE=? where SEAT_ID=?");
        preparedStatement.setInt(1, seat.getSeatId());
        preparedStatement.setString(2, seat.getSeatNumber());
        preparedStatement.setBoolean(3, seat.isAvailable());
        preparedStatement.execute();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from SEATS where SEAT_ID=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Seat> findAll() throws Exception {
        List<Seat> seatList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from SEATS");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Seat seat = seatMapper.seatMapper(resultSet);
            seatList.add(seat);
        }
        return seatList;
    }

    @Override
    public Seat findById(Integer id) throws Exception {
        Seat seat = null;
        preparedStatement = connection.prepareStatement("select * from SEATS where SEAT_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            seat = seatMapper.seatMapper(resultSet);
        }
        return seat;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
