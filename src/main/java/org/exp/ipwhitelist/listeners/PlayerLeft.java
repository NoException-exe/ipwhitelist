package org.exp.ipwhitelist.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.exp.ipwhitelist.repository.AdminRepository;

import java.sql.Connection;

public class PlayerLeft implements Listener {

    private final AdminRepository adminRepository;

    public PlayerLeft(Connection connection) {
        this.adminRepository = new AdminRepository(connection);
    }

    @EventHandler
    public void playerLeft(PlayerQuitEvent event){
        String playerName = event.getPlayer().getName().toLowerCase();
        adminRepository.update(playerName, false);
    }
}
