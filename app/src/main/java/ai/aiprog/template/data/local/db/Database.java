package ai.aiprog.template.data.local.db;


import ai.aiprog.template.data.model.db.flag.Flag;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;

/**
 * Author       : Arvindo Mondal
 * Created on   : 09-05-2019
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
public class Database implements DatabaseService {

    private final AppDatabase appDatabase;

    @Inject
    public Database(AppDatabase appDatabase){
        this.appDatabase = appDatabase;
    }

    @Override
    public Flowable<List<Flag>> getAllFlagsDb() {
        return Flowable.fromCallable(() -> appDatabase.flagDao().loadAllFlag());
    }

    @Override
    public Flowable<Boolean> saveFlagsListDb(List<Flag> flagList) {
        return Flowable.fromCallable(() -> {
            appDatabase.flagDao().insertAll(flagList);
            return true;
        });
    }

    @Override
    public Flowable<Boolean> updateFlagBaseUrlDb(String flagBaseUrl) {
        return Flowable.fromCallable(() -> {
            appDatabase.flagDao().updateFlagBaseUrl(flagBaseUrl);
            return true;
        });
    }
}
