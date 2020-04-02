package ai.aiprog.template.core.dialogs.flag.adapter;

import androidx.databinding.ObservableField;

import ai.aiprog.template.data.model.db.flag.Flag;

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
public class FlagAdapterViewModel{
    public static final ObservableField<String> textFilter = new ObservableField<>("");
    private final ObservableField<Integer> id;
    public final ObservableField<String> capital;
    public final ObservableField<String> country;
    public final ObservableField<String> iSOCode1;
    public final ObservableField<String> iSOCode2;
    public final ObservableField<String> mobilePhonePrefix;
    public final ObservableField<String> continent;
    public final ObservableField<String> flagUrl;

    public FlagAdapterViewModel(Flag model) {
        this.id = new ObservableField<>(model.getId());
        this.capital = new ObservableField<>(model.getCapital());
        this.country = new ObservableField<>(model.getCountry());
        this.iSOCode1 = new ObservableField<>(model.getISOCode1());
        this.iSOCode2 = new ObservableField<>(model.getISOCode2());
        this.mobilePhonePrefix = new ObservableField<>(model.getMobilePhonePrefix());
        this.continent = new ObservableField<>(model.getContinent());
        this.flagUrl = new ObservableField<>(model.getFlagBaseUrl() + model.getFlagUrl());
    }
}
