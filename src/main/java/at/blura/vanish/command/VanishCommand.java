/*
Author: Blura_YT
Date: 05.12.2021
@Copyright by Blura_YT
*/
package at.blura.vanish.command;

import at.blura.vanish.Vanish;
import at.blura.vanish.object.VanishPlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    private Vanish vanish;

    public VanishCommand(Vanish vanish) {
        this.vanish = vanish;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("vanish.command.use")) {
            sender.sendMessage(vanish.getPrefix() + vanish.getNoPermissionMessage());
            return true;
        }
        if(args.length == 0) {
            if(!(sender instanceof Player)) return true;
            Player player = (Player) sender;
            UUID uuid = player.getUniqueId();
            if(!VanishPlayer.containsVanishPlayer(uuid)) {
                VanishPlayer vanishPlayer = new VanishPlayer(uuid, vanish);
                vanishPlayer.hidePlayer().register();
                player.sendMessage(vanish.getVanishedMessage());
            } else {
                VanishPlayer vanishPlayer = VanishPlayer.getVanishPlayer(uuid);
                vanishPlayer.showPlayer().remove();
                player.sendMessage(vanish.getShowedMessage());
            }
        } else if(args.length == 1) {
            if(!sender.hasPermission("vanish.command.other")) {
                sender.sendMessage(vanish.getPrefix() + vanish.getNoPermissionMessage());
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            UUID targetUUID = target.getUniqueId();
            if(target != null) {
                if(!VanishPlayer.containsVanishPlayer(targetUUID)) {
                    VanishPlayer vanishPlayer = new VanishPlayer(targetUUID, vanish);
                    vanishPlayer.hidePlayer().register();
                    sender.sendMessage(vanish.getPrefix() + vanish.getVanishedOtherPlayerMessage(target.getName()));
                    target.sendMessage(vanish.getPrefix() + vanish.getVanishedMessage());
                } else {
                    VanishPlayer vanishPlayer = VanishPlayer.getVanishPlayer(targetUUID);
                    vanishPlayer.showPlayer().remove();
                    sender.sendMessage(vanish.getPrefix() + vanish.getShowedOtherPlayerMessage(target.getName()));
                    target.sendMessage(vanish.getPrefix() + vanish.getShowedMessage());
                }
            } else {
                sender.sendMessage(vanish.getPrefix() + vanish.getPlayerNotOnlineMessage(args[0]));
            }
        } else {
            sender.sendMessage(vanish.getPrefix() + vanish.getUsageMessage());
        }
        return false;
    }
}