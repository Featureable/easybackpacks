package me.featureable.easybackpacks;

import me.featureable.easybackpacks.commands.*;
import me.featureable.easybackpacks.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class EasyBackPacks extends JavaPlugin {

    public String ebheader = ChatColor.translateAlternateColorCodes('&', "&8[&4EasyBackpacks&8]");

    @Override
    public void onEnable() {
        new PlayerCloseInventoryListener(this);
        new BackpackCommand(this);

        Bukkit.getConsoleSender().sendMessage(ebheader + ChatColor.GREEN + " Enabled.");
        PluginUpdater updater = new PluginUpdater(this, 68568);
        try {
            if (updater.checkForUpdates()) {
                Bukkit.getConsoleSender().sendMessage(ebheader + ChatColor.GREEN + " An update was found! New version: '" + updater.getLatestVersion() + "'. Download: " + updater.getResourceURL());
            } else {
                Bukkit.getConsoleSender().sendMessage(ebheader + ChatColor.GREEN + " Version: '" + updater.getLatestVersion() + "' up to date.");
            }
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ebheader + ChatColor.RED + " Could not check for updates! Stacktrace:");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ebheader + ChatColor.GREEN + " Disabled.");
    }
}
