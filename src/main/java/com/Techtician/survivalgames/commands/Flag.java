package com.Techtician.survivalgames.commands;

import java.util.HashMap;

import org.bukkit.command.CommandSender;
import com.Techtician.survivalgames.Game;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.SettingsManager;



public class Flag implements ConsoleSubCommand {

    public boolean onCommand(CommandSender sender, String[] args) {
        
        if (!sender.hasPermission(permission())) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.nopermission", sender);
            return true;
        }
        
        if(args.length < 2){
        	sender.sendMessage(help());
            return true;
        }
        
        Game g = GameManager.getInstance().getGame(Integer.parseInt(args[0]));
        
        if(g == null){
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.gamedoesntexist", sender, "arena-" + args[0]);
            return true;
        }
        
        HashMap<String, Object>z = SettingsManager.getInstance().getGameFlags(g.getID());
        z.put(args[1].toUpperCase(), g.getID());
        SettingsManager.getInstance().saveGameFlags(z, g.getID());
        		

        
        return false;
    }

    public String help() {
        return "/sg flag <id> <flag> <value> - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.flag", "Modifies an arena-specific setting");
    }

	public String permission() {
		return "sg.admin.flag";
	}
}
