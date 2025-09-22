package mftplus.model.tools;

import lombok.Getter;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;


public class ConnectionProvider {
    @Getter
    private static final ConnectionProvider provider = new ConnectionProvider();


    private static final BasicDataSource dataSource = new BasicDataSource();
    private ConnectionProvider() {
        dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        dataSource.setUsername("javase");
        dataSource.setPassword("javase123");

        dataSource.setMaxIdle(4);
        dataSource.setMaxIdle(8);
        dataSource.setMaxTotal(16);

        dataSource.setMaxWait(Duration.ofSeconds(10));

    }
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public int getNextId(String sequenceName) throws SQLException {
        String sql = "SELECT " + sequenceName + ".nextval AS NEXT_ID FROM dual" ;
        try (Connection connection= getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
                 if (resultSet.next()) {
                     return resultSet.getInt("next_id");
                 }else  throw   new SQLException("Sequence " + sequenceName + " did not return a value");
        }


    }


    }

