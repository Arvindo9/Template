package ai.aiprog.template.ui.launcher.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import ai.aiprog.template.BR;
import ai.aiprog.template.R;
import ai.aiprog.template.base.BaseActivity;
import ai.aiprog.template.databinding.SplashActivityBinding;
import ai.aiprog.template.di.builder.ViewModelProviderFactory;
import ai.aiprog.template.ui.launcher.welcome.WelcomeActivity;
import ai.aiprog.template.utils.setup.Logger;

import javax.inject.Inject;

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
public class SplashActivity extends BaseActivity<SplashActivityBinding, SplashViewModel> implements SplashNavigator {
    private SplashViewModel viewModel;

    private SplashActivityBinding binding;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public void getActivityBinding(SplashActivityBinding binding) {
        this.binding = binding;
    }

    /**
     * @param state Initialise any thing here before data binding
     * @param args  this is the data which passes by previous activity or context
     */
    @Override
    protected void initialization(Bundle state, Bundle args) {

    }

    @Override
    protected int getLayout() {
        return R.layout.splash_activity;
    }

    @Override
    public int getBindingVariable() {
        return BR.data;
    }

    @Override
    protected Class<SplashViewModel> setViewModel() {
        return SplashViewModel.class;
    }

    @Override
    protected void getViewModel(SplashViewModel viewModel) {
        this.viewModel = viewModel;
    }


    @Override
    protected void init() {
        viewModel.setNavigator(this);
        viewModel.onStart("");
//        showToast(stringFromJNI());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();

    //Navigator-----------------------

    @Override
    public void openWelcomeScreen() {
        Intent intent = WelcomeActivity.newIntent(this);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void openLoginActivity() {
    }

    @Override
    public void openHomeActivity() {
//        startActivity(intent);
    }


    private void setUpNotification() {
        // If a Notification message is tapped, any data accompanying the Notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the Notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the Notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying Notification message.
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Logger.e("Key: " + key + " Value: " + value);

                if(key.equals("Key")) {
                    try {
                        String idStr = String.valueOf(value);
                        int id = Integer.parseInt(idStr);
//                        onOpenJobItem(id);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }
        // [END handle_data_extras]
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(int message) {

    }
}
