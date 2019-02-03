package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CourseBody {
    @SerializedName("staffID")
    @Expose
    private Integer staffID;
    @SerializedName("courseCompleted")
    @Expose
    private String courseCompleted;
    @SerializedName("renewelDate")
    @Expose
    private String renewelDate;
    @SerializedName("courseID")
    @Expose
    private Integer courseID;
    @SerializedName("courseTitle")
    @Expose
    private String courseTitle;
    @SerializedName("isRequired")
    @Expose
    private Boolean isRequired;
    @SerializedName("status")
    @Expose
    private String status;

    public Integer getStaffID() {
        return staffID;
    }

    public String getCourseCompleted() {
        return courseCompleted;
    }

    public String getRenewelDate() {
        return renewelDate;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public Boolean getRequired() {
        return isRequired;
    }

    public String getStatus() {
        return status;
    }
}
