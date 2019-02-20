package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountBody {
    @SerializedName("User")
    @Expose
    private User user;
    @SerializedName("Roles")
    @Expose
    private List<String> roles = null;

    public User getUser() {
        return user;
    }

    public List<String> getRoles() {
        return roles;
    }

}
