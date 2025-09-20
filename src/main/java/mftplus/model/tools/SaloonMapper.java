package mftplus.model.tools;

import mftplus.model.entity.Saloon;
import mftplus.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaloonMapper {
    public Saloon saloonMapper(ResultSet resultSet) throws SQLException {
        return Saloon
                .builder()
                .saloonId(resultSet.getInt("saloon-id"))
                .name(resultSet.getString("saloon-name"))
                .address(resultSet.getString("address"))
                .capacity(resultSet.getInt("capacity"))
                .manager(User.builder().userId(resultSet.getInt("manager-id")).build())
                .build();
    }
}
