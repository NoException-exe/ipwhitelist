package org.exp.ipwhitelist.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.exp.ipwhitelist.repository.PlayerRepository;

import java.sql.Connection;

import static org.exp.ipwhitelist.Ipwhitelist.getInstance;

public class PlayerJoin implements Listener {
    private  final PlayerRepository playerRepository;


    public PlayerJoin(Connection connection){
        this.playerRepository = new PlayerRepository(connection);

    }

    @EventHandler
    public boolean joining(PlayerLoginEvent player){

        String playerName = player.getPlayer().getName().toLowerCase();
        String playerIp = player.getAddress().getHostAddress();

        if (!getInstance.isEnable){
            player.allow();
            return  true;
        }

        if (playerRepository.exist(playerName)) {

            String savedPlayerIp = playerRepository.getData();

            if (savedPlayerIp != null && !savedPlayerIp.equals(playerIp)) {
                player.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You do not have permission to join this server.");
                return  false;
            }

            if (savedPlayerIp == null) {
                playerRepository.update(playerName, playerIp);
            }

            player.allow();
            return  true;
        }

        player.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You do not have permission to join this server.");
        return  false;
    }
}
