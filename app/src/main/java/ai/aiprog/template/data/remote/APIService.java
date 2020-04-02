package ai.aiprog.template.data.remote;


import ai.aiprog.template.data.model.apis.flag.FlagApi;

import io.reactivex.Flowable;
import retrofit2.http.GET;

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
