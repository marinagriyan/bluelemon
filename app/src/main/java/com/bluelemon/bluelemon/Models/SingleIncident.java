package com.bluelemon.bluelemon.Models;

public class SingleIncident {
    private String title;
    private String category;
    private String measurement;
    private String type;
    private String reportedBy;
    private String investigator;

    public SingleIncident(String title, String category, String measurement, String type, String reportedBy, String investigator) {
        this.title = title;
        this.category = category;
        this.measurement = measurement;
        this.type = type;
        this.reportedBy = reportedBy;
        this.investigator = investigator;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getMeasurement() {
        return measurement;
    }

    public String getType() {
        return type;
    }

    public String getReportedBy() {
        return reportedBy;
    }

    public String getInvestigator() {
        return investigator;
    }
}
