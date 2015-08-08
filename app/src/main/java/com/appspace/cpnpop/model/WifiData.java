package com.appspace.cpnpop.model;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class WifiData {
    private String ssid;
    private String bSsid;
    private String capabilities;
    private int level;
    private int frequency;
    private int calLevel;

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBSsid() {
        return bSsid;
    }

    public void setBSsid(String bSsid) {
        this.bSsid = bSsid;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getCalLevel() {
        return calLevel;
    }

    public void setCalLevel(int calLevel) {
        this.calLevel = calLevel;
    }

    public String toString() {
        String temp = "SSID:" + getSsid();
        temp += " BSSID:" + getBSsid();
        temp += " capabilities:" + getCapabilities();
        temp += " level:" + getLevel();
        temp += " frequency:" + getFrequency();
        temp += " calLevel:" + getCalLevel();
        return temp;
    }
}
