package pro.homiecraft;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import com.sk89q.worldedit.regions.Region;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;

public class SignListn implements Listener {
	@EventHandler
	public void signCreate(SignChangeEvent event){
		Player player = event.getPlayer();
		String pName = player.getName();
		Block block = event.getBlock();
		
		WorldEditPlugin worldEditPlugin = null;
		worldEditPlugin = (WorldEditPlugin) realEstate.pluginST.getServer().getPluginManager().getPlugin("WorldEdit");
		Selection sel = worldEditPlugin.getSelection(player);
		
		WorldGuardPlugin worldGuardPlugin = null;
		worldGuardPlugin = (WorldGuardPlugin) realEstate.pluginST.getServer().getPluginManager().getPlugin("WorldGuard");
		RegionManager regionManager = worldGuardPlugin.getRegionManager(player.getWorld());
		
		if (realEstate.perms.has(player, "estate.create")){
			if (!(sel == null)){
				String[] line = event.getLines();
						
				if (line[0].equalsIgnoreCase("[estate]")){
					if (event.getLine(2) == ""){
						player.sendMessage("[RealEstate] please specify a price on the third line!");
					}else{
						if(event.getLine(3) == ""){
							player.sendMessage("[RealEstate] please specify a name on the fourth line!");	
						}else{
							player.sendMessage("[RealEstate] You have created an estate sign!! testy testy :)");
							
							event.setLine(0, "[Estate]");
							event.setLine(1, "No Owner");
							block.getState().update();
											
							String owner = event.getLine(1);
							String price = event.getLine(2);
							String name = event.getLine(3);
							
							if (sel instanceof CuboidSelection) {
								BlockVector min = sel.getNativeMinimumPoint().toBlockVector();
								BlockVector max = sel.getNativeMaximumPoint().toBlockVector();
								
								
								String sMin = min.toString();
								String sMax = max.toString();
								
								
								
								SaveEstate.estateSave(sMin, sMax, name, owner, pName, price);
							}
						}
					}
				}else{
					player.sendMessage("You don't have permissions to create estates!");
				}
			}else{
				player.sendMessage("You need to have a region selected!");
			}
		}
	}
}
