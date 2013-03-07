package pro.homiecraft.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

import pro.homiecraft.realEstate;
import pro.homiecraft.config.EstateConfig;

public class cEstate implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		WorldGuardPlugin worldGuardPlugin = null;
		worldGuardPlugin = (WorldGuardPlugin) realEstate.pluginST.getServer().getPluginManager().getPlugin("WorldGuard");
		RegionManager regionManager = worldGuardPlugin.getRegionManager(player.getWorld());
		
		if (cmd.getName().equalsIgnoreCase("estate")){
			if(args.length < 1){
				sender.sendMessage("Plugin Name: " + realEstate.pluginST.getDescription().getName().toString());
				sender.sendMessage("Version: " + realEstate.pluginST.getDescription().getVersion().toString());
				sender.sendMessage("Author(s): " + realEstate.pluginST.getDescription().getAuthors().toString());
			}else{
				if(args[0].equalsIgnoreCase("addowner")){
					if (realEstate.perms.has(sender, "estate.addowner")){
						if (args.length == 3){
							String estateName = args[1].toLowerCase();
							String getOwner = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".owner");
							String getAgent = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".player");
							String pName = player.getName();
							if(pName.equalsIgnoreCase(getAgent) || pName.equalsIgnoreCase(getOwner)){
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
										
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
										
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.addPlayer(args[2]);
										
								estateRegion.setOwners(estateOwner);
										
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									player.sendMessage("Error: " + e);
								}
							}else{
								sender.sendMessage("[RealEstate] You're not the owner of this Estate!");
							}
						}else{
							sender.sendMessage("Usage: /estate addowner estateName playerName");
						}
					}else{
						sender.sendMessage("You don't have access to addowner in plugin RealEstate!");
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("removeowner")){
					if (realEstate.perms.has(sender, "estate.removeowner")){
						if (args.length == 3){
							String estateName = args[1].toLowerCase();
							String getOwner = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".owner");
							String getAgent = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".player");
							String pName = player.getName();
							if(pName == getAgent || pName == getOwner){								
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
										
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
										
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.removePlayer(args[2]);
										
								estateRegion.setOwners(estateOwner);
										
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									player.sendMessage("Error: " + e);
								}
							}else{
								sender.sendMessage("[RealEstate] You're not the owner of this Estate!");
							}
								}else{
							sender.sendMessage("Usage: /estate removeowner estateName playerName");
						}
					}else{
						sender.sendMessage("You don't have access to removeowner in plugin RealEstate!");
					}
					return true;
				}
				
				if(args[0].equalsIgnoreCase("addfriend")){
					if (realEstate.perms.has(sender, "estate.addfriend")){
						if(args.length == 3){
							String pName = player.getName();
							//String[] estateInfo = getEstate.getEstateInfo(args[1]);
								
							String getOwner = EstateConfig.getEstateConfig("estates").getString("Estate." + args[1] + ".owner");
							String getAgent = EstateConfig.getEstateConfig("estates").getString("Estate." + args[1] + ".player");
								
							if(pName.equalsIgnoreCase(getOwner) || pName.equalsIgnoreCase(getAgent)){
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + args[1] + ".max");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + args[1] + ".max");
									
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(args[1], min, max);
									
								DefaultDomain addFriend = new DefaultDomain();
								addFriend.addPlayer(args[2]);
									
								estateRegion.setOwners(addFriend);
									
								try {
									sender.sendMessage("[RealEstate] You have added " + args[2] + "To your estate named: " + args[1]);
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								sender.sendMessage("[RealEstate] You're not the owner of this Estate!");
							}
						}else{
							sender.sendMessage("Usage: /es addfriend <estateName> <playerName>");
						}
					}else{
						sender.sendMessage("You don't have access to addfriend in plugin RealEstate!");
					}
				}
				
				if(args[0].equalsIgnoreCase("warp")){
					if (realEstate.perms.has(sender, "estate.warp")){
						if (!(args[1].equalsIgnoreCase(""))){
							double x = EstateConfig.getEstateConfig("estates").getDouble("Estate." + args[1] + ".x");
							double y = EstateConfig.getEstateConfig("estates").getDouble("Estate." + args[1] + ".y");
							double z = EstateConfig.getEstateConfig("estates").getDouble("Estate." + args[1] + ".z");
							
							World scWorld = (World) EstateConfig.getEstateConfig("estates").get("Estate." + args[1] + ".world");
							
							Location estateLoc = new Location(scWorld, x,y,z);
							
							player.teleport(estateLoc);
						}else{
							player.sendMessage("Usage: /es warp EstateName");
						}
					}
				}
			}
			return true;
		}
		return false;
	}
}
