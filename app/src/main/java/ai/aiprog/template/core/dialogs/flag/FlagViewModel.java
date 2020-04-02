package ai.aiprog.template.core.dialogs.flag;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.MutableLiveData;

import ai.aiprog.template.base.BaseViewModel;
import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.data.model.db.flag.Flag;
import ai.aiprog.template.utils.rx.SchedulerProvider;

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
public class FlagViewModel extends BaseViewModel<FlagNavigator> {


    public final ObservableList<Flag> flagObservableList = new ObservableArrayList<>();
    private final MutableLiveData<List<Flag>> flagLiveData;

    public FlagViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        flagLiveData = new MutableLiveData<>();
    }

    //List view---------------------------------------
    public ObservableList<Flag> getFlagObservableList() {
        return flagObservableList;
    }

    public void addFlagToList(List<Flag> jobs) {
        flagObservableList.clear();
        flagObservableList.addAll(jobs);
    }

    public MutableLiveData<List<Flag>> getFlagLiveData() {
        return flagLiveData;
    }

    public void setFlagLiveData(List<Flag> list){
        flagLiveData.setValue(list);
    }

    //Resources---------------------------------------

    public void onBackButtonClick(){
        getNavigator().onBackButtonClick();
    }

    public void onClearButtonClick(){
        getNavigator().onClearButtonClick();
    }

    public void onSearchClick(){
        getNavigator().onSearchClick();
    }
}
