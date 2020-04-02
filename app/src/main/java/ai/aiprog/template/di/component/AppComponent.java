package ai.aiprog.template.di.component;


import android.app.Application;

import ai.aiprog.template.base.BaseApplication;
import ai.aiprog.template.di.builder.ActivityBuilder;
import ai.aiprog.template.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
 * Email        : arvindo@aiprog.ai
 * Company      : AIPROG
 * Designation  : Programmer
 * About        : I am a human can only think, I can't be a person like machine which have lots of memory and knowledge.
 * Quote        : No one can measure limit of stupidity but stupid things bring revolutions
 * Strength     : Never give up
 * Motto        : To be known as great Mathematician
 * Skills       : Algorithms and logic
 * Website      : www.aiprog.ai
 */
@Singleton
@Component(
        modules = {
            AndroidInjectionModule.class,
            AppModule.class,
            ActivityBuilder.class
    }
)
public interface AppComponent {

    void inject(BaseApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
