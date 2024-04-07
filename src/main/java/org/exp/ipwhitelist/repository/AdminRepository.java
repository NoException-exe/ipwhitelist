package org.exp.ipwhitelist.repository;

import org.bukkit.Bukkit;
import org.exp.ipwhitelist.interfaces.IAdminInterface;
import org.exp.ipwhitelist.query.SQLQuery;
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
    public boolean exist(String username, String adminNickName) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLQuery.FIND_ADMIN)){
            statement.setString(1, username);
            statement.setString(2, adminNickName);
            ResultSet resultSet = statement.executeQuery();

           if (!resultSet.next())
               return false;

           //set data
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
    public void create(String username, String password, String adminNickname){
        try (PreparedStatement statement = this.connection.prepareStatement(SQLQuery.CREATE_NEW_ADMIN)){
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, adminNickname);

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

    @Override
    public void update(String username, boolean authenticated) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLQuery.UPDATE_ADMIN_AUTH)){
            statement.setBoolean(1, authenticated);
            statement.setString(2, username);
            statement.setString(3, username);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                Bukkit.getConsoleSender().sendMessage("Admin updated with success");
            } else {
                Bukkit.getConsoleSender().sendMessage("Failed to update admin");
            }

        } catch (SQLException err) {
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }
    }

    @Override
    public boolean IsAuthenticated(String adminNickName) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQLQuery.ADMIN_IS_AUTHENTICATED)){
            statement.setString(1, adminNickName);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        }catch (SQLException err){
            Bukkit.getConsoleSender().sendMessage("Error: " + err.getMessage());
        }

        return false;
    }
}
