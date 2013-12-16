package uk.co.reosh.PopChests;

import java.util.List;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import uk.co.reosh.PopChests.Events.PlayerInteract;

public class PopChests extends JavaPlugin {
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public static List<String> worlds;
	public static Boolean allworlds = false;
	
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
	    this.saveDefaultConfig();
		pm.registerEvents(new PlayerInteract(), this);
		PluginDescriptionFile pdfile = this.getDescription();
		
		worlds = this.getConfig().getStringList("enabled-worlds");
		allworlds = this.getConfig().getBoolean("all-worlds");
		
		if (allworlds) {
			this.logger.info("Enabling " + pdfile.getName() + " " + pdfile.getVersion() + " for all worlds!");
		} else {
			String enabledworlds = "";
			for (String w : worlds) { enabledworlds += w + " "; }
			
			this.logger.info("Enabling " + pdfile.getName() + " " + pdfile.getVersion() + " for worlds " + enabledworlds);
		}
	}
}
		
	
