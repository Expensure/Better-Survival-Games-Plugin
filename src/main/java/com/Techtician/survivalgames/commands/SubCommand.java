package com.Techtician.survivalgames.commands;

import org.bukkit.entity.Player;

public interface SubCommand {

    public boolean onCommand(Player player, String[] args);

    public String help();
    
    public String permission();
    
}