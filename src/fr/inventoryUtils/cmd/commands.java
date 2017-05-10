package fr.inventoryUtils.cmd;

import org.bukkit.entity.Player;

import fr.inventoryUtils.Main;

public class commands {
		

	Main plugin = Main.getInstance();
	
	public void SyntaxError(Player player){
		player.sendMessage("§cInvalid syntax. Type /InvUtils help to see the commands");
	}
}
