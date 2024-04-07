package org.exp.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.exp.ipwhitelist.commands.*;
import org.exp.ipwhitelist.db.MySql;
import org.exp.ipwhitelist.db.SQLite;
import org.exp.ipwhitelist.listeners.PlayerJoin;
import org.exp.ipwhitelist.listeners.PlayerLeft;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public final class Ipwhitelist extends JavaPlugin {
    public  static Ipwhitelist getInstance;
    public boolean isEnable = false;

    private MySql mySql;
    private SQLite sqLite;

    private InputStream inputStream;
    private Connection connection;

    @Override
    public void onEnable() {

        getInstance = this;

        //create plugin path if not exist.
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }


        //load config.yml
        saveDefaultConfig();

        //init config
        FileConfiguration config = getConfig();

        //mysql initialize
        mySql = new MySql(config);


        if (mySql.connect()) {
            this.connection = mySql.getConnection();
            inputStream = getClass().getResourceAsStream("/MySql/database.sql");
        }
        else {

            //create Sqlite file.
            this.createSqlDbFile();

            sqLite = new SQLite();

            if (!sqLite.connect(this)){
                Bukkit.getConsoleSender().sendMessage("Failed to connect to SQLite database. Plugin will be disabled.");
                getServer().getPluginManager().disablePlugin(this);
            }

            inputStream = getClass().getResourceAsStream("/Sqlite/database.sql");
            this.connection = sqLite.getConnection();
        }

        this.createTable(this.connection);
        this.registerEvents();
        this.registerCommands();

    }

    @Override
    public void onDisable() {
        if (mySql != null) {
            mySql.closeConnection();
        }

        if (sqLite != null) {
            sqLite.closeConnection();
        }
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(this.connection), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeft(this.connection), this);
    }

    private void registerCommands() {
        Bukkit.getPluginCommand("register-admin").setExecutor(new RegisterAdmin(this.connection));
        Bukkit.getPluginCommand("add-player").setExecutor(new AddPlayer(this.connection));
        Bukkit.getPluginCommand("remove-player").setExecutor(new RemovePlayer(this.connection));
        Bukkit.getPluginCommand("whitelist-start").setExecutor(new PluginControl());
        Bukkit.getPluginCommand("wl-login").setExecutor(new PlayerLogin(this.connection));
    }

    private void createTable(Connection connection) {
        try {
            assert inputStream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder query = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                query.append(line);
                if (line.endsWith(";")) {
                    Statement statement = connection.createStatement();
                    statement.executeUpdate(query.toString());
                    statement.close();
                    query = new StringBuilder();
                }
            }

            reader.close();
        }
        catch (IOException | SQLException e){
            Bukkit.getConsoleSender().sendMessage("Error to loader sql file: " + e.getMessage());
        }
    }


    private void createSqlDbFile(){
        //crate sqlite db file if not exist.
        File databaseFile = new File(getDataFolder(), "database.db");

        if(!databaseFile.exists()){
            try{
                databaseFile.createNewFile();
            }catch (IOException err) {
                Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
            }
        }
    }
}
