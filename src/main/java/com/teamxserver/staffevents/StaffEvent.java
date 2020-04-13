package com.teamxserver.staffevents;

import com.teamxserver.config.staffevents.StaffEventConfig;
import com.teamxserver.messages.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class StaffEvent implements Listener {

    public String name;
    public int eventid;
    public String displayname;
    public ItemStack item;
    public boolean isActive = false;
    public ArrayList<String> setup = new ArrayList<String>();

    public StaffEvent(String name, int eventid, String displayname, ItemStack item) {
        this.name = name;
        this.eventid = eventid;
        this.displayname = displayname;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public void startEvent(Player player) {
        if(StaffEventManager.eventAlreadyRunning() == false) {
            isActive = true;
            StaffEventConfig.set(name, true);
            player.sendMessage(MessageManager.getMsg("startevent") + displayname);
        } else {
            player.sendMessage(MessageManager.getMsg("eventalreadyrunning"));
            player.closeInventory();
            return;
        }
    }

    public void stopEvent(Player player) {
        if(isActive) {
            isActive = false;
            StaffEventConfig.set(name, false);
            player.sendMessage(MessageManager.getMsg("stopevent") + displayname);
        } else {
            return;
        }
    }

    public void setupEvent(Player player) {

    }

    public void removeEventData(Player player) {

    }

}
