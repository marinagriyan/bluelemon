package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentBody {
    @SerializedName("categoryName")
    @Expose
    private String categoryName;
    @SerializedName("categoryID")
    @Expose
    private Integer categoryID;
    @SerializedName("categorySpec")
    @Expose
    private Integer categorySpec;
    @SerializedName("documentID")
    @Expose
    private Integer documentID;
    @SerializedName("documentName")
    @Expose
    private String documentName;
    @SerializedName("renewalFrequency")
    @Expose
    private Integer renewalFrequency;
    @SerializedName("documentCategory")
    @Expose
    private Integer documentCategory;
    @SerializedName("siteID")
    @Expose
    private String siteID;
    @SerializedName("site")
    @Expose
    private String site;
    @SerializedName("userName")
    @Expose
    private Object userName;
    @SerializedName("itemID")
    @Expose
    private Integer itemID;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("fullPath")
    @Expose
    private String fullPath;
    @SerializedName("uploadedDate")
    @Expose
    private String uploadedDate;
    @SerializedName("currentDocumentID")
    @Expose
    private Integer currentDocumentID;
    @SerializedName("live")
    @Expose
    private Boolean live;
    @SerializedName("nextRenewel")
    @Expose
    private Object nextRenewel;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("currentDocument")
    @Expose
    private Integer currentDocument;
    @SerializedName("uploadedBy")
    @Expose
    private Object uploadedBy;
    @SerializedName("documentDate")
    @Expose
    private String documentDate;
    @SerializedName("isExpired")
    @Expose
    private Boolean isExpired;
    @SerializedName("chartValue")
    @Expose
    private String chartValue;

    public String getCategoryName() {
        return categoryName;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public Integer getCategorySpec() {
        return categorySpec;
    }

    public Integer getDocumentID() {
        return documentID;
    }

    public String getDocumentName() {
        return documentName;
    }

    public Integer getRenewalFrequency() {
        return renewalFrequency;
    }

    public Integer getDocumentCategory() {
        return documentCategory;
    }

    public String getSiteID() {
        return siteID;
    }

    public String getSite() {
        return site;
    }

    public Object getUserName() {
        return userName;
    }

    public Integer getItemID() {
        return itemID;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String getUploadedDate() {
        return uploadedDate;
    }

    public Integer getCurrentDocumentID() {
        return currentDocumentID;
    }

    public Boolean getLive() {
        return live;
    }

    public Object getNextRenewel() {
        return nextRenewel;
    }

    public String getComments() {
        return comments;
    }

    public Integer getCurrentDocument() {
        return currentDocument;
    }

    public Object getUploadedBy() {
        return uploadedBy;
    }

    public String getDocumentDate() {
        return documentDate;
    }

    public Boolean getExpired() {
        return isExpired;
    }

    public String getChartValue() {
        return chartValue;
    }
}
