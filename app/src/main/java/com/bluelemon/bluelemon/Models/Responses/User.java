package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("userID")
    @Expose
    private String userID;
    @SerializedName("passwordFormat")
    @Expose
    private Integer passwordFormat;
    @SerializedName("mobilePin")
    @Expose
    private Object mobilePin;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("loweredEmail")
    @Expose
    private Object loweredEmail;
    @SerializedName("passwordQuestion")
    @Expose
    private Object passwordQuestion;
    @SerializedName("passwordAnswer")
    @Expose
    private Object passwordAnswer;
    @SerializedName("isApproved")
    @Expose
    private Boolean isApproved;
    @SerializedName("isLockedOut")
    @Expose
    private Boolean isLockedOut;
    @SerializedName("createDate")
    @Expose
    private String createDate;
    @SerializedName("lastLoginDate")
    @Expose
    private String lastLoginDate;
    @SerializedName("lastPasswordChangedDate")
    @Expose
    private String lastPasswordChangedDate;
    @SerializedName("lastLockoutDate")
    @Expose
    private String lastLockoutDate;
    @SerializedName("failedPasswordAttemptCount")
    @Expose
    private Integer failedPasswordAttemptCount;
    @SerializedName("failedPasswordAttemptWindowStart")
    @Expose
    private String failedPasswordAttemptWindowStart;
    @SerializedName("failedPasswordAnswerAttemptCount")
    @Expose
    private Integer failedPasswordAnswerAttemptCount;
    @SerializedName("failedPasswordAnswerAttemptWindowStart")
    @Expose
    private String failedPasswordAnswerAttemptWindowStart;
    @SerializedName("comment")
    @Expose
    private Object comment;
    @SerializedName("applicationID")
    @Expose
    private String applicationID;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("mobileAlias")
    @Expose
    private String mobileAlias;
    @SerializedName("isAnonymous")
    @Expose
    private Boolean isAnonymous;
    @SerializedName("lastActivityDate")
    @Expose
    private String lastActivityDate;
    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("applicationName")
    @Expose
    private String applicationName;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("roles")
    @Expose
    private List<String> roles = null;
    @SerializedName("expires")
    @Expose
    private String expires;

    public String getUserID() {
        return userID;
    }

    public Integer getPasswordFormat() {
        return passwordFormat;
    }

    public Object getMobilePin() {
        return mobilePin;
    }

    public String getEmail() {
        return email;
    }

    public Object getLoweredEmail() {
        return loweredEmail;
    }

    public Object getPasswordQuestion() {
        return passwordQuestion;
    }

    public Object getPasswordAnswer() {
        return passwordAnswer;
    }

    public Boolean getApproved() {
        return isApproved;
    }

    public Boolean getLockedOut() {
        return isLockedOut;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public String getLastPasswordChangedDate() {
        return lastPasswordChangedDate;
    }

    public String getLastLockoutDate() {
        return lastLockoutDate;
    }

    public Integer getFailedPasswordAttemptCount() {
        return failedPasswordAttemptCount;
    }

    public String getFailedPasswordAttemptWindowStart() {
        return failedPasswordAttemptWindowStart;
    }

    public Integer getFailedPasswordAnswerAttemptCount() {
        return failedPasswordAnswerAttemptCount;
    }

    public String getFailedPasswordAnswerAttemptWindowStart() {
        return failedPasswordAnswerAttemptWindowStart;
    }

    public Object getComment() {
        return comment;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public String getUserName() {
        return userName;
    }

    public String getMobileAlias() {
        return mobileAlias;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public String getLastActivityDate() {
        return lastActivityDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getPhone() {
        return phone;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getExpires() {
        return expires;
    }
}
