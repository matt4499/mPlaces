package com.github.matt4499.mplaces;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.matt4499.mplaces.MUtils;

public class WorldCreatorCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player && MUtils.IsStaff(sender)) {
			if(args == null) { 
				sender.sendMessage("you did not specify a name of world to create.");
				return true;
			}
			if (Bukkit.getWorld(args[0]) != null) {
		        sender.sendMessage(ChatColor.RED + "Error: world with that name already exists");
		        return true;
		    }
			WorldCreator wc = new WorldCreator(args[0]);
			wc.environment(World.Environment.NORMAL);
			wc.generateStructures(false);
			wc.type(WorldType.FLAT);
			wc.hardcore(false);
			Bukkit.getServer().broadcastMessage("A new place is being generated. This may cause lag for ~10 seconds.");
			try {
		        World NewWorld = wc.createWorld();
		        NewWorld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
		        NewWorld.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
		        NewWorld.setGameRule(GameRule.DISABLE_RAIDS, true);
		        NewWorld.setGameRule(GameRule.DO_FIRE_TICK, false);
		        NewWorld.setGameRule(GameRule.DO_MOB_SPAWNING, false);
		        NewWorld.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
		        NewWorld.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
		        NewWorld.setGameRule(GameRule.DO_WEATHER_CYCLE, false);
		        NewWorld.setGameRule(GameRule.KEEP_INVENTORY, true);
		        NewWorld.setGameRule(GameRule.MOB_GRIEFING, false);
		        NewWorld.setGameRule(GameRule.SHOW_DEATH_MESSAGES, false);
		        NewWorld.setGameRule(GameRule.SPAWN_RADIUS, 1);
		        NewWorld.setSpawnLocation(0, 5, 0);
		        NewWorld.setDifficulty(Difficulty.PEACEFUL);
		        NewWorld.getWorldBorder().setCenter(0, 0);
		        NewWorld.getWorldBorder().setSize(75);
		        NewWorld.getWorldBorder().setDamageAmount(200);
		        NewWorld.getWorldBorder().setWarningTime(0);
		        NewWorld.getWorldBorder().setWarningDistance(0);
		        NewWorld.getWorldBorder().setWarningTime(0);
		        NewWorld.setTime(6000);
		        sender.sendMessage("World was successfully created!");
		        Location NewWorldLocation = new Location(NewWorld, NewWorld.getSpawnLocation().getX(), NewWorld.getSpawnLocation().getY(), NewWorld.getSpawnLocation().getZ());
		        Player p = ((Player) sender);
		        PlaceJoinEvent pj = new PlaceJoinEvent(p, NewWorld);
		        Bukkit.getServer().getPluginManager().callEvent(pj);
		        ((Player) sender).teleport(NewWorldLocation);
		        return true;
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }
			
		}
		sender.sendMessage(ChatColor.RED + "no permission");
		return false;
	}

}
