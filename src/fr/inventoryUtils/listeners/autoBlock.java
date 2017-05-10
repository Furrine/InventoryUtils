package fr.inventoryUtils.listeners;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.inventoryUtils.Main;

public class autoBlock implements Listener {
	
	public Main plugin = Main.getInstance();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		if(plugin.autoBlock == true){
			if(plugin.autoBlockArray.contains(e.getPlayer())){return;}
			plugin.autoBlockArray.add(e.getPlayer());
		}
	}

	@EventHandler
	public void onPlayerPlaceBlock(BlockPlaceEvent e){
		
		Player player = e.getPlayer();
		ItemStack inHand = player.getInventory().getItemInMainHand();
		Block placed = e.getBlock();
		
		if(!plugin.autoBlockArray.contains(player)){return;} //If no plugin.autoBlockArray return
		
		if(inHand.getType() == placed.getType() && inHand.getAmount() == 1){ //The player placed the last block of his main hand
			//Survival action
			if(player.getGameMode() == GameMode.SURVIVAL){
				player.getInventory().getItemInMainHand().setAmount(0);
				if(player.getInventory().containsAtLeast(new ItemStack(placed.getType()), 1)){
					int newItemInt = player.getInventory().first(placed.getType());
					ItemStack newItem = player.getInventory().getItem(newItemInt);
					player.getInventory().setItem(newItemInt, new ItemStack(Material.AIR));
					player.getInventory().setItemInMainHand(newItem);
				}
			}
		}
	}
}