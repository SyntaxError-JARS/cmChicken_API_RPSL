package com.revature.cmchicken.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();

    private Properties prop = new Properties();


    private ConnectionFactory(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            prop.load(loader.getResourceAsStream("db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static ConnectionFactory getInstance() {

        return connectionFactory;
    }

    public Connection getConnection(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(prop.getProperty("url"),
                    prop.getProperty("user"), prop.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }



}
