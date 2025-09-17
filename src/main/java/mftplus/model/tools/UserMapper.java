package mftplus.model.tools;

import mftplus.model.entity.Customer;
import mftplus.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User userMapper(ResultSet resultSet) throws SQLException {
        return User
                .builder()
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .role(resultSet.getString("role"))
                .customer(Customer.builder().customerId(resultSet.getInt("customer_id")).build())
                .build();
    }

}
