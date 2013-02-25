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
		
		makingDbready();
		//testGetdata();
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
		log.info("[RealEstate] Connection to MySql Database");
		String host = this.getConfig().getString("RealEstate.mysql.host");
		String db = this.getConfig().getString("RealEstate.mysql.db");
		String user = this.getConfig().getString("RealEstate.mysql.user");
		String pw = this.getConfig().getString("RealEstate.mysql.pw");
		String port = this.getConfig().getString("RealEstate.mysql.port");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pw);
			st = con.createStatement();
			log.info("[RealEstate] MySql Database connected!");
		}catch(Exception ex){
			log.info("[RealEstate] mysql error: " + ex);
		}
	}
	
	public void makingDbready() {
		String sql = "CREATE TABLE IF NOT EXISTS Estates(id INTEGER not NULL AUTO_INCREMENT,"+
					"estate VARCHAR(255),"+
					"price INTEGER,"+
					"location TEXT,"+
					"owner VARCHAR(120),"+
					"agent VARCHAR(120),"+
					"PRIMARY KEY (id))";
		try {
			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.info("[RealEstate] failed to create tables.. Check MySql settings!");
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
