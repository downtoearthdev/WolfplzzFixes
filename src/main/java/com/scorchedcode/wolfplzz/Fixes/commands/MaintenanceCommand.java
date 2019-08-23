package com.scorchedcode.wolfplzz.Fixes.commands;

import com.scorchedcode.wolfplzz.Fixes.DarkInit;
import com.scorchedcode.wolfplzz.Fixes.WolfplzzFixes;
import com.scorchedcode.wolfplzz.Fixes.events.MaintenanceEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MaintenanceCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length == 1) {
            switch(strings[0]) {
                case "on":
                    WolfplzzFixes.MAINTENANCE_MODE = true;
                    commandSender.sendMessage(ChatColor.YELLOW + "Maintenance Mode has been enabled.");
                    //DarkInit.getPlugin().getServer().setWhitelist(true);
                    Bukkit.getPluginManager().callEvent(new MaintenanceEvent(true));
                    return true;
                case "off":
                    WolfplzzFixes.MAINTENANCE_MODE = false;
                    commandSender.sendMessage(ChatColor.GREEN + "Maintenance Mode has been disabled.");
                    //DarkInit.getPlugin().getServer().setWhitelist(false);
                    Bukkit.getPluginManager().callEvent(new MaintenanceEvent(false));
                    return true;
                default:
            }
        }
        return false;
    }
}
