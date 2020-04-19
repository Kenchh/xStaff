package com.teamxserver.staff;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.teamxserver.messages.MessageManager;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;

public class StaffManager {

    public static ArrayList<String> instaff = new ArrayList<String>();

    public static HashMap<String, ItemStack[]> playerinv = new HashMap<String, ItemStack[]>();
    public static HashMap<String, ItemStack[]> playerarmor = new HashMap<String, ItemStack[]>();

    public static void switchStaffMode(Player p) {
        if(instaff.contains(p.getName())) {

            instaff.remove(p.getName());
            giveInv(p);

            setFly(p, false);
            setInvisEffect(p, false);

        } else {

            instaff.add(p.getName());
            saveInv(p);

            p.setFoodLevel(20);
            p.setSaturation(10.0F);
            p.getInventory().clear();

            setFly(p, true);
            setInvisEffect(p, true);

        }
    }

    public static void saveInv(Player p) {
        playerinv.put(p.getName(), p.getInventory().getContents());
        playerarmor.put(p.getName(), p.getInventory().getArmorContents());
    }

    public static void giveInv(Player p) {
        p.getInventory().setContents(playerinv.get(p.getName()));
        p.getInventory().setArmorContents(playerarmor.get(p.getName()));
        playerinv.put(p.getName(), p.getInventory().getContents());
        playerarmor.put(p.getName(), p.getInventory().getArmorContents());
    }

    public static void setFly(Player p, boolean fly) {
        if(fly == true) {
            if(p.getAllowFlight() == false) {
                p.setAllowFlight(true);
            }
            p.setFlying(true);
        } else {
            p.setFlying(false);
        }
    }

    public static void setInvisEffect(Player p, boolean invis) {
        if(invis == true) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 1000000, 0));
        } else {
            p.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

}
