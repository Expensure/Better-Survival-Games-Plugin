package com.Techtician.survivalgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import com.Techtician.survivalgames.Game;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.MessageManager.PrefixType;
import com.Techtician.survivalgames.logging.QueueManager;
import com.Techtician.survivalgames.SettingsManager;

public class Reload implements ConsoleSubCommand{

	public boolean onCommand(final CommandSender sender, String[] args) {		
		if(sender.hasPermission(permission())){
			if(args.length != 1){
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Valid reload types <Settings | Games | All>", sender);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Settings will reload the settings configs and attempt to reapply them", sender);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Games will reload all games currently running", sender);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "All will attempt to reload the entire plugin", sender);

				return true;

			}
			if(args[0].equalsIgnoreCase("settings")){
				SettingsManager.getInstance().reloadChest();
				SettingsManager.getInstance().reloadKits();
				SettingsManager.getInstance().reloadMessages();
				SettingsManager.getInstance().reloadSpawns();
				SettingsManager.getInstance().reloadSystem();
				SettingsManager.getInstance().reloadConfig();
				for(Game g: GameManager.getInstance().getGames()){
					g.reloadConfig(); 
				}
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Settings Reloaded", sender);
			}
			else if(args[0].equalsIgnoreCase("games")){	
				for(Game g:GameManager.getInstance().getGames()){
					QueueManager.getInstance().rollback(g.getID(), true);
					g.disable();
					g.enable();
				}
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Games Reloaded", sender);
			}
			else if(args[0].equalsIgnoreCase("all")){	
				final Plugin pinstance = GameManager.getInstance().getPlugin();
				Bukkit.getPluginManager().disablePlugin(pinstance);
				Bukkit.getPluginManager().enablePlugin(pinstance);
				MessageManager.getInstance().sendMessage(PrefixType.INFO, "Plugin reloaded", sender);
			}

		} else {
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", sender);
		}
		return true;
	}

	public String help() {
		// TODO Auto-generated method stub
		return "/sg reload [settings|game|all]  Reload module configuration";
	}

	public String permission() {
		return "sg.admin.reload";
	}

}
