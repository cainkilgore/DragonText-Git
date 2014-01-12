package com.cainkilgore;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DtRemoveCommand implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args) {
		if(l.equalsIgnoreCase("dtremove")) {
			if(!(s instanceof Player)) {
				return false;
			}
			
			Player player = (Player) s;
			
			if(!player.hasPermission("dragontext.use")) {
				player.sendMessage(ChatColor.RED + "You cannot use this command.");
				return true;
			}
			
			if(!DragonText.isDragonExisting()) {
				player.sendMessage(ChatColor.RED + "There is no dragon on this world right now.");
				return true;
			}
			
			DragonText.removeDragon();
			player.sendMessage(ChatColor.LIGHT_PURPLE + "The dragon has been removed.");
		}
		return true;
	}

}
