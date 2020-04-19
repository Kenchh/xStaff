package com.teamxserver.main;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.teamxserver.commands.hostevent;
import com.teamxserver.commands.staff;
import com.teamxserver.config.MessagesConfig;
import com.teamxserver.config.staffevents.EggHuntConfig;
import com.teamxserver.config.staffevents.StaffEventConfig;
import com.teamxserver.gui.staffevents.ConfirmResetEvent;
import com.teamxserver.gui.staffevents.EventGUI;
import com.teamxserver.messages.MessageManager;
import com.teamxserver.staff.StaffManager;
import com.teamxserver.staffevents.StaffEvent;
import com.teamxserver.staffevents.StaffEventManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import javax.swing.*;
import java.util.ArrayList;

public class xStaff extends JavaPlugin {

    @Override
    public void onEnable() {

        MessageManager.initDefaultMessages();
        MessagesConfig.init();

        StaffEventManager.initStaffEvents();

        Bukkit.getPluginManager().registerEvents(new EventGUI(), this);
        Bukkit.getPluginManager().registerEvents(new ConfirmResetEvent(), this);

        for(StaffEvent e : StaffEventManager.staffevents) {
            Bukkit.getPluginManager().registerEvents(e, this);
        }

        StaffEventConfig.init();

        this.getCommand("hostevent").setExecutor(new hostevent());
        this.getCommand("staff").setExecutor(new staff());

        EggHuntConfig.init();

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                StaffEventConfig.save();
                EggHuntConfig.save();
            }
        }, 1L, 100L);

        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(StaffManager.instaff.contains(p.getName())) {
                        ActionBarAPI.sendActionBar(p, MessageManager.getMsg("staffmode"));
                    }
                }
            }
        }, 50L, 60L);

    }


}
