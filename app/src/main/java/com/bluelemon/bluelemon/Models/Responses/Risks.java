package com.bluelemon.bluelemon.Models.Responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Risks {
    @SerializedName("StatusCode")
    @Expose
    public Integer statusCode;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Body")
    @Expose
    public List<RiskBody> body = null;

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public List<RiskBody> getBody() {
        return body;
    }
}
