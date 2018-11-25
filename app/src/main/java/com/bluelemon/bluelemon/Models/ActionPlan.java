package com.bluelemon.bluelemon.Models;

import java.util.List;

public class ActionPlan {
    private String title;
    private String numberofPeople;
    private List<PlanMember> members;

    public ActionPlan(String title, String numberofPeople, List<PlanMember> members) {
        this.title = title;
        this.numberofPeople = numberofPeople;
        this.members = members;
    }

    public String getTitle() {
        return title;
    }

    public String getNumberofPeople() {
        return numberofPeople;
    }

    public List<PlanMember> getMembers() {
        return members;
    }
}
