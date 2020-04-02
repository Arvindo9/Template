package ai.aiprog.template.utils.tasks;

import ai.aiprog.template.data.model.db.flag.Flag;

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
public interface Task {

    interface TaskCallBack{
        void onResponse(String responseCode, Object object);

        void onError(Throwable throwable);
    }

    void getFlagApi();

    void getFlagApi(TaskCallBack viewModel);

    void saveFlagDb(List<Flag> list, String flagBaseUrl);

    void getFlagDb(TaskCallBack viewModel);

}
