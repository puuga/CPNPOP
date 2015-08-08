package com.appspace.cpnpop.apimanager;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class StoreLocationManager {
    public static final String API_LOCATION_URL = "http://128.199.133.166/roomlink/location.php?app_id=2";

    public static String API_LOCATION_URL_WITH_NAME(String name) {
        return API_LOCATION_URL + "&name=" + name;
    }

    public static String API_LOCATION_URL_WITH_ID(String id) {
        return API_LOCATION_URL + "&id=" + id;
    }
}
