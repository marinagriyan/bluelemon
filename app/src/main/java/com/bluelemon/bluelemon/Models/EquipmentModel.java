package com.bluelemon.bluelemon.Models;

public class EquipmentModel {
    private String title;
    private String date;
    private String days;
    private String id;
    private String building;

    public EquipmentModel(String title, String date, String days, String id, String building) {
        this.title = title;
        this.date = date;
        this.days = days;
        this.id = id;
        this.building = building;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDays() {
        return days;
    }

    public String getId() {
        return id;
    }

    public String getBuilding() {
        return building;
    }
}
