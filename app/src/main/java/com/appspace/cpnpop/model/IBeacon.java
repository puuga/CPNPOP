package com.appspace.cpnpop.model;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class IBeacon {
    private int eeiDeviceId;
    private String nameEEI;
    private String uuid;
    private String major;
    private String minor;
    private String eeiMac;
    private String deviceName;

    private IBeacon() {
    }

    private IBeacon(int eeiDeviceId, String nameEEI, String uuid, String major, String minor, String eeiMac, String deviceName) {
        this.setEeiDeviceId(eeiDeviceId);
        this.setNameEEI(nameEEI);
        this.setUuid(uuid);
        this.setMajor(major);
        this.setMinor(minor);
        this.setEeiMac(eeiMac);
        this.setDeviceName(deviceName);
    }

    public static IBeacon createIBeacon() {
        return new IBeacon();
    }

    public static IBeacon createIBeacon(int eeiDeviceId, String nameEEI, String uuid, String major, String minor, String eeiMac, String deviceName) {
        return new IBeacon(eeiDeviceId, nameEEI, uuid, major, minor, eeiMac, deviceName);
    }

    public int getEeiDeviceId() {
        return eeiDeviceId;
    }

    public void setEeiDeviceId(int eeiDeviceId) {
        this.eeiDeviceId = eeiDeviceId;
    }

    public String getNameEEI() {
        return nameEEI;
    }

    public void setNameEEI(String nameEEI) {
        this.nameEEI = nameEEI;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    public String getEeiMac() {
        return eeiMac;
    }

    public void setEeiMac(String eeiMac) {
        this.eeiMac = eeiMac;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
