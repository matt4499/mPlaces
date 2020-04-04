package com.github.matt4499.mplaces;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class GlobalChatHandler implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(sender instanceof Player) {
			StringBuilder builder = new StringBuilder();
			for(String s : args) {
			    builder.append(s + " ");
			}
			String str = builder.toString();
			if(str == null) {
				sender.sendMessage(ChatColor.RED + "[Error] You did not specify a message to send globally");
				return true;
			} else {
				PermissionUser user = PermissionsEx.getUser((Player) sender);
				String ColoredPrefix = ChatColor.translateAlternateColorCodes('&', user.getPrefix());
			    String ColoredMessage = ChatColor.translateAlternateColorCodes('&', str);
				if(str.matches("\b([n].[g][g][e][r]|nig|n.g.e.r.)\b")) { 
					((Player) sender).kickPlayer("do not say the n word"); 
					return true; 
				}
				DiscordWebhook webhook = new DiscordWebhook("removed discord webhook link");
			    webhook.setContent(ChatColor.stripColor(str));
			    webhook.setAvatarUrl("https://crafatar.com/avatars/" + user.getPlayer().getUniqueId());
			    webhook.setUsername("[GLOBAL] " + ChatColor.stripColor(user.getPrefix()) + user.getPlayer().getDisplayName());
			    try {
					webhook.execute();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if(user.has("chat.color")) {
				Bukkit.broadcastMessage(ChatColor.BLUE + "[Global] " + ColoredPrefix + ChatColor.GRAY + user.getName() + ChatColor.GRAY + " > " + ColoredMessage);
				return true;
				} else {
				Bukkit.broadcastMessage(ChatColor.BLUE + "[Global] " + ColoredPrefix + ChatColor.GRAY + user.getName() + ChatColor.GRAY + " > " + str);
				return true;
				}
				
			}
		}
		return false;
	}

}
