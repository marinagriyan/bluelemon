package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentCategory {
    @SerializedName("categoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("categorySpec")
    @Expose
    private Integer categorySpec;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("syncDateTime")
    @Expose
    private String syncDateTime;
    @SerializedName("syncUserID")
    @Expose
    private Object syncUserID;
    @SerializedName("syncDeviceInfo")
    @Expose
    private String syncDeviceInfo;
    @SerializedName("syncRowGuid")
    @Expose
    private Object syncRowGuid;
    @SerializedName("syncIsModified")
    @Expose
    private Boolean syncIsModified;

    public Integer getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
