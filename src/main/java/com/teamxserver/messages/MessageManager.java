package com.teamxserver.messages;

import org.bukkit.ChatColor;

import java.util.HashMap;

public class MessageManager {

    public static HashMap<String, String> msgs = new HashMap<String, String>();

    public static void initDefaultMessages() {
        addDefaultMessage("prefix", ChatColor.DARK_AQUA + "Staff " + ChatColor.GRAY + ">>" + ChatColor.RESET + " ");
        addDefaultMessage("noperm", getMsg("prefix") + ChatColor.RED + "You do not have permission to execute this command.");

        addDefaultMessage("staffmode", ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF MODE");

        /** Staff Events */
        addDefaultMessage("startevent", getMsg("prefix") + ChatColor.GREEN + "You have started event " + ChatColor.BOLD);
        addDefaultMessage("stopevent", getMsg("prefix") + ChatColor.GREEN + "You have " + ChatColor.RED + "" + "stopped" + ChatColor.GREEN + " event " + ChatColor.RED + "" + ChatColor.BOLD);
        addDefaultMessage("setupevent", getMsg("prefix") + ChatColor.GREEN + "You have are now setting up event " + ChatColor.BOLD);
        addDefaultMessage("leavesetupevent", getMsg("prefix") + ChatColor.RED + "You are no longer setting up event " + ChatColor.BOLD);
        addDefaultMessage("errorinsetup", getMsg("prefix") + ChatColor.RED + "You need to get out of setup mode to perform this action!");
        addDefaultMessage("eventalreadyrunning", getMsg("prefix") + ChatColor.RED + "There is already an event running!");
        addDefaultMessage("invnotemptysetup", getMsg("prefix") + ChatColor.RED + "Please empty your inventory before entering setup mode!");
        addDefaultMessage("errorisactive", getMsg("prefix") + ChatColor.RED + "Please stop the current running event to perform this action!");
        addDefaultMessage("resetevent", getMsg("prefix") + ChatColor.DARK_RED + "All event related data has now been reset.");

            /** Egg Hunt Messages */
            addDefaultMessage("eggprefix", ChatColor.RESET + "O " + ChatColor.YELLOW + "E" + ChatColor.GOLD + "gg" + ChatColor.GREEN + "Hu" + ChatColor.BLUE + "nt " + ChatColor.WHITE + "O" + ChatColor.GRAY + " >>" + ChatColor.RESET + " ");
            addDefaultMessage("placedegg", getMsg("eggprefix") + ChatColor.GREEN + "%placedeggs% eggs placed!");
            addDefaultMessage("destroyedegg", getMsg("eggprefix") + ChatColor.RED + "-1 egg, Now: %placedeggs%");
            addDefaultMessage("foundegg", getMsg("eggprefix") + ChatColor.YELLOW + "<%foundeggs%/%placedeggs%> eggs found!");
            addDefaultMessage("alreadyfoundegg", getMsg("eggprefix") + ChatColor.RED + "You have already found this egg!");
            addDefaultMessage("foundalleggs", getMsg("eggprefix") + ChatColor.LIGHT_PURPLE + ChatColor.BOLD + "%player% has found all easter eggs!");
    }

    public static String getMsg(String name) {
        if(msgs.containsKey(name)) {
            return msgs.get(name);
        }
        return "Message " + name + " not found.";
    }

    public static void addDefaultMessage(String name, String msg) {
        if(msgs.containsKey(name) == false) {
            msgs.put(name, msg);
        }
    }

    public static void editMessage(String name, String newmsg) {
        if(msgs.containsKey(name)) {
            msgs.replace(name, msgs.get(name), newmsg);
        }
    }

    public void removeDefaultMessage(String name) {
        if(msgs.containsKey(name)) {
            msgs.remove(name);
        }
    }

}
