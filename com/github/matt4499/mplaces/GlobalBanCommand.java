package com.github.matt4499.mplaces;

import javax.annotation.Nonnull;

import org.bukkit.BanList.Type;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalBanCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@Nonnull CommandSender sender, @Nonnull Command cmd, @Nonnull String arg2, @Nonnull String[] args) {
		Player p = ((Player) sender);
		if(!MUtils.IsStaff(sender)) {
			MUtils.SendMsg(p, "You do not have permission.");
			return true;
		} else {
			if(args[0].isEmpty()) { 
				MUtils.SendMsg(p, "You did not specify a player");
				return true;
			}
			OfflinePlayer target = Bukkit.getPlayer(args[0]);
			String reason = MUtils.BuildReason(args);
			Player banner = p;
			Bukkit.getServer().getBanList(Type.NAME).addBan(
					target.getName(),
					reason,
					null,
					banner.getName()
			);
		}
		return false;
	}

}
