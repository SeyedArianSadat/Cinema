package mftplus.model.tools;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionProvider {
    @Getter
    private final static ConnectionProvider provider = new ConnectionProvider();

    private ConnectionProvider() {
    }

    public Connection getOracleConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE",
                "javase",
                "java123"
        );
    }

    public int getNextId(String sequenceName) throws Exception{

        ResultSet resultSet = getOracleConnection().prepareStatement(String.format("select %s.nextval as NEXT_ID from dual", sequenceName)).executeQuery();
        resultSet.next();
        return resultSet.getInt("NEXT_ID");
    }

    public Connection getH2Connection() throws SQLException {
//        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:file:./db/mft_class;AUTO_SERVER=TRUE;MODE=Oracle",
                "sa",
                ""
        );
    }
}
