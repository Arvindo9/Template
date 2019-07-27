package com.aiprog.template.ui.launcher.splash;

import android.os.Handler;

import com.aiprog.template.base.BaseViewModel;
import com.aiprog.template.data.DataManager;
import com.aiprog.template.utils.rx.SchedulerProvider;
import com.aiprog.template.utils.tasks.Task;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
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
public class SplashViewModel extends BaseViewModel<SplashNavigator> {

    private final Task task;
    public boolean isPermissionGranted = false;
    private boolean isAppReady = false;
    private boolean isWaitComplete = false;
    private int taskId = 0;
    private static int SPLASH_TIME_OUT = 3000;

    private final int LOGGED_IN_MODE_FIRST_TIME = 0;
    private final int LOGGED_IN_MODE_LOGGED_OUT = 1;
    private final int LOGGED_IN_MODE_REGISTRATION_PHASE_1 = 2;
    private final int LOGGED_IN_MODE_REGISTRATION_PHASE_2 = 3;
    private final int LOGGED_IN_MODE_REGISTRATION_PHASE_3 = 4;
    private final int LOGGED_IN_MODE_REGISTRATION_PHASE_4 = 5;
    private final int LOGGED_IN_MODE_LOGGED_IN_SECURITY_IMAGE = 6;
    private final int LOGGED_IN_MODE_LOGGED_IN_PROFILE_IMAGE = 7;
    private final int LOGGED_IN_MODE_LOGGED_IN_HOME = 8;
    private final int LOGGED_IN_MODE_UPDATE_PLAY_STORE = 9;

    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider, Task task) {
        super(dataManager, schedulerProvider);
        this.task = task;
    }

    void onStart(String currentVersion) {
        isAppReady = false;
        isWaitComplete = false;

        waitForThreadComplete();

        decideNextScreen();
    }

    private void decideNextScreen(){
        if(getDataManager().getLoggedInMode() ==
                DataManager.LoggedInMode.LOGGED_IN_MODE_FIRST_TIME.getType()){
            taskId = LOGGED_IN_MODE_FIRST_TIME;
        }
        else if(getDataManager().getLoggedInMode() ==
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()){
            taskId = LOGGED_IN_MODE_LOGGED_OUT;
        }
        else if(getDataManager().getLoggedInMode() ==
                DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_IN_HOME.getType()){
            taskId = LOGGED_IN_MODE_LOGGED_IN_HOME;
        }

        isAppReady = true;
        onNextScreenDecided();
    }

    private void onNextScreenDecided(){
        if(isWaitComplete && isAppReady) {
            switch (taskId) {
                case LOGGED_IN_MODE_FIRST_TIME:
                    getNavigator().openWelcomeScreen();
                    break;
                case LOGGED_IN_MODE_LOGGED_OUT:
                    getNavigator().openLoginActivity();
                    break;
                case LOGGED_IN_MODE_LOGGED_IN_HOME:
                    getNavigator().openHomeActivity();
                    break;
            }
        }
    }

    private void waitForThreadComplete(){
        new Handler().postDelayed(() -> {
            isWaitComplete = true;
            onNextScreenDecided();
        }, SPLASH_TIME_OUT);
    }

    //Additional task--------------------------------------
}
