package com.bluelemon.bluelemon.Models;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class CoursesModel implements Section<SingleCourse> {
    private String date;
    private List<SingleCourse> list;

    public CoursesModel(String date, List<SingleCourse> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    @Override
    public List<SingleCourse> getChildItems() {
        return list;
    }
}
