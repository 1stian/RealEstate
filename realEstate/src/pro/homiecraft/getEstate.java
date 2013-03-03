package pro.homiecraft;

import pro.homiecraft.config.EstateConfig;

public class getEstate {
	public static String[] getEstateInfo(String name){
		String owner = EstateConfig.getEstateConfig("estates").getString("Estate." + name + ".owner");
		String agent = EstateConfig.getEstateConfig("estates").getString("Estate." + name + ".player");
		String price = EstateConfig.getEstateConfig("estates").getString("Estate." + name + ".price");
		String min = EstateConfig.getEstateConfig("estates").getString("Estate." + name + ".min");
		String max = EstateConfig.getEstateConfig("estates").getString("Estate." + name + ".max");
		double x = EstateConfig.getEstateConfig("estates").getDouble("Estate." + name + ".x");
		double y = EstateConfig.getEstateConfig("estates").getDouble("Estate." + name + ".y");
		double z = EstateConfig.getEstateConfig("estates").getDouble("Estate." + name + ".z");
		
		String xS = Double.toString(x);
		String yS = Double.toString(y);
		String zS = Double.toString(z);
		
		String[] estateInfo = {owner, agent, price, min, max, xS, yS, zS};
		
		return(estateInfo);
	}
}
