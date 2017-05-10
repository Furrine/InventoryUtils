package fr.inventoryUtils.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class autoblock extends commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){sender.sendMessage("§cYou must be a player");return true;}
		Player player = (Player) sender;
		if(args.length == 0){
			String state = "§cOFF";
			if(plugin.autoBlockArray.contains(player)){state = "§aON";}
			player.sendMessage("§bautoBlock state : "+state);
			return true;
		}
		if(args.length > 1){
			SyntaxError(player);
			return true;
		}
		if(args[0].equalsIgnoreCase("on")){
			String state = "§aON";
			if(!plugin.autoBlockArray.contains(player)){
				plugin.autoBlockArray.add(player);
				player.sendMessage("§eautoBlock state set to : "+state);
				return true;
			}
			player.sendMessage("§bautoBlock state already set to §aON");
			return true;			
		}
		if(args[0].equalsIgnoreCase("off")){
			String state = "§cOFF";
			if(plugin.autoBlockArray.contains(player)){
				plugin.autoBlockArray.remove(player);
				player.sendMessage("§eautoBlock state set to : "+state);
				return true;
			}
			player.sendMessage("§bautoBlock state already set to §cOFF");
			return true;
		}
		return false;
	}

}
