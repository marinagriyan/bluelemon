package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Folder {

    @SerializedName("folderID")
    @Expose
    private Integer folderID;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("order")
    @Expose
    private Integer order;
    @SerializedName("parentID")
    @Expose
    private Object parentID;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("syncDateTime")
    @Expose
    private Object syncDateTime;
    @SerializedName("syncUserID")
    @Expose
    private Object syncUserID;
    @SerializedName("syncDeviceInfo")
    @Expose
    private Object syncDeviceInfo;
    @SerializedName("syncRowGuid")
    @Expose
    private Object syncRowGuid;
    @SerializedName("syncIsModified")
    @Expose
    private Boolean syncIsModified;

    public Integer getFolderID() {
        return folderID;
    }

    public String getName() {
        return name;
    }

    public Integer getOrder() {
        return order;
    }

    public Object getParentID() {
        return parentID;
    }

    public Integer getCount() {
        return count;
    }

    public Object getSyncDateTime() {
        return syncDateTime;
    }

    public Object getSyncUserID() {
        return syncUserID;
    }

    public Object getSyncDeviceInfo() {
        return syncDeviceInfo;
    }

    public Object getSyncRowGuid() {
        return syncRowGuid;
    }

    public Boolean getSyncIsModified() {
        return syncIsModified;
    }
}