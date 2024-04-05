package org.exp.ipwhitelist.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.exp.ipwhitelist.auth.AuthPlayer;
import org.exp.ipwhitelist.repository.AdminRepository;
import org.exp.ipwhitelist.repository.PlayerRepository;

import java.sql.Connection;

public class RemovePlayer implements CommandExecutor {

    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;
    private  final AuthPlayer authPlayer;
    public RemovePlayer(Connection connection) {
        this.adminRepository = new AdminRepository(connection);
        this.playerRepository = new PlayerRepository(connection);
        this.authPlayer = new AuthPlayer(adminRepository);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {

        if (commandSender instanceof ConsoleCommandSender) {
            if (args.length < 1) {
                commandSender.sendMessage("Use delete-player <PlayerNickName>");
                return true;
            }

            String playerName = args[0].toLowerCase();

            if (!playerRepository.exist(playerName))
            {
                commandSender.sendMessage("Player not exist.");
                return true;
            }

            playerRepository.delete(playerName);

            return true;
        }
        else {

            Player player = (Player) commandSender;

            if (args.length < 1) {
                return false;
            }

            String playerName = args[0].toLowerCase();
            String adminNickname = player.getName().toLowerCase();


            if (!authPlayer.playerIsAuthenticated(adminNickname)) {
                player.sendMessage("Login first /wl-login <username> <password>");
                return true;
            }


            if (!playerRepository.exist(playerName)){
                commandSender.sendMessage("Player not exist.");
                return true;
            }

            playerRepository.delete(playerName);
            player.sendMessage("Player removed from whitelist.");
            return  true;

        }
    }
}
