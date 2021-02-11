package com.Techtician.survivalgames.commands;

import org.bukkit.command.CommandSender;
import com.Techtician.survivalgames.Game;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.SettingsManager;



public class Disable implements ConsoleSubCommand{

    public boolean onCommand(CommandSender sender, String[] args) {   
    	if(!sender.hasPermission(permission()) && !sender.isOp()){
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.nopermission", sender);
            return true;
        }
        try{
        if(args.length == 0){
            for(Game g: GameManager.getInstance().getGames()){
                g.disable();
            }
                MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.INFO, "game.all", sender, "input-disabled");

        }else{

            GameManager.getInstance().disableGame(Integer.parseInt(args[0]));
                MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.INFO, "game.state", sender, "arena-" + args[0], "input-disabled");
        }
        } catch (NumberFormatException e) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.notanumber", sender, "input-Arena");
        } catch (NullPointerException e) {
            MessageManager.getInstance().sendFMessage(MessageManager.PrefixType.ERROR, "error.gamedoesntexist", sender, "arena-" + args[0]);
        }
        return true;
    }
    
    public String help() {
        return "/sg disable <id> - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.disable", "Disables arena <id>");
    }

	public String permission() {
		return "sg.arena.disable";
	}
}
