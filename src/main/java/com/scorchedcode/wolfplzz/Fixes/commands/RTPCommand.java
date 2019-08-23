package com.scorchedcode.wolfplzz.Fixes.commands;

import com.scorchedcode.wolfplzz.Fixes.DarkInit;
import com.wimbli.WorldBorder.BorderData;
import com.wimbli.WorldBorder.Config;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class RTPCommand implements CommandExecutor {

    private static HashMap<Player, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (cooldowns.containsKey(commandSender)) {
            if (!(cooldowns.get(commandSender) <= Calendar.getInstance().getTimeInMillis())) {
                String timeLeft = ((cooldowns.get(commandSender) - Calendar.getInstance().getTimeInMillis()) / 1000 <= 60) ? (cooldowns.get(commandSender) - Calendar.getInstance().getTimeInMillis()) / 1000 + " seconds " : ((cooldowns.get(commandSender) - Calendar.getInstance().getTimeInMillis()) / 1000 / 60 + " minutes ");
                if(DarkInit.getPlugin().getConfig().getString("rtp.cooldown-message", null) != null && !DarkInit.getPlugin().getConfig().getString("rtp.cooldown-message").isEmpty())
                    commandSender.sendMessage(ChatColor.RED + timeLeft);
                return true;
            }
            cooldowns.remove(commandSender);
        }
        DarkInit.getPlugin().getServer().getScheduler().scheduleAsyncDelayedTask(DarkInit.getPlugin(), () -> {
            BorderData border = Config.Border(((Player) commandSender).getWorld().getName());
            if (border != null) {
                Location zero = new Location(((Player) commandSender).getWorld(), border.getX(), 0, border.getZ());
                Location rnd;
                int minSpawn = DarkInit.getPlugin().getConfig().getInt("rtp.min_spawn_blocks", 0);
                int minBorder = DarkInit.getPlugin().getConfig().getInt("rtp.min_border_blocks", 0);
                do {
                    rnd = zero.clone().add(-border.getRadiusX()+minBorder + new Random().nextInt(border.getRadiusX() * 2)-minBorder, 5 + new Random().nextInt(254), -border.getRadiusZ()+minBorder + new Random().nextInt(border.getRadiusZ() * 2)-minBorder);
                    if(!border.insideBorder(rnd)) {
                        rnd = null;
                        continue;
                    }
                    if (!(DarkInit.getPlugin().getConfig().getBoolean("rtp.water", false)) && rnd.getBlock().getType() == Material.WATER) {
                        rnd = null;
                        continue;
                    }
                    /*int minSpawn = DarkInit.getPlugin().getConfig().getInt("rtp.min_spawn_blocks", 0);
                    int minBorder = DarkInit.getPlugin().getConfig().getInt("rtp.min_border_blocks", 0);
                    if (minSpawn != 0 && !((rnd.getBlockZ() < (rnd.getWorld().getSpawnLocation().getBlockZ() - minSpawn) ||
                            rnd.getBlockZ() > (rnd.getWorld().getSpawnLocation().getBlockZ() + minSpawn)) &&
                            (rnd.getBlockX() < (rnd.getWorld().getSpawnLocation().getBlockX() - minSpawn) ||
                                    rnd.getBlockX() > (rnd.getWorld().getSpawnLocation().getBlockX() + minSpawn)))) {
                        DarkInit.getPlugin().getServer().broadcastMessage("Rnd Block Spawn");
                        rnd = null;
                        continue;
                    }
                    if (minBorder != 0 && !((rnd.getBlockZ() + minBorder < border.getRadiusZ() ||
                            rnd.getBlockZ() - minBorder > border.getRadiusZ()) &&
                            (rnd.getBlockX() + minBorder < border.getRadiusX() ||
                                    rnd.getBlockX() - minBorder > border.getRadiusX()))) {
                        DarkInit.getPlugin().getServer().broadcastMessage("Rnd Block Border");
                        rnd = null;
                        continue;
                    }*/
                    if (!(rnd.getBlock().getRelative(BlockFace.DOWN).getType().isSolid() && rnd.getBlock().getType() == Material.AIR && rnd.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR && rnd.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() == Material.AIR)) {
                        for(int x = 0; x < rnd.getWorld().getMaxHeight(); x++)  {
                            rnd.setY(x);
                            if (rnd.getBlock().getRelative(BlockFace.DOWN).getType().isSolid() && rnd.getBlock().getType() == Material.AIR && rnd.getBlock().getRelative(BlockFace.UP).getType() == Material.AIR && rnd.getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP).getType() == Material.AIR) {
                                break;
                            }
                        }
                    }

                } while (rnd == null);
                final Location choice = rnd;
                DarkInit.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(DarkInit.getPlugin(), () -> {
                    ((Player) commandSender).teleport(choice);
                });
                commandSender.sendMessage(ChatColor.LIGHT_PURPLE + "Teleported to random location!");
            }
        });

        cooldowns.put((Player) commandSender, Calendar.getInstance().getTimeInMillis() + (DarkInit.getPlugin().getConfig().getLong("rtp.cooldown") * 60000));
        return true;
    }
}
