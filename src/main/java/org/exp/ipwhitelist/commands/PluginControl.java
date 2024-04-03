package org.exp.ipwhitelist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import static org.exp.ipwhitelist.Ipwhitelist.getInstance;

public class PluginControl implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String str, String[] args) {

        if (commandSender instanceof ConsoleCommandSender) {


            getInstance.isEnable = !getInstance.isEnable;


            commandSender.sendMessage("This plugin is " +  (getInstance.isEnable? "Enable": "Disable"));
            return true;
        }
        else  {
            Player player = (Player) commandSender;
            player.sendMessage("This command can only be used in the console.");
            return true;
        }
    }
}
