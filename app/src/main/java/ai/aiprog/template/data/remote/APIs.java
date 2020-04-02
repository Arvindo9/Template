package ai.aiprog.template.data.remote;


import ai.aiprog.template.data.model.apis.flag.FlagApi;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Flowable;
import retrofit2.Retrofit;

/**
 * Author       : Arvindo Mondal
 * Created on   : 24-12-2018
 * Email        : arvindomondal@gmail.com
 */
public class APIs implements APIService {

    private final APIService apiService;
    private final APIService apiServiceUtils;

    @Inject
    public APIs(@Named("APP") Retrofit retrofit, @Named("UTILS") Retrofit retrofitUtils) {
        this.apiService = retrofit.create(APIService.class);
        this.apiServiceUtils = retrofitUtils.create(APIService.class);
    }

    @Override
    public Flowable<FlagApi> countryCode() {
        return apiServiceUtils.countryCode();
    }
}
