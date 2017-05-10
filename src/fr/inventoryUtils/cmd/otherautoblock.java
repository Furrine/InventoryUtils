package fr.inventoryUtils.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class otherautoblock extends commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1){
			Player target = Bukkit.getPlayer(args[0]);
			if(target.isOnline()){
				String state = "§cOFF";
				if(plugin.autoBlockArray.contains(target)){
					state = "§aON";
				}
				sender.sendMessage("§bautoBlock state of §e"+target.getName()+"§b : "+state);
				return true;
			}else{
				sender.sendMessage("§cPlayer not found");
			}
		}
		if(args.length == 2){
			Player target = Bukkit.getPlayer(args[0]);
			if(target.isOnline()){
				if(args[1].equalsIgnoreCase("on")){
					String state = "§aON";
					if(!plugin.autoBlockArray.contains(target)){
						plugin.autoBlockArray.add(target);
						sender.sendMessage("§eautoBlock state set to : "+state);
						target.sendMessage("§eautoBlock state changed to : "+state);
						return true;
					}
					sender.sendMessage("§bautoBlock state already set to §aON");
					return true;			
				}else if(args[1].equalsIgnoreCase("off")){
					String state = "§aOFF";
					if(plugin.autoBlockArray.contains(target)){
						plugin.autoBlockArray.remove(target);
						sender.sendMessage("§eautoBlock state set to : "+state);
						target.sendMessage("§eautoBlock state changed to : "+state);
						return true;
					}
					sender.sendMessage("§bautoBlock state already set to §aOFF");
					return true;			
				}else{
					sender.sendMessage("§cInvalide state (use ON or OFF)");
					return true;
				}
			}else{
				sender.sendMessage("§cPlayer not found");
				return true;
			}
		}
		
		return false;
	}

}
