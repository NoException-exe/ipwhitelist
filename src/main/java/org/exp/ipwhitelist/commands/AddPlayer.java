package org.exp.ipwhitelist.commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.exp.ipwhitelist.auth.AuthPlayer;
import org.exp.ipwhitelist.repository.AdminRepository;
import org.exp.ipwhitelist.repository.PlayerRepository;

import java.sql.Connection;

public class AddPlayer implements CommandExecutor {

    private final AdminRepository adminRepository;
    private final PlayerRepository playerRepository;
    private  final AuthPlayer authPlayer;

    public AddPlayer(Connection connection) {
        this.adminRepository = new AdminRepository(connection);
        this.playerRepository = new PlayerRepository(connection);
        this.authPlayer = new AuthPlayer(adminRepository);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {

        if (commandSender instanceof ConsoleCommandSender) {
            if (args.length < 1) {
                commandSender.sendMessage("Use add-player <PlayerNickName> ");
                return true;
            }

            String playerName = args[0].toLowerCase();

            if (playerRepository.exist(playerName)) {
                commandSender.sendMessage("Player already exist.");
                return true;
            }

            playerRepository.create(playerName);

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

            if (playerRepository.exist(playerName)) {
                player.sendMessage("Player already exist.");
                return true;
            }

            //add player to whitelist.
            playerRepository.create(playerName);
            player.sendMessage("Player Added success to whitelist.");

            return true;
        }
    }
}
