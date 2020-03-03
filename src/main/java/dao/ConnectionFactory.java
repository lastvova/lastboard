package dao;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Logger logger = Logger.getLogger(ConnectionFactory.class);
    private static Properties properties = new Properties();
    private static Connection connection;

    private ConnectionFactory() {
    }

    private static void loadProperty() {
        try (InputStream inputStream = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                logger.error("Wrong path to property");
                return;
            }
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        loadProperty();
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"));
        } catch (SQLException e) {
            logger.error(e);
        }
        return connection;
    }
}
