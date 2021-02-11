package com.Techtician.survivalgames.commands;

import org.bukkit.entity.Player;
// import com.Techtician.survivalgames.SettingsManager;
import com.Techtician.survivalgames.stats.StatsWallManager;



public class SetStatsWall implements SubCommand {

    public boolean onCommand(Player player, String[] args) {
        StatsWallManager.getInstance().setStatsSignsFromSelection(player);
        return false;
    }

    
    public String help(){
        return null; //"/sg setstatswall - "+ SettingsManager.getInstance().getMessageConfig().getString("messages.help.setstatswall", "Sets the stats wall");
    }

	public String permission() {
		return "sg.admin.setstatswall";
	}
}
