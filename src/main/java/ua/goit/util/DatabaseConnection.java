package ua.goit.util;

import lombok.SneakyThrows;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection implements Closeable {

    private final static String URL = PropertiesLoader.getProperty("db.url");
    private final static String USERNAME = PropertiesLoader.getProperty("db.username");
    private final static String PASSWORD = PropertiesLoader.getProperty("db.password");
    private final static String JDBC_DRIVER = PropertiesLoader.getProperty("db.driver");
    private static DatabaseConnection databaseConnection;
    private final Connection connection;

    @SneakyThrows
    private DatabaseConnection() {
        Class.forName(JDBC_DRIVER);
        this.connection = DriverManager. getConnection(URL, USERNAME, PASSWORD);
    }

    @SneakyThrows
    public static DatabaseConnection getInstance() {
        if (databaseConnection==null){
             databaseConnection = new DatabaseConnection();
        }else if (databaseConnection.connection.isClosed()){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        databaseConnection.close();
    }
}
