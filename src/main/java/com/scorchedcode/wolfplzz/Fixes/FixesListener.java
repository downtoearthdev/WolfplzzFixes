package com.scorchedcode.wolfplzz.Fixes;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FixesListener implements Listener {


    @EventHandler(priority=EventPriority.HIGH)
    public void onPhantomTargetEvent(EntityTargetLivingEntityEvent e) {
        if(e.getEntityType() == EntityType.PHANTOM) {
            if(e.getTarget() instanceof Player && ((Player)e.getTarget()).getStatistic(Statistic.TIME_SINCE_REST) < DarkInit.getPlugin().getConfig().getInt("phantom-control.days-to-appear")) {
                e.getEntity().remove();
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRestFromPhantomEvent(PlayerBedEnterEvent e) {
        DarkInit.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(DarkInit.getPlugin(), () -> {
            if(e.getPlayer().isSleeping()) {
                e.getPlayer().setStatistic(Statistic.TIME_SINCE_REST, 0);
                if(DarkInit.getPlugin().getConfig().getString("phantom-control.rested-message", null) != null && !DarkInit.getPlugin().getConfig().getString("phantom-control.rested-message").isEmpty())
                    e.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + DarkInit.getPlugin().getConfig().getString("phantom-control.rested-message"));
            }
        }, DarkInit.getPlugin().getConfig().getInt("phantom-control.reset-nap-seconds"));

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent e) {
        if(e.getTo().getY() < 0.0) {
            for(int x = 0; x < 250; x++) {
                Block locBlock = e.getTo().getWorld().getBlockAt(e.getTo().getBlockX(), x, e.getTo().getBlockY());
                Block upBlock = locBlock.getRelative(BlockFace.UP);
                Block upUpBlock = upBlock.getRelative(BlockFace.UP);
                if(upBlock.getType() == Material.AIR && upUpBlock.getType() == Material.AIR) {
                    e.setTo(upBlock.getLocation());
                    return;
                }
            }

        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        File deathFile = new File(DarkInit.getPlugin().getDataFolder().getAbsolutePath() + File.separatorChar + "deaths.yml");
        if(!deathFile.exists()) {
            try {
                deathFile.createNewFile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        YamlConfiguration deathYaml = YamlConfiguration.loadConfiguration(deathFile);
        deathYaml.set(e.getEntity().getName() + ".x", e.getEntity().getLocation().getBlockX());
        deathYaml.set(e.getEntity().getName() + ".y", e.getEntity().getLocation().getBlockY());
        deathYaml.set(e.getEntity().getName() + ".z", e.getEntity().getLocation().getBlockZ());
        deathYaml.set(e.getEntity().getName() + ".world", e.getEntity().getLocation().getWorld().getName());
        try {
            deathYaml.save(deathFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWhitelistChallenge(PlayerLoginEvent e) {
       if(WolfplzzFixes.MAINTENANCE_MODE && !DarkInit.hasPerm(e.getPlayer(), "wolfplzzfixes.whitelist"))
           e.disallow(PlayerLoginEvent.Result.KICK_WHITELIST, DarkInit.getPlugin().getConfig().getString("maintenance-mode.kick-message", "The server is in maintenance."));
    }

}
