package com.scorchedcode.wolfplzz.Fixes.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MaintenanceEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();
    private boolean ismodeon = false;

    public MaintenanceEvent(boolean ismodeon) {
        this.ismodeon = ismodeon;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public boolean isMaintenanceEnabled() {
        return ismodeon;
    }


}
