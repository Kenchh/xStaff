package com.teamxserver.gui.staffevents;

import com.teamxserver.messages.MessageManager;
import com.teamxserver.staffevents.StaffEvent;
import com.teamxserver.staffevents.StaffEventManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Method;

public class ConfirmResetEvent implements Listener {

    public ConfirmResetEvent() {

    }

    Inventory confirminv;

    public ConfirmResetEvent(String prompt, StaffEvent staffevent) {
        confirminv = Bukkit.createInventory(null, 9*5, "Confirm to continue -" + staffevent.getName());

        ItemStack confirmitem = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta confirmmeta = confirmitem.getItemMeta();
        confirmmeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "ACCEPT");
        confirmitem.setItemMeta(confirmmeta);

        ItemStack abortitem = new ItemStack(Material.BARRIER);
        ItemMeta abortmeta = abortitem.getItemMeta();
        abortmeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "ABORT");
        abortitem.setItemMeta(abortmeta);

        ItemStack promptitem = new ItemStack(Material.SIGN);
        ItemMeta promptmeta = promptitem.getItemMeta();
        promptmeta.setDisplayName(ChatColor.GRAY + prompt);
        promptitem.setItemMeta(promptmeta);

        confirminv.setItem(2 + 9*2, abortitem);
        confirminv.setItem(4 + 9*2, promptitem);
        confirminv.setItem(6 + 9*2, confirmitem);

    }

    public Inventory getInventory() {
        return confirminv;
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getTitle().contains("Confirm to continue") == false) {
            return;
        }

        if(p.hasPermission("xstaff.hostevent") == false) {
            p.sendMessage(MessageManager.getMsg("noperm"));
            p.closeInventory();
            return;
        }


        if(e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem().getType() == null || e.getCurrentItem() == null) {
            return;
        }

        e.setCancelled(true);

        if(e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
            String[] s = e.getInventory().getTitle().split("-");
            StaffEventManager.getStaffEventByName(s[1]).removeEventData((Player) e.getWhoClicked());
        }

        if(e.getCurrentItem().getType() == Material.BARRIER) {
            p.closeInventory();
        }
    }

}
