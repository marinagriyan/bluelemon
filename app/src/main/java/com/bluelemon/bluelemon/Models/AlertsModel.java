package com.bluelemon.bluelemon.Models;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

public class AlertsModel implements Section<SingleAlert> {
   private String date;
   private List<SingleAlert> alerts;

   public AlertsModel(String date, List<SingleAlert> alerts) {
      this.date = date;
      this.alerts = alerts;
   }

   public String getDate() {
      return date;
   }

    @Override
    public List<SingleAlert> getChildItems() {
        return alerts;
    }
}
