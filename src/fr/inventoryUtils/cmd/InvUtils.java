package fr.inventoryUtils.cmd;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.inventoryUtils.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class InvUtils extends commands implements CommandExecutor {
	
	public Main plugin = Main.getInstance();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)){return false;}
		Player player = (Player) sender;
		if(args.length == 0){//Info command without args
			TextComponent message1 = new TextComponent();
			TextComponent message2 = new TextComponent();
			TextComponent message3 = new TextComponent();
			
			message1.setText("§7========================================================");
			player.spigot().sendMessage(message1);
			
			message2.setText(  "§e§lInventory Utils §r§eplugin\n"
							+ "§bmade by : §3§lFurrine§f\n"
							+ "§aVersion: §21.1.2 §r§c(inDev version)");
			message2.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/inventoryutils.39925/"));
			message2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW+"Open Plugin page on Spigotmc.org").create()));
			player.spigot().sendMessage(message2);
			
			message3.setText(  "§7========================================================\n"
							+ "§e§l/InvUtils help§r §8- §7See all commands\n"
							+ "§7========================================================");
			message3.setHoverEvent(null);
			message3.setClickEvent(null);
			player.spigot().sendMessage(message3);
			return true;
		}
		if(args[0].equalsIgnoreCase("help")){
			if(args.length !=1){ //If invalid syntax
				SyntaxError(player);
				return true;
			}
			String help = 	  "§7========================================================\n"
							+ "§e§l/InvUtils help§r §8- §7See help §c(But.. you're already here !)\n"
							+ "§e§l/InvUtils config §r§c<path> §8- §7See config path value\n"
							+ "§e§l/InvUtils config §r§c<path> <value> §8- §7Set config path value\n"
							+ "§e§l/autoBlock §r§c<ON|OFF> §8- §7Set self autoblock state\n"
							+ "§e§l/otherAutoBlock §r§c<name> <ON|OFF> §8- §7Set player autoblock state\n"
							+ "§7========================================================";
			player.sendMessage(help);
			return true;
		}
		if(args[0].equalsIgnoreCase("config")){
			if(args.length > 3){ //If invalid syntax
				SyntaxError(player);
				return true;
			}
			if(args.length == 2){
				if(plugin.getConfig().get(args[1]) != null){
					player.sendMessage(args[1]+" : "+plugin.getConfig().get(args[1]));
					return true;
				}else{
					player.sendMessage("Incorrect config path. Check the config file or the wiki.");
					return true;
				}
			}
			if(plugin.getConfig().get(args[1]) != null){
				plugin.getConfig().set(args[1], args[2]);
				plugin.saveConfig();
				player.sendMessage("§eConfig edited");
				plugin.loadConfig();
				return true;
			}
		}
		
		
		return false;
	}

}