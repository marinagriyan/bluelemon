package com.bluelemon.bluelemon.Models;

public class Notification {
    private String name;
    private String content;
    private String date;

    public Notification(String name, String content, String date) {
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }
}
