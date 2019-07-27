package com.aiprog.template.ui.launcher.welcome;

import android.content.Context;

import com.aiprog.template.base.BaseViewModel;
import com.aiprog.template.data.DataManager;
import com.aiprog.template.utils.rx.SchedulerProvider;
import com.aiprog.template.utils.security.DeviceId;
import com.aiprog.template.utils.tasks.Task;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

import static com.aiprog.template.utils.AppConstants.APPLICATION_ID_EMPTY_API;
import static com.aiprog.template.utils.AppConstants.APPLICATION_ID_ERROR_API;
import static com.aiprog.template.utils.AppConstants.APPLICATION_ID_SAVE_EMPTY_DB;
import static com.aiprog.template.utils.AppConstants.APPLICATION_ID_SAVE_ERROR_DB;
import static com.aiprog.template.utils.AppConstants.FLAG_ERROR_API;
import static com.aiprog.template.utils.AppConstants.FLAG_ERROR_DB;

/**
 * Author       : Arvindo Mondal
 * Created on   : 13-05-2019
 * Email        : arvindo@aiprog.in
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.in
 */
public class WelcomeViewModel extends BaseViewModel<WelcomeNavigator> {

    private final Task task;
    private WeakReference<Context> contextReference;

    @Inject
    public WelcomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Task task) {
        super(dataManager, schedulerProvider);
        this.task = task;
    }

    void onStart(Context context) {
        contextReference = new WeakReference<>(context);
//        Tasks.getTasks().getFlagApi(this);
        task.getFlagApi();
        setLaunchMode();
    }

    private void setLaunchMode() {
        getDataManager().setLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);

    }

    //Resources data--------------------------------

    public void onBackClick(){
        getNavigator().onBackClick();
    }

    public void onNextClick(){
        getNavigator().onNextClick();
    }

    //Additional task--------------------------------

    @Override
    public void onResponse(int responseCode, Object object) {
        switch (responseCode){
            case FLAG_ERROR_DB:
            case FLAG_ERROR_API: break;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        getNavigator().handleError(throwable);
    }
}
