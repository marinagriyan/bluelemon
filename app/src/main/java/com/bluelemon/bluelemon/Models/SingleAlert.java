package com.bluelemon.bluelemon.Models;

public class SingleAlert {
    private String title;
    private String time;
    private String type;
    private String department;

    public SingleAlert(String title, String time, String type, String department) {
        this.title = title;
        this.time = time;
        this.type = type;
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDepartment() {
        return department;
    }
}
