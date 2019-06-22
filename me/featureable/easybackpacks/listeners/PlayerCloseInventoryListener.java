package me.featureable.easybackpacks.listeners;

import me.featureable.easybackpacks.EasyBackPacks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerCloseInventoryListener implements Listener {

    private EasyBackPacks plugin;

    public PlayerCloseInventoryListener(EasyBackPacks plugin) {
        this.plugin = plugin;

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        String title = e.getInventory().getTitle();

        if (title.equalsIgnoreCase(ChatColor.GREEN + "Backpack") == false) return;

        Inventory inv = e.getInventory();

        plugin.getConfig().set(e.getPlayer().getUniqueId().toString(), null);
        //plugin.saveConfig();
        for (int i = 0; i <= 35; i++) {
            ItemStack item = inv.getItem(i);
            if (item == null) continue;
            if (item.getType() == Material.AIR) continue;
            plugin.getConfig().set(e.getPlayer().getUniqueId().toString() + "." + i, item);
        }
        plugin.saveConfig();
    }
}
