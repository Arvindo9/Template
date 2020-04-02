package ai.aiprog.template.data;


import ai.aiprog.template.data.local.db.DatabaseService;
import ai.aiprog.template.data.local.prefs.PreferencesService;
import ai.aiprog.template.data.remote.APIService;

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
public interface DataManager extends DatabaseService, PreferencesService, APIService {

    void updateUserInfo(
            String accessToken,
            Long userId,
            LoggedInMode loggedInMode,
            String userName,
            String email,
            String profilePicPath);

    enum LoggedInMode {
        LOGGED_IN_MODE_FIRST_TIME(0),
        LOGGED_IN_MODE_LOGGED_OUT(1),                       //login screen
        LOGGED_IN_MODE_LOGGED_IN_HOME(2),
        LOGGED_IN_MODE_LOGGED_GOOGLE(3),
        LOGGED_IN_MODE_LOGGED_FACEBOOK(4);

        private final int mType;

        LoggedInMode(int type) {
            mType = type;
        }

        public int getType() {
            return mType;
        }
    }
}
