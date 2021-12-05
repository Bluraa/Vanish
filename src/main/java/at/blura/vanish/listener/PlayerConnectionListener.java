/*
Author: Blura_YT
Date: 05.12.2021
@Copyright by Blura_YT
*/
package at.blura.vanish.listener;

import at.blura.vanish.object.VanishPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerConnectionListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        for (VanishPlayer vanishPlayer : VanishPlayer.getVanishPlayers().values()) {
            vanishPlayer.hidePlayer(player);
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        if(VanishPlayer.containsVanishPlayer(uuid)) {
            VanishPlayer.getVanishPlayer(uuid).remove();
        }
    }
}