package mftplus.model.tools;

import mftplus.model.entity.Customer;
import java.sql.ResultSet;

public class CustomerMapper {
    public Customer customerMapper(ResultSet resultSet) throws Exception {
        return Customer
                .builder()
                .customerId(resultSet.getInt("customer_id"))
                .name(resultSet.getString("name"))
                .family(resultSet.getString("family"))
                .phoneNumber(resultSet.getString("phone_number"))
                .age(resultSet.getInt("age"))
                .build();
    }
}
