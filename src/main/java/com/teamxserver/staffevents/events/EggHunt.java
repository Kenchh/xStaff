package com.teamxserver.staffevents.events;

import com.teamxserver.config.staffevents.EggHuntConfig;
import com.teamxserver.gui.staffevents.EventGUI;
import com.teamxserver.staffevents.items.Egg;
import com.teamxserver.messages.MessageManager;
import com.teamxserver.staffevents.StaffEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EggHunt extends StaffEvent {

    public static String collectcommand;
    public static String finishcommand;

    public EggHunt() {
        super("egghunt", 1, ChatColor.RESET + "O " + ChatColor.YELLOW + "E" + ChatColor.GOLD + "gg" + ChatColor.GREEN + "Hu" + ChatColor.BLUE + "nt " + ChatColor.WHITE + "O", new Egg(1));
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        if(!isActive) {
            return;
        }

        if(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = e.getClickedBlock();

            if(block.getType() != Material.SKULL) {
                return;
            }

            Player p = e.getPlayer();

            if(setup.contains(p.getName())) {
                if(p.getGameMode() != GameMode.CREATIVE) {
                    p.sendMessage(MessageManager.getMsg("errorinsetup"));
                }
                return;
            }

            if(EggHuntConfig.isEgg(e.getClickedBlock().getLocation()) == false) {
                return;
            }

            if(EggHuntConfig.hasFoundEgg(e.getClickedBlock().getLocation(), p) == false) {
                EggHuntConfig.setFoundEgg(e.getClickedBlock().getLocation(), p);
                p.sendMessage(MessageManager.getMsg("foundegg").replace("%foundeggs%", "" + EggHuntConfig.getFoundEggsAmount(p)).replace("%placedeggs%", "" + EggHuntConfig.getPlacedEggsAmount()));
                for(int i= 0; i<= 3; i++)
                    p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.5F, 1.3F);

                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), collectcommand.replace("%player%", p.getName()));
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "xpaction " + p.getName() + " findegg");

                /* Found all */
                if(EggHuntConfig.getFoundEggsAmount(p) >= EggHuntConfig.getPlacedEggsAmount()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finishcommand.replace("%player%", p.getName()));
                    Bukkit.broadcastMessage(MessageManager.getMsg("foundalleggs").replace("%player%", p.getName()));
                    EggHuntConfig.set("finishedhunt." + p.getName(), true);
                }

            } else {
                p.sendMessage(MessageManager.getMsg("alreadyfoundegg"));
            }



        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();

        if(p.hasPermission("xstaff.hostevent") == false) {
            return;
        }

        if(setup.contains(p.getName()) == false) {
            return;
        }

        if(e.getBlock().getType() != Material.SKULL) {
            return;
        }

        if(EggHuntConfig.isEgg(e.getBlock().getLocation())) {
            EggHuntConfig.removePlacedEgg(e.getBlock().getLocation());
            p.sendMessage(MessageManager.getMsg("destroyedegg").replace("%placedeggs%", "" + EggHuntConfig.getPlacedEggsAmount()));
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if(p.hasPermission("xstaff.hostevent") == false) {
            return;
        }

        if(setup.contains(p.getName()) == false) {
            return;
        }

        if(e.getBlockPlaced().getType() != Material.SKULL) {
            return;
        }

        EggHuntConfig.setPlacedEgg(e.getBlockPlaced().getLocation());
        p.sendMessage(MessageManager.getMsg("placedegg").replace("%placedeggs%", "" + EggHuntConfig.getPlacedEggsAmount()));

    }

    @Override
    public void setupEvent(Player player) {
        super.setupEvent(player);

        if(setup.contains(player.getName())) {
            player.sendMessage(MessageManager.getMsg("leavesetupevent") + displayname);
            setup.remove(player.getName());
            player.openInventory(new EventGUI(player).getInventory());
        } else {
            if(hasEmptyInv(player) == true) {
                player.sendMessage(MessageManager.getMsg("setupevent") + displayname);
                setup.add(player.getName());
                giveEggs(player);
            } else {
                player.sendMessage(MessageManager.getMsg("invnotemptysetup"));
                player.closeInventory();
            }
        }

    }

    @Override
    public void stopEvent(Player player) {
        super.stopEvent(player);

        if(setup.contains(player.getName())) {
            setup.remove(player.getName());
        }

    }

    @Override
    public void removeEventData(Player player) {
        super.removeEventData(player);

        if(isActive) {
            player.sendMessage(MessageManager.getMsg("errorisactive"));
            player.closeInventory();
            return;
        }

        ArrayList<Location> locs = new ArrayList<Location>();

        for(String locstring : EggHuntConfig.getKeys()) {

            if(locstring.contains("-") == false) {
                continue;
            }

            String[] cordsstring = locstring.split("-");
            Location loc = new Location(player.getWorld(), Double.parseDouble(cordsstring[0]), Double.parseDouble(cordsstring[1]), Double.parseDouble(cordsstring[2]));
            if(EggHuntConfig.isEgg(loc)) {
                locs.add(loc);
            }
        }

        for(Location loc : locs) {
            loc.getBlock().setType(Material.AIR);
        }

        EggHuntConfig.resetData();

        player.sendMessage(MessageManager.getMsg("resetevent"));

    }

    public void giveEggs(Player player) {
        for(int i = 1; i<= 4; i++) {
            player.getInventory().addItem(new Egg(i));
        }
    }

    public boolean hasEmptyInv(Player player) {
        for(ItemStack it : player.getInventory().getContents())
        {
            if(it != null) return false;
        }
        return true;
    }

}
