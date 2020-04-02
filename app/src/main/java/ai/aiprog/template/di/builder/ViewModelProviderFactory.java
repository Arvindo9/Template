package ai.aiprog.template.di.builder;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.ui.launcher.splash.SplashViewModel;
import ai.aiprog.template.ui.launcher.welcome.WelcomeViewModel;
import ai.aiprog.template.utils.rx.SchedulerProvider;
import ai.aiprog.template.utils.tasks.Task;
import ai.aiprog.template.utils.tasks.Tasks;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Author       : Arvindo Mondal
 * Created on   : 10-05-2019
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
@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory  {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;
    private final Task task;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider, Task task) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
        this.task = task;
    }

    @NotNull
    @Override
    public <T extends ViewModel> T create(@NotNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(Tasks.class)) {
            //noinspection unchecked
            return (T) new Tasks(dataManager,schedulerProvider);
        }
        else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager,schedulerProvider, task);
        }
        else if (modelClass.isAssignableFrom(WelcomeViewModel.class)) {
            //noinspection unchecked
            return (T) new WelcomeViewModel(dataManager,schedulerProvider, task);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
