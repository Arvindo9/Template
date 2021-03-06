package ai.aiprog.template.core.dialogs.flag.module;

import ai.aiprog.template.core.dialogs.flag.adapter.FlagAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Author       : Arvindo Mondal
 * Created on   : 24-05-2019
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
public class FlagModule {

    @Provides
    FlagAdapter provideFlagAdapter() {
        return new FlagAdapter(new ArrayList<>());
    }
}
