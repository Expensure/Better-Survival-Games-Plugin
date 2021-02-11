package com.Techtician.survivalgames.commands;

import org.bukkit.command.CommandSender;
import com.Techtician.survivalgames.Game;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.Game.GameMode;
import com.Techtician.survivalgames.MessageManager.PrefixType;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.SettingsManager;



public class Enable implements ConsoleSubCommand{

	public boolean onCommand(CommandSender sender, String[] args) {      
		if(!sender.hasPermission(permission()) && !sender.isOp()){
			MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", sender);
			return true;
		}
		try{
			if(args.length == 0){
				for(Game g:GameManager.getInstance().getGames()){
					if(g.getMode() == GameMode.DISABLED)
						g.enable();
				}
				MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.INFO, "game.all", sender, "input-enabled");
			}
			else{
				GameManager.getInstance().enableGame(Integer.parseInt(args[0]));
				MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.INFO, "game.state", sender, "arena-" + args[0], "input-enabled");
			}
		} catch (NumberFormatException e) {
			MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.notanumber", sender, "input-Arena");
		} catch (NullPointerException e) {
			MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.gamedoesntexist", sender, "arena-" + args[0]);
		}
		return true;

	}


	public String help() {
		return "/sg enable <id> - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.enable", "Enables arena <id>");
	}

	public String permission() {
		return "sg.arena.enable";
	}
}