package com.bluelemon.bluelemon.Models.Responses;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EquipmentBody {
    @SerializedName("equipmentID")
    @Expose
    private String equipmentID;
    @SerializedName("equipmentInstanceReference")
    @Expose
    private Integer equipmentInstanceReference;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private Object endDate;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("inUse")
    @Expose
    private Boolean inUse;
    @SerializedName("theatreID")
    @Expose
    private String theatreID;
    @SerializedName("departmentID")
    @Expose
    private String departmentID;
    @SerializedName("checkRequirements")
    @Expose
    private String checkRequirements;
    @SerializedName("frequency")
    @Expose
    private Integer frequency;
    @SerializedName("awaitingRepair")
    @Expose
    private Boolean awaitingRepair;
    @SerializedName("quarantined")
    @Expose
    private Boolean quarantined;
    @SerializedName("lastCheckDate")
    @Expose
    private String lastCheckDate;
    @SerializedName("make")
    @Expose
    private String make;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("serial")
    @Expose
    private String serial;
    @SerializedName("refno")
    @Expose
    private String refno;
    @SerializedName("retire")
    @Expose
    private Boolean retire;
    @SerializedName("checkDate")
    @Expose
    private String checkDate;
    @SerializedName("departmentName")
    @Expose
    private String departmentName;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("categoryID")
    @Expose
    private String categoryID;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("folderID")
    @Expose
    private Integer folderID;
    @SerializedName("chartValue")
    @Expose
    private String chartValue;

    public String getEquipmentID() {
        return equipmentID;
    }

    public Integer getEquipmentInstanceReference() {
        return equipmentInstanceReference;
    }

    public String getComments() {
        return comments;
    }

    public String getStartDate() {
        return startDate;
    }

    public Object getEndDate() {
        return endDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public String getTheatreID() {
        return theatreID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public String getCheckRequirements() {
        return checkRequirements;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public Boolean getAwaitingRepair() {
        return awaitingRepair;
    }

    public Boolean getQuarantined() {
        return quarantined;
    }

    public String getLastCheckDate() {
        return lastCheckDate;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getSerial() {
        return serial;
    }

    public String getRefno() {
        return refno;
    }

    public Boolean getRetire() {
        return retire;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getUserName() {
        return userName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getCategory() {
        return category;
    }

    public Integer getFolderID() {
        return folderID;
    }

    public String getChartValue() {
        return chartValue;
    }
}
