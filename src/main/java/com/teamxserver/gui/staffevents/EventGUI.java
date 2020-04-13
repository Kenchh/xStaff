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

import java.util.Arrays;

public class EventGUI implements Listener {

    Inventory eventinv;

    public EventGUI() {

    }

    public EventGUI(Player player) {
        eventinv = Bukkit.createInventory(null, 9*6, "Select an event to start/host");

        createItems(player);
    }

    public void createItems(Player player) {
        for(StaffEvent e : StaffEventManager.staffevents) {
            addEventItem(player, e, e.getEventid());
        }
    }

    public void addEventItem(Player player, StaffEvent e, int slot) {
        ItemStack item = e.getItem();

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(e.getDisplayname());

        String startorstop = ChatColor.GREEN + "start";
        if(e.isActive) {
            startorstop = ChatColor.RED + "stop";
        }

        String enterorleave = ChatColor.GREEN + "enter";
        if(e.setup.contains(player.getName())) {
            enterorleave = ChatColor.RED + "leave";
        }

        meta.setLore(Arrays.asList(
                ChatColor.GRAY + "Left-Click to " + startorstop + ChatColor.GRAY + " this event.",
                ChatColor.GRAY + "Right-Click to " + enterorleave + ChatColor.GRAY + " setup-mode for this event.",
                ChatColor.DARK_RED + "Shift-Click to reset this event."
        ));

        item.setItemMeta(meta);

        eventinv.setItem(slot-1+10, item);
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getTitle() != "Select an event to start/host") {
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

        if(e.getCurrentItem().hasItemMeta() == false) {
            return;
        }

        if(e.getCurrentItem().getItemMeta().hasDisplayName() == false) {
            return;
        }

        StaffEvent se = StaffEventManager.getStaffEventByDisplayname(e.getCurrentItem().getItemMeta().getDisplayName());

        if(e.getClick().isShiftClick()) {
            p.openInventory(new ConfirmResetEvent("Are you sure you want to reset all data for " + se.displayname + "?", se).getInventory());
            return;
        }

        if(e.getClick().isLeftClick()) {

            if(se.isActive) {
                se.stopEvent(p);
            } else {
                se.startEvent(p);
            }

        }


        if(e.getClick().isRightClick()) {
            se.setupEvent(p);
            return;
        }


        p.openInventory(new EventGUI(p).getInventory());

    }

    public Inventory getInventory() {
        return eventinv;
    }

}
