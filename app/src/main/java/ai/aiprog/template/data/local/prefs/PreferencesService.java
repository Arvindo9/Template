package ai.aiprog.template.data.local.prefs;


import ai.aiprog.template.data.DataManager;

/**
 * Author       : Arvindo Mondal
 * Created on   : 23-12-2018
 */
public interface PreferencesService {
    String getApplicationId();

    void setApplicationId(String id);

    String getAccessToken();

    void setAccessToken(String token);

    int getLoggedInMode();

    void setLoggedInMode(DataManager.LoggedInMode mode);

    void setEmail(String email);

    String getEmail();

    void setCountryCode(String countryCode);

    String getCountryCode();

    void setMobile(String mobile);

    String getMobile();
}
