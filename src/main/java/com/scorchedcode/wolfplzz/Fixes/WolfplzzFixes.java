package com.scorchedcode.wolfplzz.Fixes;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import com.scorchedcode.wolfplzz.Fixes.commands.DeathLocationCommand;
import com.scorchedcode.wolfplzz.Fixes.commands.MaintenanceCommand;
import com.scorchedcode.wolfplzz.Fixes.commands.RTPCommand;
import com.scorchedcode.wolfplzz.Fixes.commands.SocialCommands;
import com.wimbli.WorldBorder.BorderData;
import com.wimbli.WorldBorder.Config;
import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class WolfplzzFixes extends JavaPlugin {

    public static boolean MAINTENANCE_MODE = false;

    @Override
    public void onDisable() {
        if(MAINTENANCE_MODE) {
            File persist = new File(getDataFolder().getAbsolutePath() + File.separatorChar + "dat.yml");
            try {
                persist.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEnable() {
        new DarkInit(this);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new FixesListener(), this);
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        getCommand("deathlocation").setExecutor(new DeathLocationCommand());
        getCommand("rtp").setExecutor(new RTPCommand());
        getCommand("discord").setExecutor(new SocialCommands());
        getCommand("twitch").setExecutor(new SocialCommands());
        getCommand("website").setExecutor(new SocialCommands());
        getCommand("youtube").setExecutor(new SocialCommands());
        getCommand("teamspeak").setExecutor(new SocialCommands());
        File persist = new File(getDataFolder().getAbsolutePath() + File.separatorChar + "dat.yml");
        if(persist.exists()) {
            MAINTENANCE_MODE = true;
            persist.delete();
        }
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL,
                Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
            public void onPacketSending(PacketEvent event) {
                onPing(event.getPacket().getServerPings().read(0));
            }
        });
    }

    private void onPing(WrappedServerPing ping) {
        if(MAINTENANCE_MODE) {
            ping.setVersionName("Maintenance");
            ping.setVersionProtocol(999);
            ping.setMotD(getConfig().getString("maintenance-mode.motd"));
        }
    }
}
