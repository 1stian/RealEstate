package pro.homiecraft;

import pro.homiecraft.config.EstateConfig;

public class SaveEstate {
	
	public void estateSave(){
		boolean saveType = EstateConfig.getEstateConfig("estates").getBoolean("RealEstate.mysql.Use mysql", false);
		
		if (saveType == true){
			
		}else if(saveType == false){
			
		}
	}
}
