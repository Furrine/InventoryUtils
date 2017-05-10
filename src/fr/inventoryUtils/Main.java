package fr.inventoryUtils;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.inventoryUtils.cmd.InvUtils;
import fr.inventoryUtils.cmd.autoblock;
import fr.inventoryUtils.cmd.otherautoblock;
import fr.inventoryUtils.listeners.autoBlock;

public class Main extends JavaPlugin {
	
	static Main instance;
	public Boolean autoBlock;
	public ArrayList<Player> autoBlockArray = new ArrayList<>();
	
	@Override
	public void onEnable(){
		saveDefaultConfig();
		
		loadConfig();
		
		instance = this;
		
		getServer().getPluginManager().registerEvents(new autoBlock(), this);
		getServer().getPluginCommand("InvUtils").setExecutor(new InvUtils());
		getServer().getPluginCommand("autoblock").setExecutor(new autoblock());
		getServer().getPluginCommand("otherautoblock").setExecutor(new otherautoblock());
		
		for(Player p : Bukkit.getOnlinePlayers()){
			if(!autoBlockArray.contains(p) && autoBlock){
				autoBlockArray.add(p);
			}
		}
	}
	
	public void loadConfig(){
		autoBlock = getConfig().getBoolean("defaultValues.autoBlock");
	}
	
	@Override
	public void onDisable(){
		
	}
	
	public static Main getInstance(){
		return instance;
	}

}
