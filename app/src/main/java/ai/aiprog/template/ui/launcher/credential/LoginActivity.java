package ai.aiprog.template.ui.launcher.credential;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import ai.aiprog.template.BR;
import ai.aiprog.template.R;
import ai.aiprog.template.base.BaseActivity;
import ai.aiprog.template.databinding.ActivityLoginBinding;
import ai.aiprog.template.utils.setup.AppConstants;
import ai.aiprog.template.utils.setup.Logger;

/**
 * Author       : Arvindo Mondal
 * Created on   : 28-07-2019
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
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator{
    private ai.aiprog.template.databinding.ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    /**
     * @param binding activity class data binding
     */
    @Override
    public void getActivityBinding(ActivityLoginBinding binding) {
        this.binding = binding;
    }

    /**
     * @return R.layout.layout_file
     */
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    /**
     * Override for set binding variable
     *
     * @return BR.data;
     */
    @Override
    public int getBindingVariable() {
        return BR.data;
    }

    @Override
    protected Class<LoginViewModel> setViewModel() {
        return LoginViewModel.class;
    }

    @Override
    protected void getViewModel(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Do anything on onCreate after binding
     * viewModel.setNavigator(this);
     */
    @Override
    protected void init() {
        viewModel.setNavigator(this);
        viewModel.fcmToken();
        setupBroadcast();
    }

    @Override
    protected void onDestroy() {
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(messageReceiver);
        }catch (Exception ignore){}
        super.onDestroy();
    }

    //Messages event------------

    private void setupBroadcast() {
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver,
                new IntentFilter(AppConstants.INTENT_SERVER_RECEIVE_MESSAGES));
    }

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String message = intent.getStringExtra(AppConstants.KEY_FROM_SERVICE_MESSAGE);
            Logger.e("receiver " + "Got message: " + message);
            if (message != null) {
                if (message.equals(AppConstants.VALUE_FROM_SERVICE_MESSAGE)) {
//                    viewModel.getMessageDataPush(roseType, modelInbox.getPrimaryKey());
                }
            }
        }
    };
}