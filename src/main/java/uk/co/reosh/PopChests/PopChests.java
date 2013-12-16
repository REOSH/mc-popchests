package uk.co.reosh.lotr.PopChests;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.reosh.lotr.PopChests.Events.PlayerInteract;

public class PopChests extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static List<String> worlds;
	public static Boolean allworlds = false;
	
	public void onDisable() {
		PluginDescriptionFile pdfile = this.getDescription();
		this.logger.info(pdfile.getName() + " has been disabled!");
	}
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
	    this.saveDefaultConfig();
		pm.registerEvents(new PlayerInteract(), this);
		PluginDescriptionFile pdfile = this.getDescription();
		this.logger.info(pdfile.getName() + " " + pdfile.getVersion() + " has been enabled!");
		
		worlds = this.getConfig().getStringList("enabled-worlds");
		allworlds = this.getConfig().getBoolean("all-worlds");
	}
}
		
	
