package com.bluelemon.bluelemon.Models;

public class SingleCourse {
    private String title;
    private String category;

    public SingleCourse(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
