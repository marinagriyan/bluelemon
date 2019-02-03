package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EquipmentFoldersBody {
    @SerializedName("folders")
    @Expose
    private List<Folder> folders = null;
    @SerializedName("breadcrumbs")
    @Expose
    private List<Object> breadcrumbs = null;
    @SerializedName("equipments")
    @Expose
    private List<EquipmentBody> equipments = null;

    public List<Folder> getFolders() {
        return folders;
    }

    public List<Object> getBreadcrumbs() {
        return breadcrumbs;
    }

    public List<EquipmentBody> getEquipments() {
        return equipments;
    }
}
