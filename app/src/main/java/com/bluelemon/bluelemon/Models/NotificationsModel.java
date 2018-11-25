package com.bluelemon.bluelemon.Models;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class NotificationsModel implements Section<Notification> {
    private String date;
    private List<Notification> list;

    public NotificationsModel(String date, List<Notification> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }


    @Override
    public List<Notification> getChildItems() {
        return list;
    }
}
