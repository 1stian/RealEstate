package pro.homiecraft;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import net.milkbowl.vault.permission.Permission;

public class realEstate extends JavaPlugin {
	public static realEstate pluginST;
	public realEstate plugin;
	public Logger log = Logger.getLogger("Minecraft");
	public static Permission perms = null;
	
	public void onDisable(){
		
	}
	
	public void onEnable() {
		
		PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new SignListn(), this);
		
		loadConfiguration();
		realEstate.pluginST = this;
		setupPermissions();
	}
	
	public void loadConfiguration() {
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}		
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.reloadConfig();
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}
