package com.Techtician.survivalgames.commands;

import org.bukkit.entity.Player;
import com.Techtician.survivalgames.GameManager;
import com.Techtician.survivalgames.SettingsManager;



public class LeaveQueue implements SubCommand{

    public boolean onCommand(Player player, String[] args) {
        GameManager.getInstance().removeFromOtherQueues(player, -1);
        return true;
    }

    public String help() {
        return "/sg lq - " + SettingsManager.getInstance().getMessageConfig().getString("messages.help.leavequeue", "Leave the queue for any queued games");
    }

	public String permission() {
		return null;
	}

}
