package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Util util = new Util();
        Statement statement = null;
        String sqlCommand = "CREATE TABLE users (Id INT PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(20), lastname VARCHAR(40), age INT)";
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Util util = new Util();
        Statement statement = null;
        String sql = "DROP TABLE `jm_schema_one`.`users`";
        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = util.getConnection().prepareStatement("" +
                    "INSERT INTO users ( NAME, LASTNAME, AGE) VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Util util = new Util();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = util.getConnection().prepareStatement("DELETE FROM users WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Util util = new Util();
        Statement statement = null;
        List<User> usersi = new ArrayList();
        try {
            statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                usersi.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return usersi;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        Statement statement = null;
        try {
            statement = util.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("select id from users");
            while (resultSet.next()) {
                long idNumber = resultSet.getLong(1);
                removeUserById(idNumber);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

