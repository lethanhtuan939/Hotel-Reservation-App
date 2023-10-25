package com.hotelreservationapp.model;

public class SettingList {

    private String title;
    private String subDes;
    private int icon;

    public SettingList() {
    }

    public SettingList(String title, String subDes, int icon) {
        this.title = title;
        this.subDes = subDes;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubDes() {
        return subDes;
    }

    public void setSubDes(String subDes) {
        this.subDes = subDes;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
