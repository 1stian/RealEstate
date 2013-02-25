package pro.homiecraft;

import java.util.logging.Logger;
import java.sql.*;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class realEstate extends JavaPlugin {
	public static realEstate pluginST;
	public realEstate plugin;
	public Logger log = Logger.getLogger("Minecraft");
	public static Permission perms = null;
	public static Economy econ = null;
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public void onDisable(){
		
	}
	
	public void onEnable() {
		setupMysql();
		
		PluginManager pm = getServer().getPluginManager();
			pm.registerEvents(new SignListn(), this);
		
		loadConfiguration();
		realEstate.pluginST = this;
		setupPermissions();
		setupEconomy();
		
		testGetdata();
	}
	
	public void loadConfiguration() {
		if(!getDataFolder().exists()){
			getDataFolder().mkdir();
		}		
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		this.reloadConfig();
	}
	
	public void setupMysql(){
		String host = this.getConfig().getString("RealEstate.mysql.host");
		String db = this.getConfig().getString("RealEstate.mysql.db");
		String user = this.getConfig().getString("RealEstate.mysql.user");
		String pw = this.getConfig().getString("RealEstate.mysql.pw");
		String port = this.getConfig().getString("RealEstate.mysql.port");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pw);
			st = con.createStatement();
			
		}catch(Exception ex){
			log.info("RealEstate mysql error: " + ex);
		}
	}
	
	public void testGetdata(){
		try {
			String query = "select * from info";
			rs = st.executeQuery(query);
			while(rs.next()){
				String name = rs.getString("name");
				String age = rs.getString("age");
				log.info("Test from mysql: " + name + " " + age);
			}
		}catch(Exception ex){
			
		}
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
