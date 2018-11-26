package com.bluelemon.bluelemon.Models;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class IncidentsModel implements Section<SingleIncident> {
    private String date;
    private List<SingleIncident> list;

    public IncidentsModel(String date, List<SingleIncident> list) {
        this.date = date;
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    @Override
    public List<SingleIncident> getChildItems() {
        return list;
    }
}
