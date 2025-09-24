package mftplus.model.repository;

import mftplus.model.entity.Payment;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.PaymentMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository implements Repository<Payment, Integer> , AutoCloseable {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final PaymentMapper paymentMapper = new PaymentMapper();

    public PaymentRepository() throws SQLException {
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }



    @Override
    public void save(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement("insert into PAYMENTS(PAYMENT_ID, AMOUNT, PAYMENT_TYPE, PAYMENT_TIME)"+
                " values(?,?,?,?)");

        preparedStatement.setInt(1, payment.getPaymentId());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setString(3, payment.getPaymentType().toString());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(payment.getPaymentTime()));
        preparedStatement.execute();

    }

    @Override
    public void edit(Payment payment) throws Exception {
        preparedStatement = connection.prepareStatement("update PAYMENTS set AMOUNT=?, PAYMENT_TYPE=?, PAYMENT_TIME=? where PAYMENT_ID=?");

        preparedStatement.setDouble(1, payment.getAmount());
        preparedStatement.setString(2, payment.getPaymentType().toString());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(payment.getPaymentTime()));
        preparedStatement.execute();
    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement = connection.prepareStatement("delete from PAYMENTS where PAYMENT_ID=?");

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    @Override
    public List<Payment> findAll() throws Exception {
        List<Payment> paymentList = new ArrayList<>();

        preparedStatement = connection.prepareStatement("select * from PAYMENTS");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Payment payment = paymentMapper.paymentMapper(resultSet);
            paymentList.add(payment);
        }
        return paymentList;
    }


    @Override
    public Payment findById(Integer id) throws Exception {
       Payment payment = null;
       preparedStatement = connection.prepareStatement("select * from PAYMENTS where PAYMENT_ID=?");
       preparedStatement.setInt(1, id);
       ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            payment = paymentMapper.paymentMapper(resultSet);
        }
        return payment;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
