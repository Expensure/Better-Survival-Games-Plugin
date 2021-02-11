package com.Techtician.survivalgames.commands;

import org.bukkit.command.CommandSender;

public interface ConsoleSubCommand {

    public boolean onCommand(CommandSender sender, String[] args);

    public String help();
    
    public String permission();
    
}