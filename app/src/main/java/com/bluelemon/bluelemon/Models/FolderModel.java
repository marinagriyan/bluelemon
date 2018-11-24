package com.bluelemon.bluelemon.Models;

public class FolderModel {
    private String title;
    private String count;

    public FolderModel(String title, String count) {
        this.title = title;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
