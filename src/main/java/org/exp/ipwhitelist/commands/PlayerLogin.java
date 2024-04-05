package org.exp.ipwhitelist.commands;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.exp.ipwhitelist.auth.AuthPlayer;
import org.exp.ipwhitelist.repository.AdminRepository;

import java.sql.Connection;

public class PlayerLogin implements CommandExecutor {

    private final AdminRepository adminRepository;
    private  final AuthPlayer authPlayer;

    public PlayerLogin(Connection connection) {
      this.adminRepository = new AdminRepository(connection);
      this.authPlayer = new AuthPlayer(adminRepository);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {

        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;

            if (args.length < 2){
                return false;
            }

            String username = args[0].toLowerCase();
            String password = args[1];
            String adminNickName = player.getName().toLowerCase();


            if (!authPlayer.login(username, adminNickName, password)){
                player.sendMessage("Username or Password Incorrect.");
                return true;
            }

            adminRepository.update(username, true);
            player.sendMessage("Login Successful.");
            return  true;
        }
        else{
            commandSender.sendMessage("This command only run by player.");
            return  true;
        }
    }
}
