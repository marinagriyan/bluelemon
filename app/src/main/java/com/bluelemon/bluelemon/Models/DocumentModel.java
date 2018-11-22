package com.bluelemon.bluelemon.Models;

public class DocumentModel {
    private boolean status;
    private String title;
    private String date;
    private String category;
    private String location;

    public DocumentModel(boolean status, String title, String date, String category, String location) {
        this.status = status;
        this.title = title;
        this.date = date;
        this.category = category;
        this.location = location;
    }

    public boolean getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }
}
