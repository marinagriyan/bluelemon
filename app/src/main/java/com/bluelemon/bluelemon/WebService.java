package com.bluelemon.bluelemon;


import com.bluelemon.bluelemon.Models.Responses.Accidents;
import com.bluelemon.bluelemon.Models.Responses.Account;
import com.bluelemon.bluelemon.Models.Responses.Courses;
import com.bluelemon.bluelemon.Models.Responses.DocumentCategories;
import com.bluelemon.bluelemon.Models.Responses.Documents;
import com.bluelemon.bluelemon.Models.Responses.EquipmentBody;
import com.bluelemon.bluelemon.Models.Responses.EquipmentFolders;
import com.bluelemon.bluelemon.Models.Responses.Risks;
import com.bluelemon.bluelemon.Models.Responses.SingleAccident;
import com.bluelemon.bluelemon.Models.Responses.SingleDocument;
import com.bluelemon.bluelemon.Models.Responses.UpdatePassword;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebService {
    @FormUrlEncoded
    @POST("/account/login")
    Call<JsonObject> login(@Header ("Origin") String origin,
                           @Header ("Device") String device,
                           @Field("username") String username,
                           @Field("password") String password);

    @POST("/Account/GetUserData")
    Call<Account> getAccount(@Header("Origin") String origin,
                             @Header("Authorization") String token,
                             @Body JsonObject body);

    @POST("/Account/UpdatePassword")
    Call<UpdatePassword> updatePassword(@Header("Origin") String origin,
                                        @Header("Authorization") String token,
                                        @Body JsonObject body);

    @POST("/Accident/CreateOrUpdate")
    Call<JsonObject> createAccident(@Header("Origin") String origin,
                                    @Header("Authorization") String token,
                                    @Body JsonObject body);

    @POST("/Accident/GetAccidentsList")
    Call<Accidents> getAccidentsList(@Header("Origin") String origin,
                                     @Header("Authorization") String token,
                                     @Body JsonObject body);

    @POST("/Accident/GetAccident")
    Call<SingleAccident> getAccident(@Header("Origin") String origin,
                                     @Header("Authorization") String token,
                                     @Body JsonObject body);

    @POST("/document")
    Call<Documents> getDocuments(@Header("Origin") String origin,
                        @Header("Authorization") String token,
                        @Body JsonObject body);

    @POST("/document/Get")
    Call<SingleDocument> getSingleDocument(@Header("Origin") String origin,
                                           @Header("Authorization") String token,
                                           @Body JsonObject body);

    @POST("/document/document")
    Call<SingleDocument> createDocument(@Header("Origin") String origin,
                        @Header("Authorization") String token,
                        @Body JsonObject body);

    @POST("/document/GetAllCategories")
    Call<DocumentCategories> getDocumentCategories(@Header("Origin") String origin,
                                                   @Header("Authorization") String token);

    @GET("/document/download")
    Call<ResponseBody> downloadDocument(@Header("Origin") String origin,
                                        @Header("Authorization") String token,
                                        @Query("itemID") int id);

    @POST("/certificate")
    Call<Documents> getCertificates(@Header("Origin") String origin,
                                 @Header("Authorization") String token,
                                 @Body JsonObject body);

    @POST("/certificate/Get")
    Call<SingleDocument> getSingleCertificate(@Header("Origin") String origin,
                                           @Header("Authorization") String token,
                                           @Body JsonObject body);

    @POST("/certificate/certificate")
    Call<SingleDocument> createCertificate(@Header("Origin") String origin,
                                 @Header("Authorization") String token,
                                 @Body JsonObject body);

    @POST("/certificate/GetAllCategories")
    Call<DocumentCategories> getCertificateCategories(@Header("Origin") String origin,
                                                   @Header("Authorization") String token);

    @POST("/Risk")
    Call<Risks> getRisks(@Header("Origin") String origin,
                             @Header("Authorization") String token,
                             @Body JsonObject body);

    @GET("/Risk/downloadRisk")
    Call<ResponseBody> downloadRisk(@Header("Origin") String origin,
                                    @Header("Authorization") String token,
                                    @Query("riskID") int id);

    @POST("/equipment")
    Call<List<EquipmentBody>> getEquipment(@Header("Origin") String origin,
                                           @Header("Authorization") String token);

    @POST("/equipment/Folder")
    Call<JsonObject> createFolder(@Header("Origin") String origin,
                                  @Header("Authorization") String token);

    @POST("/equipment/GetFolders")
    Call<EquipmentFolders> getFolders(@Header("Origin") String origin,
                                      @Header("Authorization") String token,
                                      @Body JsonObject body);

    @POST("/equipment/instance")
    Call<JsonObject> createEquipment(@Header("Origin") String origin,
                                  @Header("Authorization") String token,
                                  @Body JsonObject body);

    @POST("/training/GetStaffCourses")
    Call<Courses> getCourses(@Header("Origin") String origin,
                             @Header("Authorization") String token,
                             @Body JsonObject body);

}
