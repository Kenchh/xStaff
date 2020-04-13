package com.teamxserver.config.staffevents;

import com.teamxserver.staffevents.StaffEvent;
import com.teamxserver.staffevents.StaffEventManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class StaffEventConfig {

    private static File file;
    private static FileConfiguration eventfile;

    public static void init() {

        file = new File(Bukkit.getPluginManager().getPlugin("xStaff").getDataFolder(), "staffevents.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        eventfile = YamlConfiguration.loadConfiguration(file);
        eventfile.options().copyDefaults(true);

        for(StaffEvent event : StaffEventManager.staffevents) {
            if(get(event.getName()) != null) {
                boolean active = (Boolean) get(event.getName());
                StaffEventManager.getStaffEventByName(event.getName()).isActive = active;
            } else {
                set(event.getName(), false);
                continue;
            }
        }

        try {
            eventfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Set<String> getEvents() {
        return eventfile.getKeys(false);
    }

    public static Object get(String event) {
        return eventfile.get(event);
    }

    public static void set(String event, boolean isActive) {
        eventfile.set(event, isActive);
    }

    public static void save() {
        try {
            eventfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
