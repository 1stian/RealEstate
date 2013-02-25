package pro.homiecraft.config;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import pro.homiecraft.realEstate;

public class EstateConfig {
	static realEstate plugin;
    public EstateConfig(realEstate plugin) {
    	EstateConfig.plugin = plugin;
    }
   
    public static FileConfiguration customConfig = null;
    public static File customConfigFile = null;
   
    public static void reloadEstateConfig(String Estate) {
            
            customConfigFile = new File(realEstate.pluginST.getDataFolder() + "/data/" + Estate + ".yml");
            
            customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
           
            InputStream defConfigStream = realEstate.pluginST.getResource("/data/" + Estate + ".yml");
            if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                    customConfig.setDefaults(defConfig);
            }
    }
   
    public static FileConfiguration getEstateConfig(String spawn) {
            if (customConfig == null) {
                    reloadEstateConfig(spawn);
            }
            return customConfig;
    }
   
    public static void saveEstateConfig(String spawn) {
            if (customConfig == null || customConfigFile == null) {
                    return;
            }
           
            try {
                    customConfig.save(customConfigFile);
            } catch (IOException e) {
                    Logger.getLogger("Minecraft").severe("Could not save " + spawn);
            }
    }
}
