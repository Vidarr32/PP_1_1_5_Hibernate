package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS userstable;");
            statement.executeUpdate("CREATE TABLE userstable(\n" +
                    "id int not null auto_increment,\n" +
                    "name varchar(30),\n" +
                    "lastName varchar(30),\n" +
                    "age int,\n" +
                    "primary key(id));");
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.getStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO userstable (name, lastname, age) VALUES (?, ?, ?);");

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try {
            PreparedStatement statement =
                    connection.prepareStatement("DELETE FROM userstable WHERE id=?;");

            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userstable;");

            while (resultSet.next()) {
                User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("TRUNCATE TABLE userstable;");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
