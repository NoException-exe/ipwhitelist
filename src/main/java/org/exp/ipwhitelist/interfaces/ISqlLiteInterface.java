package org.exp.ipwhitelist.interfaces;

import org.bukkit.plugin.Plugin;

import java.sql.Connection;

public interface ISqlLiteInterface {
    public boolean connect(Plugin plugin);
    public Connection getConnection();
    public void closeConnection();
    public void createTable();
}
