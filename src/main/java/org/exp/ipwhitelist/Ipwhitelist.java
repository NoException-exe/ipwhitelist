package org.exp.ipwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.exp.ipwhitelist.commands.AddPlayer;
import org.exp.ipwhitelist.commands.PluginControl;
import org.exp.ipwhitelist.commands.RemovePlayer;
import org.exp.ipwhitelist.commands.RegisterAdmin;
import org.exp.ipwhitelist.db.SQLite;
import org.exp.ipwhitelist.listeners.PlayerJoin;

import java.io.File;
import java.io.IOException;

public final class Ipwhitelist extends JavaPlugin {
    public  static Ipwhitelist getInstance;

    public boolean isEnable = false;

    private SQLite sqlite;

    @Override
    public void onEnable() {

        getInstance = this;

        //create plugin path if not exist.
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }

        File databaseFile = new File(getDataFolder(), "database.db");

        if(!databaseFile.exists()){
            try{
                databaseFile.createNewFile();
            }catch (IOException err) {
                Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
            }
        }

        //config sqlite
        sqlite = new SQLite();

        if(sqlite.connect(this)){
            sqlite.createTable(); //creating tables.

            //register event
            Bukkit.getPluginManager().registerEvents(new PlayerJoin(sqlite.getConnection()), this);

            //register command
            Bukkit.getPluginCommand("register-admin").setExecutor(new RegisterAdmin(sqlite.getConnection()));

            //add-ip command
            Bukkit.getPluginCommand("add-player").setExecutor(new AddPlayer(sqlite.getConnection()));

            //delete player
            Bukkit.getPluginCommand("remove-player").setExecutor(new RemovePlayer(sqlite.getConnection()));

            //on/off command
            Bukkit.getPluginCommand("whitelist-start").setExecutor(new PluginControl());

        }
        else{
            Bukkit.getConsoleSender().sendMessage("Failed to connect to SQLite database. Plugin will be disabled.");
            getServer().getPluginManager().disablePlugin(this);
        }

    }

    @Override
    public void onDisable() {
        if(sqlite != null){
            sqlite.closeConnection();
        }
    }
}
