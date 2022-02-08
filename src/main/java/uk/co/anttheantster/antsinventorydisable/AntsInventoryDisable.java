package uk.co.anttheantster.antsinventorydisable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntsInventoryDisable extends JavaPlugin implements Listener, CommandExecutor {

    String onOrOff;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        onOrOff = getConfig().getString("Default");

        Bukkit.getPluginManager().registerEvents(this, this);
        getCommand("aid").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("aid")){
            if (args[0].equalsIgnoreCase("enable")){
                onOrOff = "Enabled";
                return false;
            } else if (args[0].equalsIgnoreCase("disable")){
                onOrOff = "Disabled";
                return false;
            }
            sender.sendMessage(ChatColor.GOLD + "Usage: /aid <Enable/Disable>");
            return false;
        }
        return false;
    }

    @EventHandler
    public void playerUseInv(InventoryClickEvent e){
        if (onOrOff.equalsIgnoreCase("Enabled")){
            if (e.getWhoClicked().hasPermission("aid.bypass")){
                e.setCancelled(true);
            }
        }
        if (onOrOff.equalsIgnoreCase("Disabled")){
            e.setCancelled(false);
        }
    }
}
