package com.aiprog.template.data.remote;


import com.aiprog.template.data.model.apis.flag.FlagApi;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Author       : Arvindo Mondal
 * Created on   : 23-12-2018
 */
public interface APIService {
    @GET("template/api/Flag/GetCountryFlag")
    Flowable<FlagApi> countryCode();

/*
    @Multipart
    @POST("abc")
    Flowable<String> mediaUploadApi(
            @Part("Data") RequestBody data,
            @Part MultipartBody.Part file
    );
*/
/*
    @GET("/api/MobileApi/TaksCompleted?")
    Call<String> setUserProfileData(
            @Query("mobile") String mobile,
            @Query("TaskId") String taskId
    );

    @FormUrlEncoded
    @POST("/api/MobileApi/GetDetails")
    Call<String> details(
            @Field("Mobile") String mobile,
            @Field("type") String walletType,
            @Field("PageIndex") String pageIndex,
            @Field("TopCount") String topCount
    );
    */

}
