package com.github.matt4499.mplaces;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlaceJoinEvent extends Event implements Cancellable {
	 private static final HandlerList handlers = new HandlerList();
	    private Player player;
	    private World world;
	    private Boolean cancelled = false;

	    public PlaceJoinEvent(Player player1, World place1) {
	        player = player1;
	        world = place1;
	    }

	    public Player getPlayer() {
	        return player;
	    }
	    public World getWorld() {
	    	return world;
	    }

	    public boolean isCancelled() {
	        return cancelled;
	    }

	    public void setCancelled(boolean cancel) {
	        cancelled = cancel;
	    }

	    public HandlerList getHandlers() {
	        return handlers;
	    }

	    public static HandlerList getHandlerList() {
	        return handlers;
	    }
}
