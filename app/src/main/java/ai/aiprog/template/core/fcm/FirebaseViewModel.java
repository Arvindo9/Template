package ai.aiprog.template.core.fcm;

import ai.aiprog.template.base.BaseViewModel;
import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.utils.rx.SchedulerProvider;

/**
 * Author : Arvindo Mondal
 * Created on 12-02-2020
 */
public class FirebaseViewModel extends BaseViewModel {

    public FirebaseViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

/*
    private void pushFcmToken(String token) {
        getCompositeDisposable().add(getDataManager()
                .pushFcmToken(token)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().newThread())
                .subscribe(response -> {
                    if (response != null){
                        //Saved succeful
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                }));
    }
    */
}
