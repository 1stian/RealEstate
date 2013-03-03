package pro.homiecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import pro.homiecraft.realEstate;

public class cEstate implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("estate")){
			sender.sendMessage("Plugin Name: " + realEstate.pluginST.getDescription().getName().toString());
			sender.sendMessage("Version: " + realEstate.pluginST.getDescription().getVersion().toString());
			sender.sendMessage("Author(s): " + realEstate.pluginST.getDescription().getAuthors().toString());
			return true;
		}
		return false;
	}
}
