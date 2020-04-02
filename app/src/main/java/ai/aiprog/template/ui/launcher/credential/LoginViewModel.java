package ai.aiprog.template.ui.launcher.credential;

import android.util.Log;

import ai.aiprog.template.base.BaseViewModel;
import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.utils.rx.SchedulerProvider;
import ai.aiprog.template.utils.setup.Logger;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author       : Arvindo Mondal
 * Created date : 13-08-2019
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
public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    void fcmToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("DashboardViewModel", "getInstanceId failed", task.getException());
                    return;
                }

                if(task.getResult() != null) {
                    // Get new Instance ID token
                    String token = task.getResult().getToken();
                    // Log and toast
                    Log.e("DashboardViewModel", token);

                    pushFcmToken(token);
                }
            });
    }

    private void pushFcmToken(String token) {
        JSONObject params;
        try {
            params = paramsPushFcmToken(token);
            Logger.e("Params:" + params.toString());
        } catch (JSONException e) {
            params = new JSONObject();
        }
/*
        getCompositeDisposable().add(getDataManager()
                .pushFcmToken(accessToken, params)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().newThread())
                .subscribe(response -> {
                    if (response != null && response.getStatus() == RESULT_SUCCESS) {
//                        getDataManager().setFcmToken(false);
                    }
                    else {
//                        getDataManager().setFcmToken(true);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
//                    getDataManager().setFcmToken(true);
                }));
        */
    }


    public static JSONObject paramsPushFcmToken(String token)
            throws JSONException, ArrayIndexOutOfBoundsException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        return jsonObject;
    }

}
