package com.cainkilgore;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class DragonText extends JavaPlugin {
	
	static EnderDragon dragon = null;
	static BukkitTask pauseDragon = null;
	static Location dragonLocation = null;
	static DragonText plugin;
	
	public void onEnable() {
		plugin = this;
		System.out.println("[DragonText] DragonText API has been enabled.");
		Bukkit.getServer().getPluginCommand("dtt").setExecutor(new DttCommand());
		Bukkit.getServer().getPluginCommand("dt").setExecutor(new DtCommand());
		Bukkit.getServer().getPluginCommand("dtremove").setExecutor(new DtRemoveCommand());
		
	}
	
	public void onDisable() {
		System.out.println("[DragonText] DragonText API has been disabled.");
		if(dragonLocation != null) {
			dragon.remove();
			dragon.setHealth(0);
		}
	}
	
	public static void addText(String text, Location location, int period) {
		dragonLocation = location;
		if(pauseDragon == null) {
			dragon = location.getWorld().spawn(location, EnderDragon.class);
			dragon.setCustomName(text.replace("&", "§"));
			pauseDragon();
			
			Bukkit.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
				public void run() {
					removeDragon();
				}
			}, period * 20);
			
		} else {
			removeDragon();
		}
	}
	
	public static void addText(String text, Location location) {
		dragonLocation = location;
		if(pauseDragon == null) {
			dragon = location.getWorld().spawn(location, EnderDragon.class);
			dragon.setCustomName(text.replace("&", "§"));
			pauseDragon();
		} else {
			removeDragon();
		}
	}
	
	public static void removeDragon() {
		if(dragon != null) {
			dragon.remove();
		}
		
		if(pauseDragon != null) {
			pauseDragon.cancel();
			pauseDragon = null;
		}
		
		if(dragonLocation != null) {
			dragonLocation = null;
		}
	}
	
	public static void pauseDragon() {
		pauseDragon = Bukkit.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
			public void run() {
				dragon.teleport(dragonLocation);
			}
		}, 5L, 5L);
	}
	
	public static boolean isDragonExisting() {
		if(dragonLocation != null) {
			return true;
		} else {
			return false;
		}
	}

}
