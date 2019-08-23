package com.scorchedcode.wolfplzz.Fixes.commands;

import com.scorchedcode.wolfplzz.Fixes.DarkInit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class DeathLocationCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            File deathFile = new File(DarkInit.getPlugin().getDataFolder().getAbsolutePath() + File.separatorChar + "deaths.yml");
            if(deathFile.exists()) {
                YamlConfiguration deathYaml = YamlConfiguration.loadConfiguration(deathFile);
                if(!deathYaml.get(commandSender.getName(), "none").equals("none")) {
                    String deathLocation = ChatColor.GREEN + "(" + deathYaml.getInt(commandSender.getName() + ".x") + "," +
                            deathYaml.getInt(commandSender.getName() + ".y") + "," +
                            deathYaml.getInt(commandSender.getName() + ".z") + ", " +
                            deathYaml.getString(commandSender.getName() + ".world") + ")";
                    commandSender.sendMessage(deathLocation);
                    return true;
                }
            }
            commandSender.sendMessage(ChatColor.RED + "No death location information recorded yet!");
            return true;
        }
        return false;
    }
}
