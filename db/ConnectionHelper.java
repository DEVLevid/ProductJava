package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        final String url = "jdbc:mysql://localhost/loja";
        final String user = "root";
        final String password = "password@123";

        Class.forName(driver);
        return DriverManager.getConnection(url, user, password);
    }
}
