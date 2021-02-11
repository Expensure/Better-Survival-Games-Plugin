package com.Techtician.survivalgames.commands;


import org.bukkit.entity.Player;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.SettingsManager;
import com.Techtician.survivalgames.MessageManager.PrefixType;


public class AutoJoin implements SubCommand {

	public boolean onCommand(Player player, String[] args) {
		if(player.hasPermission(permission())){
			if(SettingsManager.getInstance().getConfig().getInt("randomjoin-mode", 1) == 0) {
				GameManager.getInstance().addPlayerRandomly(player);
			}else {
				GameManager.getInstance().autoAddPlayer(player);
			}
			
		}
		else{
			MessageManager.getInstance().sendFMessage(PrefixType.WARNING, "error.nopermission", player);
		}
		return true;
	}

	public String help() {
		return "/sg autojoin - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.autojoin", "Automatically joins a game");
	}

	public String permission() {
		return "sg.arena.join";
	}
}

