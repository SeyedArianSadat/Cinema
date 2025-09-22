package mftplus.model.repository;

import mftplus.model.entity.Customer;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.CustomerMapper;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CustomerRepository implements Repository<Customer,Integer>,AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final CustomerMapper customerMapper=new CustomerMapper();

    public  CustomerRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }

    @Override
    public void save(Customer customer) throws Exception {
        preparedStatement=connection.prepareStatement(
                "insert into customers(customer_id,name,family,phone_number,age)" +
                "values(?,?,?,?,?)"
        );
        preparedStatement.setInt(1, customer.getCustomerId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getFamily());
        preparedStatement.setString(4, customer.getPhoneNumber());
        preparedStatement.setInt(5, customer.getAge());
        preparedStatement.executeUpdate();

    }

    @Override
    public void edit(Customer customer) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update customers set name=?,family=?,phone_number=?,age=? where customer_id=?"
        );
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getFamily());
        preparedStatement.setString(3, customer.getPhoneNumber());
        preparedStatement.setInt(4, customer.getAge());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from customers where customer_id=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();

    }

    @Override
    public List<Customer> findAll() throws Exception {
        List<Customer> customerList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from customers order by customer_id");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = customerMapper.customerMapper(resultSet);
            customerList.add(customer);
        }
        return customerList;
    }

    @Override
    public Customer findById(Integer id) throws Exception {
       Customer customer = null;

       preparedStatement=connection.prepareStatement("select * from customers where customer_id=?");
       preparedStatement.setInt(1, id);
       ResultSet resultSet = preparedStatement.executeQuery();
       if (resultSet.next()) {
           customer = customerMapper.customerMapper(resultSet);
       }
       return customer;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();

    }
}
