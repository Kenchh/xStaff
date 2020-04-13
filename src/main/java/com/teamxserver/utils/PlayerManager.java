package com.teamxserver.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerManager {

    public static Player getPlayerByName(String playername) {
        if(Bukkit.getPlayer(playername) != null) {
            if(Bukkit.getPlayer(playername).isOnline()) {
                return Bukkit.getPlayer(playername);
            }
        }
        return null;
    }

}
