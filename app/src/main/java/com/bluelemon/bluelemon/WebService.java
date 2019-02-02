package com.bluelemon.bluelemon;


import com.bluelemon.bluelemon.Models.CreateAccident;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public interface WebService {

    @FormUrlEncoded
    @POST("/account/login")
    Call<JsonObject> login(@Header ("Origin") String origin,
                           @Field("username") String username,
                           @Field("password") String password);

    @POST("/Accident/CreateOrUpdate")
    Call<JsonObject> createAccident(@Header("Origin") String origin,
                                    @Header("Authorization") String token,
                                    @Body CreateAccident body);

    @POST("Accident/GetAccidentsList")
    Call<JsonObject> getAccidentsList(@Header("Origin") String origin,
                                      @Header("Authorization") String token);

}