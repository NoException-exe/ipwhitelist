package org.exp.ipwhitelist.db;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.exp.ipwhitelist.interfaces.ISqlLiteInterface;
import org.exp.ipwhitelist.query.SQLiteQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite implements ISqlLiteInterface {
    private Connection connection;

    @Override
    public boolean connect(Plugin plugin) {
        try {
            //this class is default of spigot bundler;
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder().getAbsolutePath() + "/database.db");
            Bukkit.getConsoleSender().sendMessage("SQLite Connected with success.");
            return true;
        }
        catch (ClassNotFoundException | SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }

        return false;
    }
    @Override
    public Connection getConnection() {
        return  connection;
    }

    @Override
    public void closeConnection() {
        if(connection != null){

            try {
                connection.close();
                Bukkit.getConsoleSender().sendMessage("SQLite connection closed");
            }
            catch (SQLException err){
                Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
            }
        }
    }

    @Override
    public void createTable() {    //create new table if not exist.
        try {
            Statement statement = connection.createStatement();

            if(statement.execute(SQLiteQuery.createWhitelistTableQuery)){
                Bukkit.getConsoleSender().sendMessage("Tables Whitelist Created");
            }

             if(statement.execute(SQLiteQuery.createAdminTableQuery)){
                Bukkit.getConsoleSender().sendMessage("Table Admin Created");
            }

            statement.close();
        }
        catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }
    }

}
