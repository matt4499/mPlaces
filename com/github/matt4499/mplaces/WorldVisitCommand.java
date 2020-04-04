package com.github.matt4499.mplaces;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class WorldVisitCommand implements CommandExecutor {
    private World world;
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player)) {
        	if((args[0].contains("lobby_the_end"))) { sender.sendMessage(ChatColor.RED + "no"); return true; }
            File file = new File(Bukkit.getServer().getWorldContainer(), args[0]);
            if (!file.exists()) { // world does not exist
                sender.sendMessage("[mPlace] That place does not exist.");
                return true;
            }
            if(((Player) sender).getWorld().getName() == args[0]) {
            	MUtils.SendMsg((Player) sender, "You are already in that world!");
            	return true;
            }
            if (Bukkit.getWorld(args[0]) == null && file.getName() != sender.getName()) { // if the world is not loaded, and their name does not equal place name
            	if(!(sender.isOp())) {
            		MUtils.SendMsg((Player) sender, "That player is not in their place!");
                return true;
            	}
            }
            WorldCreator wc = new WorldCreator(args[0]);
            this.world = Bukkit.getServer().createWorld(wc);
            Location PlaceToVisitSpawn = new Location(this.world, this.world.getSpawnLocation().getBlockX(), this.world.getSpawnLocation().getBlockY() + 1, this.world.getSpawnLocation().getBlockZ());
            sender.sendMessage("Attempting to send you to that place...");
            ((Player) sender).teleport(PlaceToVisitSpawn);
            Player p2 = ((Player) sender);
            PlaceJoinEvent pj = new PlaceJoinEvent(p2, this.world);
	        Bukkit.getServer().getPluginManager().callEvent(pj);
            PermissionUser user = PermissionsEx.getUser((Player) sender);
            String ColoredPrefix = ChatColor.translateAlternateColorCodes('&', user.getPrefix());
            if(!(this.world.getPlayers().isEmpty())) {
		        for(Player p : this.world.getPlayers()){
		            p.sendMessage(ColoredPrefix + user.getName() + ChatColor.GRAY +  " has joined the place.");
			}
            return true;
        }
        return true;
    }
        return false;
}
}