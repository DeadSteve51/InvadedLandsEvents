package me.nicbo.InvadedLandsEvents.handlers;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

/**
 * File/config handler
 *
 * @author thehydrogen
 * @since 2020-04-22
 */

public class EventFile {

    private File file;
    private FileConfiguration config;
    private String name;

    public EventFile(String fileName, Plugin plugin) {

        file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            plugin.saveResource(fileName, false);
        }

        name = fileName;

        try {
            config = new YamlConfiguration();
            config.load(file);
        } catch (Exception e) {
            plugin.getLogger().warning("Could not create the config file " + fileName);
        }
    }

    public void save() {
        try {
            config.save(file);
            reload();
        } catch(Exception e) {
            Bukkit.getLogger().info("Could not save the config file " + name);
        }
    }

    public void reload() {
        try {
            config = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            Bukkit.getLogger().info("Could not reload the config file " + name);
        }
    }

    public FileConfiguration get() { return config; }
    public FileConfiguration getConfig() { return config; }
}
