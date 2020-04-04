package com.github.matt4499.mplaces;

import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceController implements Listener {
	@EventHandler
	public void OnPlaceJoin(PlaceJoinEvent e) {
		Player joiner = e.getPlayer();
		World place = e.getWorld();
		if(!(place.getName() == joiner.getDisplayName())) {
			joiner.setGameMode(GameMode.ADVENTURE);
		}
		if((place.getName() == joiner.getDisplayName())) {
			joiner.setGameMode(GameMode.CREATIVE);
		}
	}
	@EventHandler
	public void OnBlockPlace(BlockPlaceEvent e) {
		if(e.getPlayer().getDisplayName() != e.getBlock().getWorld().getName()) {
			e.setCancelled(true);
			MUtils.SendMsg(e.getPlayer(), "You do not have permission to build in this place.");
		}
		if(MUtils.IsBlockBanned(e.getBlock().getType())) {
			e.setCancelled(true);
			MUtils.SendMsg(e.getPlayer(), "That block is disabled.");
		}
	}
	@EventHandler
	public void OnBlockBreak(BlockBreakEvent e) {
		if(e.getPlayer().getDisplayName() != e.getBlock().getWorld().getName()) {
			e.setCancelled(true);
			MUtils.SendMsg(e.getPlayer(), "You do not have permission to build in this place.");
		}
	}
}
