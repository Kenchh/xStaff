package com.teamxserver.commands;

import com.teamxserver.gui.staffevents.EventGUI;
import com.teamxserver.messages.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class hostevent implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("xstaff.hostevent") == false) {
                p.sendMessage(MessageManager.getMsg("noperm"));
                return false;
            }

            p.openInventory(new EventGUI(p).getInventory());

        }

        return false;
    }
}
