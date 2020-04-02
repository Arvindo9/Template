package ai.aiprog.template.core.dialogs.flag.module;

import ai.aiprog.template.core.dialogs.flag.FlagDialog;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Author       : Arvindo Mondal
 * Created on   : 24-05-2019
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
@Module
public abstract class FlagProvider {

    @ContributesAndroidInjector(modules = FlagModule.class)
    abstract FlagDialog provideFlagDialog();
}
