package com.aiprog.template.core.dialogs.flag;

import com.aiprog.template.data.model.db.flag.Flag;

import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 22-05-2019
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
public interface FlagNavigator {

    void onBackButtonClick();

    void onClearButtonClick();

    void onSearchClick();

    void updateListView(List<Flag> model);

    void handleError(Throwable throwable);

}
