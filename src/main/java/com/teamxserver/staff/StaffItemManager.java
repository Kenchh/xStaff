package com.teamxserver.staff;

import java.util.ArrayList;

public class StaffItemManager {

    public static ArrayList<StaffItem> staffitems = new ArrayList<StaffItem>();

    public static void init() {

    }

    public static void addStaffItem(StaffItem staffItem) {
        if(staffitems.contains(staffItem)) {
            staffitems.remove(staffItem);
        } else {
            staffitems.add(staffItem);
        }
    }

}
