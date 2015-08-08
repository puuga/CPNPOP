package com.appspace.cpnpop.geo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class StoreLocation {
    public static  final String LOCATION_ID = "location_id";
    public String id;
    public String name;
    public double latitude;
    public double longitude;
    public float radius;

    public StoreLocation(String id, String name, double latitude, double longitude, float radius) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public static List<StoreLocation> getStoreLocation() {
        List<StoreLocation> list = new ArrayList<>();
        list.add(new StoreLocation("1", "CSIT", 16.742370, 100.193738, 200f));
        list.add(new StoreLocation("2", "NU Dorm 6", 16.747196, 100.187640, 200f));
        list.add(new StoreLocation("3", "TonKla Coffee", 16.753502, 100.196456, 100f));

        return list;
    }
}
