package ai.aiprog.template.di.builder;

import ai.aiprog.template.ui.launcher.splash.SplashActivity;
import ai.aiprog.template.ui.launcher.welcome.WelcomeActivity;
import ai.aiprog.template.ui.launcher.welcome.WelcomeModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

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
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector()
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = WelcomeModule.class)
    abstract WelcomeActivity bindWelcomeActivity();

}
