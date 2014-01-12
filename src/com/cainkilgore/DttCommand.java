package com.cainkilgore;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DttCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("dtt")) {
			if(!(s instanceof Player)) {
				return false;
			}
			
			Player player = (Player) s;
			
			if(!player.hasPermission("dragontext.use")) {
				player.sendMessage(ChatColor.RED + "You cannot use this command.");
				return true;
			}
			
			if(args.length < 2) {
				player.sendMessage(ChatColor.RED + "You have entered invalid arguments.");
				return false;
			}
			
			StringBuilder x = new StringBuilder();
			for(int i = 1; i < args.length; i++) {
				x.append(args[i] + " ");
			}
			
			if(DragonText.isDragonExisting()) {
				player.sendMessage(ChatColor.RED + "There is already a dragon on this world.");
				player.sendMessage(ChatColor.RED + "Please type /dtremove to remove it before continuing.");
				return true;
			}
			
			try {
				if(x.toString().length() > 50) {
					player.sendMessage(ChatColor.RED + "You can't enter a string above 50 characters..");
					return true;
				}
				Location below = new Location(player.getWorld(), player.getWorld().getSpawnLocation().getX(), -20, player.getWorld().getSpawnLocation().getZ());
				DragonText.addText(x.toString(), below, Integer.parseInt(args[0]));
				player.sendMessage(ChatColor.LIGHT_PURPLE + "DragonText is now set and is ready for viewing.");
				player.sendMessage(ChatColor.LIGHT_PURPLE + "Text Entered: " + x.toString().replace("&", "§"));
				player.sendMessage(ChatColor.LIGHT_PURPLE + "This message will be viewable for " + args[0] + " seconds.");
			} catch (NumberFormatException e) {
				player.sendMessage(ChatColor.RED + "You have entered an invalid period.");
				System.out.println(e.getMessage());
			}
		}
		return true;
	}

}
