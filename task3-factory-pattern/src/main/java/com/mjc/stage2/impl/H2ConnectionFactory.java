package com.mjc.stage2.impl;

import com.mjc.stage2.ConnectionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H2ConnectionFactory implements ConnectionFactory {
    @Override
    public Connection createConnection() {
        try (InputStream stream = H2ConnectionFactory.class
                .getClassLoader()
                .getResourceAsStream("h2database.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            return DriverManager.getConnection(properties.getProperty("db_url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

