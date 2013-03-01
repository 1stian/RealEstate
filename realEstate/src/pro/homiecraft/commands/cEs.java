package pro.homiecraft.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pro.homiecraft.realEstate;
import pro.homiecraft.config.EstateConfig;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

public class cEs implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		WorldGuardPlugin worldGuardPlugin = null;
		worldGuardPlugin = (WorldGuardPlugin) realEstate.pluginST.getServer().getPluginManager().getPlugin("WorldGuard");
		RegionManager regionManager = worldGuardPlugin.getRegionManager(player.getWorld());
		
		if(args[0].equalsIgnoreCase("addowner")){
			if (realEstate.perms.has(sender, "estate.addowner")){
				if (!(args[1].equalsIgnoreCase(""))){
					String estateName = args[1];
					String getOwner = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".owner");
					if (getOwner == "No Owner"){
						String getAgent = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".player");
						String pName = player.getName();
						if(pName == getAgent){
							if (!(args[2].equalsIgnoreCase(""))){								
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
								
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
								
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.addPlayer(args[2]);
								
								estateRegion.setOwners(estateOwner);
								
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
								}
							}else{
								sender.sendMessage("Usage: /estate addowner estateName playerName");
							}
						}
						
					}else{
						String pName = player.getName();
						if(pName == getOwner){
							if (!(args[2].equalsIgnoreCase(""))){								
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
								
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
								
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.addPlayer(args[2]);
								
								estateRegion.setOwners(estateOwner);
								
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
								}
							}else{
								sender.sendMessage("Usage: /estate addowner estateName playerName");
							}
						}
					}
				}else{
					sender.sendMessage("Usage: /estate addowner estateName playerName");
				}
			}else{
				sender.sendMessage("You don't have access to addowner in plugin RealEstate!");
			}
		}else{
			
		}
		
		if(args[0].equalsIgnoreCase("removeowner")){
			if (realEstate.perms.has(sender, "estate.removeowner")){
				if (!(args[1].equalsIgnoreCase(""))){
					String estateName = args[1];
					String getOwner = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".owner");
					if (getOwner == "No Owner"){
						String getAgent = EstateConfig.getEstateConfig("estates").getString("Estate." + estateName + ".player");
						String pName = player.getName();
						if(pName == getAgent){
							if (!(args[2].equalsIgnoreCase(""))){								
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
								
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
								
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.removePlayer(args[2]);
								
								estateRegion.setOwners(estateOwner);
								
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
								}
							}else{
								sender.sendMessage("Usage: /estate removeowner estateName playerName");
							}
						}
						
					}else{
						String pName = player.getName();
						if(pName == getOwner){
							if (!(args[2].equalsIgnoreCase(""))){								
								BlockVector min = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".min");
								BlockVector max = (BlockVector) EstateConfig.getEstateConfig("estates").get("Estate." + estateName + ".max");
								
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(estateName, min, max);
								
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.removePlayer(args[2]);
								
								estateRegion.setOwners(estateOwner);
								
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
								}
							}else{
								sender.sendMessage("Usage: /estate removeowner estateName playerName");
							}
						}
					}
				}else{
					sender.sendMessage("Usage: /estate removeowner estateName playerName");
				}
			}else{
				sender.sendMessage("You don't have access to removeowner in plugin RealEstate!");
			}
		}else{
			sender.sendMessage("Usage: /estate removeowner estateName playerName");
		}
		return false;
	}
}
