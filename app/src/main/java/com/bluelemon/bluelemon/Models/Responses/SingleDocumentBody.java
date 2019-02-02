package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SingleDocumentBody {
    @SerializedName("uploadedDate")
    @Expose
    private Object uploadedDate;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("itemID")
    @Expose
    private Integer itemID;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("fullPath")
    @Expose
    private Object fullPath;
    @SerializedName("currentDocumentID")
    @Expose
    private Integer currentDocumentID;
    @SerializedName("nextRenewel")
    @Expose
    private String nextRenewel;
    @SerializedName("comments")
    @Expose
    private Object comments;
    @SerializedName("documentCategory")
    @Expose
    private Integer documentCategory;
    @SerializedName("isExpired")
    @Expose
    private Boolean isExpired;
    @SerializedName("categorySpec")
    @Expose
    private Integer categorySpec;
    @SerializedName("documentID")
    @Expose
    private Integer documentID;
    @SerializedName("categoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("renewalFrequency")
    @Expose
    private Integer renewalFrequency;
    @SerializedName("siteId")
    @Expose
    private String siteId;
    @SerializedName("live")
    @Expose
    private Boolean live;
    @SerializedName("documentName")
    @Expose
    private String documentName;
    @SerializedName("site")
    @Expose
    private String site;
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

    public Object getUploadedDate() {
        return uploadedDate;
    }

    public Object getUserName() {
        return userName;
    }

    public Integer getItemID() {
        return itemID;
    }

    public Object getFileName() {
        return fileName;
    }

    public Object getFullPath() {
        return fullPath;
    }

    public Integer getCurrentDocumentID() {
        return currentDocumentID;
    }

    public String getNextRenewel() {
        return nextRenewel;
    }

    public Object getComments() {
        return comments;
    }

    public Integer getDocumentCategory() {
        return documentCategory;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public Integer getCategorySpec() {
        return categorySpec;
    }

    public Integer getDocumentID() {
        return documentID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public Integer getRenewalFrequency() {
        return renewalFrequency;
    }

    public String getSiteId() {
        return siteId;
    }

    public Boolean getLive() {
        return live;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getSite() {
        return site;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getSyncDateTime() {
        return syncDateTime;
    }

    public Object getSyncUserID() {
        return syncUserID;
    }

    public String getSyncDeviceInfo() {
        return syncDeviceInfo;
    }

    public Object getSyncRowGuid() {
        return syncRowGuid;
    }

    public Boolean getSyncIsModified() {
        return syncIsModified;
    }
}
