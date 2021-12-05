/*
Author: Blura_YT
Date: 05.12.2021
@Copyright by Blura_YT
*/
package at.blura.vanish.object;

import at.blura.vanish.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class VanishPlayer {
    private final static HashMap<UUID, VanishPlayer> VANISH_PLAYERS = new HashMap<>();

    private Vanish vanish;
    private UUID vanishPlayerUUID;
    private Player vanishPlayer;

    public VanishPlayer(UUID vanishPlayerUUID, Vanish vanish) {
        this.vanish = vanish;
        this.vanishPlayerUUID = vanishPlayerUUID;
        this.vanishPlayer = Bukkit.getPlayer(vanishPlayerUUID);
        Player player;
    }

    public VanishPlayer register() {
        VANISH_PLAYERS.put(vanishPlayerUUID, this);
        return this;
    }

    public VanishPlayer remove() {
        if(VANISH_PLAYERS.containsKey(vanishPlayerUUID)) {
            VANISH_PLAYERS.remove(vanishPlayerUUID);
        }
        return this;
    }

    public VanishPlayer hidePlayer(Player player) {
        if(!player.hasPermission("vanish.bypass") && player.canSee(vanishPlayer)) {
            vanishPlayer.hidePlayer(vanish, player);
        }
        return this;
    }

    public VanishPlayer hidePlayer() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayer.hasPermission("vanish.bypass") && onlinePlayer.canSee(vanishPlayer)) {
                vanishPlayer.hidePlayer(vanish, onlinePlayer);
            }
        }
        return this;
    }

    public VanishPlayer showPlayer(Player player) {
        if(!player.hasPermission("vanish.bypass") && !player.canSee(vanishPlayer)) {
            vanishPlayer.showPlayer(vanish, player);
        }
        return this;
    }

    public VanishPlayer showPlayer() {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if(!onlinePlayer.hasPermission("vanish.bypass") && !onlinePlayer.canSee(vanishPlayer)) {
                vanishPlayer.showPlayer(vanish, onlinePlayer);
            }
        }
        return this;
    }

    public UUID getVanishPlayerUUID() {
        return vanishPlayerUUID;
    }

    public Player getVanishPlayer() {
        return vanishPlayer;
    }

    public static boolean containsVanishPlayer(UUID uuid) {
        return VANISH_PLAYERS.containsKey(uuid);
    }

    public static VanishPlayer getVanishPlayer(UUID uuid) {
        return VANISH_PLAYERS.get(uuid);
    }

    public static HashMap<UUID, VanishPlayer> getVanishPlayers() {
        return VANISH_PLAYERS;
    }
}