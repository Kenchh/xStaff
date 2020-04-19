package com.teamxserver.staff;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class StaffItem {

    public String name;
    public int slot;
    public ItemStack itemstack;

    public StaffItem(String name, String displayname, String description, int slot, ItemStack itemstack) {
        this.name = name;
        this.slot = slot;

        ItemStack item = itemstack;
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setLore(Arrays.asList(description));


    }

}
