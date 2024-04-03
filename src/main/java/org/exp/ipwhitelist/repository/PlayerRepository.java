package org.exp.ipwhitelist.repository;

import org.bukkit.Bukkit;
import org.exp.ipwhitelist.interfaces.IPlayerInterface;
import org.exp.ipwhitelist.query.SQLiteQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository implements IPlayerInterface {

    private final Connection connection;

    private  String data;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean exist(String playerIp) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.playerQuery)){
            statement.setString(1, playerIp);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next())
                return false;

            this.data = resultSet.getString("ip");

            return true;

        }catch (SQLException err){
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }

        return false;
    }

    @Override
    public void create(String PlayerName) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.createNewPlayerQuery)){
            statement.setString(1, PlayerName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                Bukkit.getConsoleSender().sendMessage("Player registered with success");
            } else {
                Bukkit.getConsoleSender().sendMessage("Failed to register Player");
            }

        } catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }
    }

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public void update(String playerName, String playerIP) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.updatePlayerIpQuery)) {
            statement.setString(1, playerIP);
            statement.setString(2, playerName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                Bukkit.getConsoleSender().sendMessage("Player updated with success");
            } else {
                Bukkit.getConsoleSender().sendMessage("Failed to update Player");
            }

        } catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }

    }

    @Override
    public void delete(String PlayerName) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.deletePlayerQuery)) {
            statement.setString(1, PlayerName);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                Bukkit.getConsoleSender().sendMessage("Player deleted with success");
            } else {
                Bukkit.getConsoleSender().sendMessage("delete to update Player");
            }

        } catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }
    }
}
