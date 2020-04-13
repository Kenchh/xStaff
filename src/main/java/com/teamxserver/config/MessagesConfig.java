package com.teamxserver.config;

import com.teamxserver.messages.MessageManager;
import com.teamxserver.staffevents.StaffEvent;
import com.teamxserver.staffevents.StaffEventManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class MessagesConfig {

    private static File file;
    private static FileConfiguration messagesfile;

    public static void init() {

        file = new File(Bukkit.getPluginManager().getPlugin("xStaff").getDataFolder(), "messages.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        messagesfile = YamlConfiguration.loadConfiguration(file);
        messagesfile.options().copyDefaults(true);

        for(String type : MessageManager.msgs.keySet()) {
            if(get(type) != null) {
                String msg = (String) get(type);

                if(msg == null || msg == "" || msg == " ") {
                    set(type, MessageManager.getMsg(type));
                } else {
                    MessageManager.editMessage(type, msg.replace("&", "§"));
                }

            } else {
                set(type, MessageManager.getMsg(type));
                continue;
            }
        }

        try {
            messagesfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Set<String> getTypes() {
        return messagesfile.getKeys(false);
    }

    public static Object get(String type) {
        return messagesfile.get(type);
    }

    public static void set(String type, String msg) {
        messagesfile.set(type, msg);
    }

    public static void save() {
        try {
            messagesfile.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /*
    public static void set(String type, String msg) {
        messagesfile.set(type, msg);
    }
    */

}
