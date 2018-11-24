package com.bluelemon.bluelemon.Models;

public class EquipmentModel {
    private String title;
    private String date;
    private String days;
    private String id;
    private String department;

    public EquipmentModel(String title, String date, String days, String id, String department) {
        this.title = title;
        this.date = date;
        this.days = days;
        this.id = id;
        this.department = department;
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

    public String getDepartment() {
        return department;
    }
}
