package com.aiprog.template.data.remote;


import com.aiprog.template.data.model.apis.flag.FlagApi;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Author       : Arvindo Mondal
 * Created on   : 23-12-2018
 */
public interface APIService {
    @GET("dating/api/Flag/GetCountryFlag")
    Flowable<FlagApi> countryCode();

/*
    @Multipart
    @POST("abc")
    Flowable<String> mediaUploadApi(
            @Part("Data") RequestBody data,
            @Part MultipartBody.Part file
    );
*/

}
