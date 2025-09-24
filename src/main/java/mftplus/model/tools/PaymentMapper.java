package mftplus.model.tools;

import mftplus.model.entity.*;
import mftplus.model.entity.enums.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {
    public Payment paymentMapper(ResultSet resultSet) throws SQLException {
        return Payment
                .builder()
                .paymentId(resultSet.getInt("payment_id"))
                .amount(resultSet.getDouble("amount"))
                .paymentType(PaymentType.valueOf(resultSet.getString("payment_type")))
                .paymentTime(resultSet.getTimestamp("payment_time").toLocalDateTime())
                .build();
    }
}
