package com.teamxserver.config.staffevents;

import com.teamxserver.staffevents.StaffEventManager;
import com.teamxserver.staffevents.events.EggHunt;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class EggHuntConfig {

    private static File file;
    private static FileConfiguration eventfile;

    public static void init() {

        file = new File(Bukkit.getPluginManager().getPlugin("xStaff").getDataFolder(), "egghuntdata.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        eventfile = YamlConfiguration.loadConfiguration(file);
        eventfile.options().copyDefaults(true);

        if(eventfile.get("collectcommand") != null && eventfile.get("collectcommand") != "" && eventfile.get("collectcommand") != "null") {
        } else {
            eventfile.set("collectcommand", "eco give %player% 300");
        }

        if(eventfile.get("finishcommand") != null && eventfile.get("finishcommand") != "" && eventfile.get("finishcommand") != "null") {
        } else {
            eventfile.set("finishcommand", "seasonpassplus:safegive %player% 1 givecase %player% eastereggcrate 1");
        }

        EggHunt.collectcommand = eventfile.get("collectcommand").toString();
        EggHunt.finishcommand = eventfile.get("finishcommand").toString();

        try {
            eventfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getKeys() {
        return eventfile.getKeys(false);
    }

    public static int getPlacedEggsAmount() {
        int eggs = 0;
        for(String loc : getKeys()) {
            if((Boolean) eventfile.get(loc + ".set") != null) {
                if ((Boolean) eventfile.get(loc + ".set") == true) {
                    eggs++;
                }
            } else {
                continue;
            }
        }
        return eggs;
    }

    public static int getFoundEggsAmount(Player player) {
        int eggs = 0;
        for(String loc : getKeys()) {
            if(eventfile.get(loc + "." + player.getUniqueId()) != null && eventfile.get(loc + "." + player.getUniqueId()) != "" && eventfile.get(loc + "." + player.getUniqueId()) != "null") {
                if((Boolean) eventfile.get(loc + ".set") == true) {
                    eggs++;
                }
            }
        }
        return eggs;
    }

    public static Object get(String event) {
        return eventfile.get(event);
    }

    public static boolean hasFoundEgg(Location location, Player player) {
        String loc = location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ();
        if(eventfile.get(loc + "." + player.getUniqueId()) != null && eventfile.get(loc + "." + player.getUniqueId()) != "" && eventfile.get(loc + "." + player.getUniqueId()) != "null") {
            if((Boolean) eventfile.get(loc + ".set") == true) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEgg(Location loc) {
        if(getKeys().contains(loc.getBlockX() + "-" + loc.getBlockY() + "-" + loc.getBlockZ()) && (Boolean) eventfile.get(loc.getBlockX() + "-" + loc.getBlockY() + "-" + loc.getBlockZ() + ".set") == true) {
            return true;
        }
        return false;
    }

    public static void setFoundEgg(Location location, Player player) {
        eventfile.set(location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ() + "." + player.getUniqueId().toString(), true);
    }

    public static void setPlacedEgg(Location location) {
        eventfile.set(location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ() + ".set", true);
    }

    public static void removePlacedEgg(Location location) {
        eventfile.set(location.getBlockX() + "-" + location.getBlockY() + "-" + location.getBlockZ() + ".set", false);
    }

    public static void resetData() {
        file.delete();
        init();
    }

    public static void set(String path, Object value) {
        eventfile.set(path, value);
    }

    public static void save() {
        try {
            eventfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
