package com.teamxserver.staffevents.items;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Egg extends ItemStack {

    public Egg(int type) {

            this.setType(Material.SKULL_ITEM);
            this.setAmount(1);
            this.setDurability((short) 3);
            SkullMeta meta = (SkullMeta) this.getItemMeta();

            String skinurl = null;

            if (type == 1) {
                skinurl = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThiOWUyOWFiMWE3OTVlMmI4ODdmYWYxYjFhMzEwMjVlN2NjMzA3MzMzMGFmZWMzNzUzOTNiNDVmYTMzNWQxIn19fQ==";
            }

            if (type == 2) {
                skinurl = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTg4OWYxMWM4ODM4YzA5ZTFlY2YyZjgzNDM5ZWJjYjlmMzI0ZTU2N2IwZTlkYzRiN2MyNWQ5M2U1MGZmMmIifX19";
            }

            if (type == 3) {
                skinurl = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4YjJiZGZjNWQxM2I5ZjQ1OTkwZWUyZWFhODJlNDRhZmIyYTY5YjU2YWM5Mjc2YTEzMjkyYjI0YzNlMWRlIn19fQ==";
            }

            if (type == 4) {
                skinurl = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTYyODUzMWViNWYwNTY5ZWRhZTE2YzhhNDNlYjIyZWVjZjdjMTUyMzViODM1YWUxNDE0YzI2OWNhZDEyY2E3In19fQ==";
            }

            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", skinurl));
            Field profileField = null;
            try {
                profileField = meta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(meta, profile);
            } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                e1.printStackTrace();
            }

            this.setItemMeta(meta);
    }

}
