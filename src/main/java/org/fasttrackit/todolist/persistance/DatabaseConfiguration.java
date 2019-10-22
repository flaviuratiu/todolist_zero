package org.fasttrackit.todolist.persistance;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        InputStream inputStream = DatabaseConfiguration.class.getClassLoader()
                .getResourceAsStream("db.properties");

        if (inputStream == null) {
            throw new RuntimeException("Could not read properties file.");
        }

        try {
            Properties properties = new Properties();
            properties.load(inputStream);

            Class.forName(properties.getProperty("driver.class"));

            return DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
        } finally {
            inputStream.close();
        }
    }
}
