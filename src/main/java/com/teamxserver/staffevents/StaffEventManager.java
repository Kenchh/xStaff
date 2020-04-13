package com.teamxserver.staffevents;

import com.teamxserver.staffevents.events.EggHunt;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class StaffEventManager {

    public static ArrayList<StaffEvent> staffevents = new ArrayList<StaffEvent>();

    public static void initStaffEvents() {
        addStaffEvent(new EggHunt());

    }

    public static StaffEvent getStaffEventByName(String name) {
        for(StaffEvent e : staffevents) {
            if(e.getName().equalsIgnoreCase(name)) {
                return e;
            }
        }
        return null;
    }

    public static StaffEvent getStaffEventByDisplayname(String displayname) {
        for(StaffEvent e : staffevents) {
            if(e.getDisplayname() == displayname) {
                return e;
            }
        }
        return null;
    }

    public static void addStaffEvent(StaffEvent e) {
        if(staffevents.contains(e) == false) {
            staffevents.add(e);
        }
    }

    public void removeStaffEvent(StaffEvent e) {
        if(staffevents.contains(e)) {
            staffevents.remove(e);
        }
    }

    public static boolean eventAlreadyRunning() {
        for(StaffEvent e : staffevents) {
            if(e.isActive) {
                return true;
            }
        }
        return false;
    }

}
