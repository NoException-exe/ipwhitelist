package org.exp.ipwhitelist.repository;

import org.bukkit.Bukkit;
import org.exp.ipwhitelist.interfaces.IAdminInterface;
import org.exp.ipwhitelist.query.SQLiteQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminRepository implements IAdminInterface {

    private final Connection connection;
    private String data;

    public AdminRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean exist(String username) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.findAdminQuery)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

           if (!resultSet.next())
               return false;

           this.data = resultSet.getString("password");

           return true;

        }catch (SQLException err){
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }

        return false;
    }

    @Override
    public String getData (){
      return this.data;
    }

    @Override
    public void create(String username, String password) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLiteQuery.crateNewAdminQuery)){
            statement.setString(1, username);
            statement.setString(2, password);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                Bukkit.getConsoleSender().sendMessage("Admin registered with success");
            } else {
                Bukkit.getConsoleSender().sendMessage("Failed to register admin");
            }

        } catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }
    }

}
