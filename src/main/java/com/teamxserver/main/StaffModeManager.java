package com.teamxserver.main;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffModeManager {

    public static ArrayList<String> instaff = new ArrayList<String>();

    public static HashMap<String, ItemStack[]> playerinv = new HashMap<String, ItemStack[]();
    public static HashMap<String, ItemStack[]> playerarmor = new HashMap<String, ItemStack[]();

    public static void switchStaffMode(Player p) {
        if(instaff.contains(p)) {

            instaff.add(p.getName());
            saveInv(p);


        } else {

            instaff.remove(p.getName());
            giveInv(p);

        }
    }

    public static void saveInv(Player p) {
        playerinv.put(p.getName(), p.getInventory().getContents());
        playerarmor.put(p.getName(), p.getInventory().getArmorContents());
    }

    public static void giveInv(Player p) {
        p.getInventory().setContents(playerinv.get(p));
        p.getInventory().setArmorContents(playerarmor.get(p));
        playerinv.put(p.getName(), p.getInventory().getContents());
        playerarmor.put(p.getName(), p.getInventory().getArmorContents());
    }

}
