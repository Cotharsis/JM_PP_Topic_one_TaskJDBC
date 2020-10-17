package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    final private String URL = "jdbc:mysql://localhost:3306/jm_schema_one?autoReconnect=true&useSSL=false";
    final private String USER = "root";
    final private String PASSWORD = "vfhc45rbibH23";
    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("Connection not gut!!!");
        }
    }
}


