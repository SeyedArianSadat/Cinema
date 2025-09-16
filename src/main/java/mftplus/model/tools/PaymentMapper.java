package mftplus.model.tools;

import mftplus.model.entity.Payment;
import mftplus.model.entity.enums.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws SQLException {
        return Payment
                .builder()
                .paymentId(resultSet.getInt("payment-id"))
                .amount(resultSet.getDouble("amount"))
                //.paymentType(PaymentType.valueOf(resultSet.getString("payment-type")))
                .paymentTime(resultSet.getTimestamp("payment-time").toLocalDateTime())
                .build();
    }
}
