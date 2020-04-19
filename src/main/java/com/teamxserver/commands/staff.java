package com.teamxserver.commands;

import com.teamxserver.main.xStaff;
import com.teamxserver.messages.MessageManager;
import com.teamxserver.staff.StaffManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class staff implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 0) {
                p.performCommand("staff:staff");
            } else {

                if(p.hasPermission("xstaff.staff")) {

                    StaffManager.switchStaffMode(p);

                } else {
                    p.sendMessage(MessageManager.getMsg("noperm"));
                }

            }

            /*
            if(p.hasPermission("xstaff.staff")) {
                if(args.length == 0) {
                    p.performCommand("staff:staff");
                    return true;
                }



            } else {
                p.sendMessage(MessageManager.getMsg("noperm"));
            }
            */

        } else {
            sender.sendMessage("You need to be ingame to execute this command!");
        }

        return false;
    }

}
