package com.bluelemon.bluelemon.Models;

public class PlanMember {
    private String name;
    private String status;
    private String date;

    public PlanMember(String name, String status, String date) {
        this.name = name;
        this.status = status;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }
}
