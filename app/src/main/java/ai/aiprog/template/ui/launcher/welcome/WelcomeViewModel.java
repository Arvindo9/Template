package ai.aiprog.template.ui.launcher.welcome;

import android.content.Context;

import ai.aiprog.template.base.BaseViewModel;
import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.utils.rx.SchedulerProvider;
import ai.aiprog.template.utils.tasks.Task;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

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

}
