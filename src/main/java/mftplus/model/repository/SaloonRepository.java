package mftplus.model.repository;

import mftplus.model.entity.Saloon;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.SaloonMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaloonRepository implements Repository<Saloon, Integer> , AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final SaloonMapper saloonMapper =  new SaloonMapper();

    public SaloonRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }


    @Override
    public void save(Saloon saloon) throws Exception {

        saloon.setSaloonId(ConnectionProvider.getProvider().getNextId("saloon_seq"));

      preparedStatement = connection.prepareStatement("insert into SALOONS(SALOON_ID, NAME, ADDRESS, CAPACITY, MANAGER_ID) values (?, ?, ?, ?, ?)");

      preparedStatement.setInt(1, saloon.getSaloonId());
      preparedStatement.setString(2, saloon.getName());
      preparedStatement.setString(3, saloon.getAddress());
      preparedStatement.setInt(4, saloon.getCapacity());
      preparedStatement.setInt(5, saloon.getManager().getUserId());
      preparedStatement.execute();
    }

    @Override
    public void edit(Saloon saloon) throws Exception {

        preparedStatement = connection.prepareStatement("update SALOONS set NAME=?, ADDRESS=?, CAPACITY=?, MANAGER_ID=? where SALOON_ID=?");

        preparedStatement.setString(1, saloon.getName());
        preparedStatement.setString(2, saloon.getAddress());
        preparedStatement.setInt(3, saloon.getCapacity());
        preparedStatement.setInt(4,saloon.getManager().getUserId());
        preparedStatement.setInt(5,saloon.getSaloonId());
        preparedStatement.execute();


    }

    @Override
    public void delete(Integer id) throws Exception {
       preparedStatement = connection.prepareStatement("delete from SALOONS where SALOON_ID=?");

       preparedStatement.setInt(1, id);
       preparedStatement.execute();
    }

    @Override
    public List<Saloon> findAll() throws Exception {
        List<Saloon> saloonList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from SALOONS");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Saloon saloon = saloonMapper.saloonMapper(resultSet);
            saloonList.add(saloon);
        }
        return saloonList;
    }

    @Override
    public Saloon findById(Integer id) throws Exception {
        Saloon saloon = null;
        preparedStatement = connection.prepareStatement("select * from SALOONS where SALOON_ID=?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            saloon = saloonMapper.saloonMapper(resultSet);
        }
        return saloon;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
