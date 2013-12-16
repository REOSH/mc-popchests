package uk.co.reosh.lotr.PopChests.Events;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import uk.co.reosh.lotr.PopChests.PopChests;

public class PlayerInteract implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void ExplosionCreation(PlayerInteractEvent event){
    	if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
	    	if(event.getClickedBlock().getType() == Material.CHEST && event.getPlayer().getGameMode() != GameMode.CREATIVE) {
	    		if(PopChests.allworlds) {
		    		chestInteract(event);
	    		} else {
	    			if(PopChests.worlds.contains(event.getPlayer().getWorld().getName())) {
			    		chestInteract(event);
		    		}
	    		}
	    	}
    	}	
    }
    
    public void chestInteract(PlayerInteractEvent event) {
    	event.setCancelled(true);
    	Chest chest = (Chest) event.getClickedBlock().getState();
    	Player player = event.getPlayer();
    	Inventory inv = chest.getInventory();

    	for(ItemStack item : inv) {
    	        if(item != null)
    	        	chest.getWorld().dropItem(chest.getLocation(), item);
    	}

    	chest.getInventory().clear();
    	player.playEffect(chest.getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
    	player.playEffect(chest.getLocation().add(new Vector(0,1.5,0)), Effect.MOBSPAWNER_FLAMES, 10);
    	player.playEffect(chest.getLocation().add(new Vector(0,0,1.5)), Effect.MOBSPAWNER_FLAMES, 10);
    	player.playEffect(chest.getLocation().add(new Vector(1.5,0,0)), Effect.MOBSPAWNER_FLAMES, 10);
    	chest.getBlock().setType(Material.AIR);
    }
}
