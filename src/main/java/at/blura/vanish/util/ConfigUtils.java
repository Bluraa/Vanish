/*
Author: Blura_YT
Date: 05.12.2021
@Copyright by Blura_YT
*/
package at.blura.vanish.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtils {

    private File settingsFolder;
    private File settingsFile;
    private YamlConfiguration settingsConfig;

    public ConfigUtils() {
        this.settingsFolder = new File("plugins/Vanish");
        this.settingsFile = new File("plugins/Vanish/settings.yml");
        this.settingsConfig = createSettingsConfig();
    }

    public YamlConfiguration createSettingsConfig() {
        try {
            if(!settingsFolder.exists()) {
                settingsFolder.mkdirs();
            }
            YamlConfiguration settingsConfig = YamlConfiguration.loadConfiguration(this.settingsFile);
            if(!settingsFile.exists()) {
                settingsFile.createNewFile();
                settingsConfig.set("Messages.Prefix", "&6[&eVanish&6] &f");
                settingsConfig.set("Messages.Vanished", "&aDu wurdest erfolgreich in den Vanish-Modus versetzt!");
                settingsConfig.set("Messages.VanishedOtherPlayer", "&aDu hast den Spieler &a&l%TARGET% &aerfolgreich in den Vanish-Modus versetzt!");
                settingsConfig.set("Messages.Showed", "&aDu bist nicht mehr im Vanish-Modus!");
                settingsConfig.set("Messages.ShowedOtherPlayer", "&aDer Spieler &a&l%TARGET% &aist nun nicht mehr im Vanish-Modus!");
                settingsConfig.set("Messages.NoPermission", "&cDazu hast du keine Rechte!");
                settingsConfig.set("Messages.PlayerNotOnline", "&cDer Spieler &c&l%TARGET% &cist nicht online!");
                settingsConfig.set("Messages.Usage", "&4Bitte verwende &c/vanish &8| &c/vanish <Player>");
                settingsConfig.save(settingsFile);
            }
            return settingsConfig;
        } catch(IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cEs ist ein Fehler beim Erstellen einer Datei aufgetreten!");
            e.printStackTrace();
        }
        return null;
    }

    public File getSettingsFolder() {
        return settingsFolder;
    }

    public File getSettingsFile() {
        return settingsFile;
    }

    public YamlConfiguration getSettingsConfig() {
        return settingsConfig;
    }
}