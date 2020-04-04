package com.github.matt4499.mplaces;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class MUtils implements Listener {
	
	public static boolean OwnsWorld(Player player, World world) {
		String pname = player.getDisplayName();
		String wname = world.getName();
		if(pname == wname) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean IsStaff(CommandSender sender) {
		Player player = ((Player) sender);
		PermissionUser user = PermissionsEx.getUser(player);
		if(user.has("group.owner") || user.inGroup("admin") || player.isOp() || (sender instanceof ConsoleCommandSender)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean IsAllowedColorChat(Player player) {
		PermissionUser user = PermissionsEx.getUser(player);
		if(IsStaff(player) || user.has("colorchat.use")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void SetWorldBorder(World world, Double size) {
		world.getWorldBorder().setSize(size);
	}
	
	public static void SendMsg(Player player, String string) {
		player.sendMessage(ChatColor.RED + "Server " + ChatColor.GRAY + "> " + ChatColor.RESET + string);
	}

	public static String BuildReason(String[] args) {
		final StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; ++i) {
            sb.append(args[i]);
            sb.append(" ");
        }
        String s = sb.toString().trim();
        s = s.replaceAll("\\\\n", "\n");
        if (s.isEmpty()) {
            return "No reason given.";
        }
        return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static Boolean IsBlockBanned(Material material1) {
		Material[] BlockedList = {Material.END_PORTAL, Material.END_PORTAL_FRAME, Material.END_CRYSTAL};
		return ArrayUtils.contains(BlockedList, material1);
	}
	public static Boolean IsWordBanned(String word) {
		String[] BlockedWordsList = {"nigger", "nig"};
		if(word.matches("\b([n].[g][g][e][r]|nig|n.g.e.r.)\b")) { 
			return true; 
		}
		return ArrayUtils.contains(BlockedWordsList, word);
	}
	
}
