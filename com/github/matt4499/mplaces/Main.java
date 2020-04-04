package com.github.matt4499.mplaces;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		this.getCommand("placecreate").setExecutor(new WorldCreatorCommand());
		this.getCommand("placedelete").setExecutor(new WorldDeleteCommand());
		this.getCommand("visit").setExecutor(new WorldVisitCommand());
		this.getCommand("g").setExecutor(new GlobalChatHandler());
		this.getCommand("gban").setExecutor(new GlobalBanCommand());
		this.getServer().getPluginManager().registerEvents(this, this);
		this.getServer().getPluginManager().registerEvents(new PlaceController(), this);
		this.getLogger().log(Level.INFO, "[mPlaces] Enabled");
		DiscordWebhook webhook = new DiscordWebhook("[removed webhook link]");
	    webhook.setContent("***Server is now online***");
	    webhook.setAvatarUrl("https://crafatar.com/avatars/f78a4d8d-d51b-4b39-98a3-230f2de0c670");
	    webhook.setUsername("[Server]");
	    try {
			webhook.execute();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	@Override
	public void onDisable() {
		DiscordWebhook webhook = new DiscordWebhook("[removed webhook link] ");
	    webhook.setContent("***Server is now offline***");
	    webhook.setAvatarUrl("https://crafatar.com/avatars/f78a4d8d-d51b-4b39-98a3-230f2de0c670");
	    webhook.setUsername("[Server]");
	    try {
			webhook.execute();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		this.getLogger().log(Level.INFO, "[mPlaces] Disabled");
	}
	@EventHandler
	public void SpawnOnJoin(PlayerJoinEvent e) {
		World LobbyWorld = Bukkit.getWorld("lobby");
        Location LobbySpawn = new Location(LobbyWorld, 0.5, 103.0, 0.5);
        e.getPlayer().teleport(LobbySpawn);
        MUtils.SendMsg(e.getPlayer(), ChatColor.GREEN + "Welcome back, " + e.getPlayer().getName() + " !");
        DiscordWebhook webhook = new DiscordWebhook("[removed webhook link]");
	    webhook.setContent("**" + e.getPlayer().getDisplayName() + "** has joined");
	    webhook.setAvatarUrl("https://crafatar.com/avatars/f78a4d8d-d51b-4b39-98a3-230f2de0c670");
	    webhook.setUsername("[Server]");
	    try {
			webhook.execute();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}
	@EventHandler
	public void LogoutMethod(PlayerQuitEvent e) {
		 DiscordWebhook webhook = new DiscordWebhook("[removed webhook link]");
		    webhook.setContent("**" + e.getPlayer().getDisplayName() + "** has disconnected");
		    webhook.setAvatarUrl("https://crafatar.com/avatars/f78a4d8d-d51b-4b39-98a3-230f2de0c670");
		    webhook.setUsername("[Server]");
		    try {
				webhook.execute();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
	}

}
