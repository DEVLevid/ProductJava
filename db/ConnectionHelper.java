package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        final String driver = "com.mysql.jdbc.Driver";
        final String url = "jdbc:mysql://localhost/LOJA";
        final String user = "root";
        final String password = "123456";

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
