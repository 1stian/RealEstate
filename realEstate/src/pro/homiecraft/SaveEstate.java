package pro.homiecraft;

import org.bukkit.World;

import pro.homiecraft.config.EstateConfig;

public class SaveEstate {
	
	public static void estateSave(String sMin, String sMax, String name, String owner, String pName, String price, World world, double x, double y, double z){
		boolean saveType = EstateConfig.getEstateConfig("estates").getBoolean("RealEstate.mysql.Use mysql", false);
		
		if (saveType == true){
			
		}else if(saveType == false){
			EstateConfig.reloadEstateConfig("estates");
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".owner", owner);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".player", pName);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".price", price);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".min", sMin);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".max", sMax);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".world", world);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".x", x);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".y", y);
			EstateConfig.getEstateConfig("estates").set("Estate." + name + ".z", z);
			EstateConfig.saveEstateConfig("estates");
			EstateConfig.reloadEstateConfig("estates");
		}
	}
}
