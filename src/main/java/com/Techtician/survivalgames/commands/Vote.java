package com.Techtician.survivalgames.commands;


import org.bukkit.entity.Player;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.SettingsManager;


public class Vote implements SubCommand {
    
    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission(permission()) && !player.isOp()) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.nopermission", player);
            return false;
        }
        int game = GameManager.getInstance().getPlayerGameId(player);
        if(game == -1){
            MessageManager.getInstance().sendMessage(MessageManager.PrefixType.ERROR, "error.notinarena", player);
            return true;
        }

        GameManager.getInstance().getGame(GameManager.getInstance().getPlayerGameId(player)).vote(player);

        return true;
    }
    
    public String help() {
        return "/sg vote - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.vote", "Votes to start the game");
    }

	public String permission() {
		return "sg.arena.vote";
	}
}