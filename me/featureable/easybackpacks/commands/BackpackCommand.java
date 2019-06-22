package me.featureable.easybackpacks.commands;

import me.featureable.easybackpacks.EasyBackPacks;
import org.apache.logging.log4j.core.appender.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BackpackCommand implements CommandExecutor {

    private EasyBackPacks plugin;

    public BackpackCommand(EasyBackPacks plugin) {
        this.plugin = plugin;

        plugin.getCommand("backpack").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("backpack")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(plugin.ebheader + ChatColor.RED + " Only players can use that command!");
                return true;
            }

            if (!(sender.hasPermission("easybp.open"))) {
                sender.sendMessage(plugin.ebheader + ChatColor.RED + " You do not have permission to access that command!");
                return true;
            }

            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(null, 36, ChatColor.GREEN + "Backpack");

            if (plugin.getConfig().contains(player.getUniqueId().toString())) {
                for (String i : plugin.getConfig().getConfigurationSection(player.getUniqueId().toString()).getKeys(false)) {
                    ItemStack item = plugin.getConfig().getItemStack(player.getUniqueId().toString() + "." + i);
                    inv.addItem(item);
                }
            }

            player.openInventory(inv);
            return true;
        }
        return true;
    }
}
