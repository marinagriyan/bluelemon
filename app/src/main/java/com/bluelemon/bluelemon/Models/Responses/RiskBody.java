package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiskBody {
    @SerializedName("ID")
    @Expose
    public Integer iD;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("review")
    @Expose
    public Integer review;
    @SerializedName("activity")
    @Expose
    public String activity;
    @SerializedName("aor")
    @Expose
    public String aor;
    @SerializedName("added")
    @Expose
    public String added;
    @SerializedName("addedBy")
    @Expose
    public String addedBy;
    @SerializedName("employees")
    @Expose
    public Integer employees;
    @SerializedName("visitors")
    @Expose
    public Integer visitors;
    @SerializedName("clientEmployees")
    @Expose
    public Integer clientEmployees;
    @SerializedName("contractors")
    @Expose
    public Integer contractors;
    @SerializedName("membersOfPublic")
    @Expose
    public Integer membersOfPublic;
    @SerializedName("others")
    @Expose
    public Integer others;
    @SerializedName("production")
    @Expose
    public Integer production;
    @SerializedName("deleted")
    @Expose
    public Boolean deleted;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created")
    @Expose
    public String created;
    @SerializedName("categoryID")
    @Expose
    public Integer categoryID;
    @SerializedName("categoryDescription")
    @Expose
    public String categoryDescription;
    @SerializedName("departmentID")
    @Expose
    public Integer departmentID;
    @SerializedName("departmentDescription")
    @Expose
    public String departmentDescription;
    @SerializedName("departmentCode")
    @Expose
    public String departmentCode;
    @SerializedName("siteID")
    @Expose
    public String siteID;
    @SerializedName("siteDescription")
    @Expose
    public String siteDescription;
    @SerializedName("siteCode")
    @Expose
    public String siteCode;
    @SerializedName("hazardCount")
    @Expose
    public Integer hazardCount;
    @SerializedName("outstandingActions")
    @Expose
    public Integer outstandingActions;
    @SerializedName("overdueActions")
    @Expose
    public Integer overdueActions;
    @SerializedName("underdueActions")
    @Expose
    public Integer underdueActions;
    @SerializedName("refno")
    @Expose
    public String refno;
    @SerializedName("files")
    @Expose
    public Integer files;
    @SerializedName("reviewDate")
    @Expose
    public String reviewDate;

    public Integer getiD() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public Integer getReview() {
        return review;
    }

    public String getActivity() {
        return activity;
    }

    public String getAor() {
        return aor;
    }

    public String getAdded() {
        return added;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public Integer getEmployees() {
        return employees;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public Integer getClientEmployees() {
        return clientEmployees;
    }

    public Integer getContractors() {
        return contractors;
    }

    public Integer getMembersOfPublic() {
        return membersOfPublic;
    }

    public Integer getOthers() {
        return others;
    }

    public Integer getProduction() {
        return production;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated() {
        return created;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public String getDepartmentDescription() {
        return departmentDescription;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public String getSiteID() {
        return siteID;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public Integer getHazardCount() {
        return hazardCount;
    }

    public Integer getOutstandingActions() {
        return outstandingActions;
    }

    public Integer getOverdueActions() {
        return overdueActions;
    }

    public Integer getUnderdueActions() {
        return underdueActions;
    }

    public String getRefno() {
        return refno;
    }

    public Integer getFiles() {
        return files;
    }

    public String getReviewDate() {
        return reviewDate;
    }
}
