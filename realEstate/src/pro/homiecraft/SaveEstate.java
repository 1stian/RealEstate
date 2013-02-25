package pro.homiecraft;

import org.bukkit.entity.Player;

import com.sk89q.worldedit.BlockVector;

import pro.homiecraft.config.EstateConfig;

public class SaveEstate {
	
	public static void estateSave(BlockVector min, BlockVector max, String name, String owner, Player player, String price){
		boolean saveType = EstateConfig.getEstateConfig("estates").getBoolean("RealEstate.mysql.Use mysql", false);
		
		if (saveType == true){
			
		}else if(saveType == false){
			
		}
	}
}
