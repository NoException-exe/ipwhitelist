package org.exp.ipwhitelist.db;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql {

    private final FileConfiguration config;
    private Connection connection;

    public MySql(FileConfiguration config) {
        this.config = config;
    }

    public boolean connect() {

        String host = config.getString("db-host");
        String user = config.getString("db-user");
        String password = config.getString(" db-passwd");
        String database = config.getString("database");
        int port = config.getInt("db-port");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + host + ":" + port + "/" + database;
            this.connection = DriverManager.getConnection(url, user, password);
            Bukkit.getConsoleSender().sendMessage("MySql Connected with success.");
            return true;
        }
        catch (ClassNotFoundException | SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Fail to connect mysql database, trying sqlite.");
        }

        return false;
    }

    public void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("MySql connection closed.");
            }
            catch (SQLException err){
                Bukkit.getConsoleSender().sendMessage("Error to close server: " + err.getMessage());
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
