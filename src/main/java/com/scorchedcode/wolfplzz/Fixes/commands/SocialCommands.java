package com.scorchedcode.wolfplzz.Fixes.commands;

import com.scorchedcode.wolfplzz.Fixes.DarkInit;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.regex.Pattern;

public class SocialCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String[] parts;
        TextComponent firstHalf = new TextComponent();
        TextComponent link = new TextComponent();
        TextComponent secondHalf = new TextComponent();
        switch (command.getName()) {
            case "discord":
                //Pattern linkPoint = Pattern.compile("\\{\\d+}");
                parts = DarkInit.getPlugin().getConfig().getString("discord-msg").split("\\{discord}");
                firstHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[0]));
                link.setText("Discord");
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DarkInit.getPlugin().getConfig().getString("discord-link")));
                secondHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[1]));
                commandSender.spigot().sendMessage(firstHalf, link, secondHalf);
                break;
            case "twitch":
                parts = DarkInit.getPlugin().getConfig().getString("socials.twitch-msg").split("\\{twitch}");
                firstHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[0]));
                link.setText("Twitch");
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DarkInit.getPlugin().getConfig().getString("socials.twitch-link")));
                secondHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[1]));
                commandSender.spigot().sendMessage(firstHalf, link, secondHalf);
                break;
            case "website":
                parts = DarkInit.getPlugin().getConfig().getString("socials.website-msg").split("\\{website}");
                firstHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[0]));
                link.setText("Website");
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DarkInit.getPlugin().getConfig().getString("socials.website-link")));
                secondHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[1]));
                commandSender.spigot().sendMessage(firstHalf, link, secondHalf);
                break;
            case "youtube":
                parts = DarkInit.getPlugin().getConfig().getString("socials.youtube-msg").split("\\{youtube}");
                firstHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[0]));
                link.setText("Youtube");
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DarkInit.getPlugin().getConfig().getString("socials.youtube-link")));
                secondHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[1]));
                commandSender.spigot().sendMessage(firstHalf, link, secondHalf);
                break;
            case "teamspeak":
                parts = DarkInit.getPlugin().getConfig().getString("socials.teamspeak-msg").split("\\{teamspeak}");
                firstHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[0]));
                link.setText("Teamspeak");
                link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, DarkInit.getPlugin().getConfig().getString("socials.teamspeak-link")));
                secondHalf.setText(ChatColor.translateAlternateColorCodes('&', parts[1]));
                commandSender.spigot().sendMessage(firstHalf, link, secondHalf);
                break;
        }

        return true;
    }
}
