package com.Techtician.survivalgames.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.SettingsManager;



public class ListPlayers implements ConsoleSubCommand{
	
	private boolean isPlayer = false;

	public boolean onCommand(CommandSender sender, String[] args) {
		Player player = null;
		if(sender instanceof Player) {
			player = (Player)sender;
			isPlayer = true;
		}
		
		int gid = 0;
		try{
			if(args.length == 0 && isPlayer){
				gid = GameManager.getInstance().getPlayerGameId(player);
			}
			else if(args.length >= 1){
				gid =  Integer.parseInt(args[0]);
			}

			String[] msg = GameManager.getInstance().getStringList(gid).split("\n");
			sender.sendMessage(msg);
			return false;
        } catch (NumberFormatException ex) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.notanumber", sender, "input-Arena");
        } catch (NullPointerException ex) {
            MessageManager.getInstance().sendMessage(MessageManager.PrefixType.ERROR, "error.gamenoexist", sender);
        }
		return false;
	}

	public String help() {
        return "/sg list [<id>] " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.listplayers","List all players in the arena you are playing in");
	}

	public String permission() {
		return "";
	}

}