package com.github.matt4499.mplaces;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.matt4499.mplaces.MUtils;

public class WorldDeleteCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2,String[] args) {
		if(!MUtils.IsStaff(sender)) {
			sender.sendMessage("no permission");
			return true;
		} else {
			World delete = Bukkit.getWorld(args[0]);
			if(!delete.getPlayers().isEmpty()) {
		        for(Player p : delete.getPlayers()){
		            World LobbyWorld = Bukkit.getWorld("lobby");
		            Location LobbySpawn = new Location(LobbyWorld, LobbyWorld.getSpawnLocation().getBlockX(), LobbyWorld.getSpawnLocation().getBlockY() + 1, LobbyWorld.getSpawnLocation().getBlockZ());
		            p.teleport(LobbySpawn);
		            p.sendMessage(ChatColor.RED + "[mPlace] This place is being deleted/reset, you are being moved to the Lobby.");
		        }
			}
				File deleteFolder = delete.getWorldFolder();
				Bukkit.getServer().unloadWorld(delete, true);
				if(deleteWorld(deleteFolder)) {
					Bukkit.getServer().broadcastMessage(ChatColor.RED + "[mPlace] The world " + ChatColor.YELLOW + args[0] + ChatColor.RED + " has just been deleted.");
					return true;
				}		    
		}
		return false;
	}
	
	public boolean deleteWorld(File path) {
	      if(path.exists()) {
	          File files[] = path.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  deleteWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	      return(path.delete());
	}

}
