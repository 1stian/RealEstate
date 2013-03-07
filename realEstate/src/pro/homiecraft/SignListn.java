package pro.homiecraft;

import java.util.regex.Pattern;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.domains.DefaultDomain;
import com.sk89q.worldguard.protection.databases.ProtectionDatabaseException;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;

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
					String signLine2 = event.getLine(2);
					if (!(Pattern.matches("[a-zA-Z]+", signLine2) == false)){
						player.sendMessage("[RealEstate] please specify a price on the third line!");
					}else{
						if(line[3].equalsIgnoreCase("")){
							player.sendMessage("[RealEstate] please specify a name on the fourth line!");	
						}else{
							player.sendMessage("[RealEstate] You have created an estate sign!! testy testy :)");
							
							event.setLine(0, "[Estate]");
							
							if (!(event.getLine(1) == "[AdminPlot]")){
								event.setLine(1, "No Owner");
							}
							
							block.getState().update();
											
							String owner = event.getLine(1);
							String price = event.getLine(2);
							String name = event.getLine(3).toLowerCase();
							
							if (sel instanceof CuboidSelection) {								
								BlockVector min = sel.getNativeMinimumPoint().toBlockVector();
								BlockVector max = sel.getNativeMaximumPoint().toBlockVector();
								
								double bcY = min.getY();
								double MbcY = max.getY();
								
								double minbcY = 0;
								double maxbcY = 0;
						
								if (bcY == MbcY){
									minbcY = bcY + -10;
									maxbcY = MbcY + 50;
								}else if(bcY < MbcY){
									minbcY = bcY + -10;
									maxbcY = MbcY + 50;
								}else if(bcY > MbcY){
									maxbcY = MbcY + -10;
									minbcY = bcY + 50;
								}
								
								double bcX = min.getX();
								double bcZ = min.getZ();
								
								double MbcX = max.getX();
								double MbcZ = max.getZ();
								
								BlockVector fMin = new BlockVector(bcX, minbcY, bcZ);
								BlockVector fMax = new BlockVector(MbcX, maxbcY, MbcZ);
								
								String sMin = fMin.toString();
								String sMax = fMax.toString();
								
								double x = event.getBlock().getX();
								double y = event.getBlock().getY();
								double z = event.getBlock().getZ();
								
								String cWorld = event.getBlock().getWorld().getName();
								
								SaveEstate.estateSave(sMin, sMax, name, owner, pName, price, cWorld, x, y, z);
								
								ProtectedCuboidRegion estateRegion = new ProtectedCuboidRegion(name, fMin, fMax);
								regionManager.addRegion(estateRegion);
								
								DefaultDomain estateOwner = new DefaultDomain();
								estateOwner.addPlayer(player.getName());
								
								estateRegion.setOwners(estateOwner);
								
								try {
									regionManager.save();
								} catch (ProtectionDatabaseException e) {
									// TODO Auto-generated catch block
								}
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
