package com.Techtician.survivalgames.commands;

import org.bukkit.entity.Player;
import com.Techtician.survivalgames.MessageManager;
import com.Techtician.survivalgames.MessageManager.PrefixType;
import com.Techtician.survivalgames.SettingsManager;



public class SetLobbySpawn implements SubCommand {

    public boolean onCommand(Player player, String[] args) {
        if (!player.hasPermission(permission()) && !player.isOp()) {
            MessageManager.getInstance().sendFMessage(PrefixType.ERROR, "error.nopermission", player);
            return true;
        }
        SettingsManager.getInstance().setLobbySpawn(player.getLocation());
        MessageManager.getInstance().sendFMessage(PrefixType.INFO, "info.lobbyspawn", player);
        return true;
    }
    
    public String help() {
        return "/sg setlobbyspawn - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.setlobbyspawn", "Set the lobby spawnpoint");
    }

	public String permission() {
		return "sg.admin.setlobby";
	}
}
