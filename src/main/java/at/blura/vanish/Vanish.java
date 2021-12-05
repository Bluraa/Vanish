package at.blura.vanish;

import at.blura.vanish.command.VanishCommand;
import at.blura.vanish.listener.PlayerConnectionListener;
import at.blura.vanish.util.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanish extends JavaPlugin {

    private static Vanish instance;
    private ConfigUtils configUtils;

    @Override
    public void onEnable() {
        instance = this;
        configUtils = new ConfigUtils();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerConnectionListener(), this);
        getCommand("vanish").setExecutor(new VanishCommand(this));
        Bukkit.getConsoleSender().sendMessage("§a§lVanish Plugin §awurde erfolgreich gestartet!");
    }

    @Override
    public void onDisable() {
        instance = null;
        configUtils = null;
        Bukkit.getConsoleSender().sendMessage("§c§lVanish Plugin §cwurde heruntergefahren!");
    }

    public String getPrefix() {
        return configUtils.getSettingsConfig().getString("Messages.Prefix").replace("&", "§");
    }

    public String getVanishedMessage() {
        return configUtils.getSettingsConfig().getString("Messages.Vanished").replace("&", "§");
    }

    public String getVanishedOtherPlayerMessage(String playerName) {
        return configUtils.getSettingsConfig().getString("Messages.VanishedOtherPlayer").replace("&", "§")
                .replace("%TARGET%", playerName);
    }

    public String getShowedMessage() {
        return configUtils.getSettingsConfig().getString("Messages.Showed").replace("&", "§");
    }

    public String getShowedOtherPlayerMessage(String playerName) {
        return configUtils.getSettingsConfig().getString("Messages.ShowedOtherPlayer").replace("&", "§")
                .replace("%TARGET%", playerName);
    }

    public String getNoPermissionMessage() {
        return configUtils.getSettingsConfig().getString("Messages.NoPermission").replace("&", "§");
    }

    public String getPlayerNotOnlineMessage(String playerName) {
        return configUtils.getSettingsConfig().getString("Messages.PlayerNotOnline").replace("&", "§")
                .replace("%TARGET%", playerName);
    }

    public String getUsageMessage() {
        return configUtils.getSettingsConfig().getString("Messages.Usage").replace("&", "§");
    }

    public ConfigUtils getConfigUtils() {
        return configUtils;
    }

    public static Vanish getInstance() {
        return instance;
    }
}