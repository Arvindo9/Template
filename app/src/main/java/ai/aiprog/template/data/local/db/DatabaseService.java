package ai.aiprog.template.data.local.db;

import ai.aiprog.template.data.model.db.flag.Flag;

import java.util.List;

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
public interface DatabaseService {

    Flowable<List<Flag>> getAllFlagsDb();

    Flowable<Boolean> saveFlagsListDb(List<Flag> flagList);

    Flowable<Boolean> updateFlagBaseUrlDb(String flagBaseUrl);

}
