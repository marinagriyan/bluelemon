package com.bluelemon.bluelemon.Models;

public class RiskModel {
    private String status;
    private String title;
    private String date;
    private String code;

    public RiskModel(String status, String title, String date, String code) {
        this.status = status;
        this.title = title;
        this.date = date;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getCode() {
        return code;
    }
}
