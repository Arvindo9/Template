package com.aiprog.template.ui.launcher.welcome;


import dagger.Module;
import dagger.Provides;

/**
 * Author       : Arvindo Mondal
 * Created on   : 25-12-2018
 * Email        : arvindomondal@gmail.com
 */
@Module
public class WelcomeModule {

    @Provides
    WelcomeAdapter provideWelcomePagerAdapter(WelcomeActivity activity) {
        return new WelcomeAdapter(activity);
    }
}
