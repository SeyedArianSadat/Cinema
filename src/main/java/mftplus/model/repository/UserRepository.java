package mftplus.model.repository;

import mftplus.model.entity.User;
import mftplus.model.tools.ConnectionProvider;
import mftplus.model.tools.UserMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer>, AutoCloseable {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private final UserMapper userMapper= new UserMapper();

    public UserRepository( )throws SQLException{
        connection = ConnectionProvider.getProvider().getOracleConnection();
    }
    @Override
    public void save(User user) throws Exception {
       preparedStatement=connection.prepareStatement(
               "insert into USERS(user_id,username,password,role,customer_id) values(?,?,?,?,?)"
       );
       preparedStatement.setInt(1,user.getUserId());
       preparedStatement.setString(2,user.getUsername());
       preparedStatement.setString(3,user.getPassword());
       preparedStatement.setString(4,user.getRole());
       preparedStatement.setInt(5,user.getCustomer().getCustomerId());
       preparedStatement.executeUpdate();
    }

    @Override
    public void edit(User user) throws Exception {
        preparedStatement=connection.prepareStatement(
                "update users set username=?,password=?,role=? where user_id=?"
        );
        preparedStatement.setString(1,user.getUsername());
        preparedStatement.setString(2,user.getPassword());
        preparedStatement.setString(3,user.getRole());
        preparedStatement.executeUpdate();

    }

    @Override
    public void delete(Integer id) throws Exception {
        preparedStatement=connection.prepareStatement(
                "delete from users where user_id=?"
        );
        preparedStatement.setInt(1,id);
        preparedStatement.executeUpdate();
    }
    @Override
    public List<User> findAll() throws Exception {
        List<User> users = new ArrayList<>();

        preparedStatement=connection.prepareStatement(
                "select * from users order by user_id");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = userMapper.userMapper(resultSet);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findById(Integer id) throws Exception {
        User user = null;

        preparedStatement=connection.prepareStatement("select * from users where user_id=?");
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }
        return user;
    }
    public User login(String username, String password) throws Exception {
        User user = null;
        preparedStatement=connection.prepareStatement("select * from users where username=? and password=?");
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = userMapper.userMapper(resultSet);
        }return user;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
    }
