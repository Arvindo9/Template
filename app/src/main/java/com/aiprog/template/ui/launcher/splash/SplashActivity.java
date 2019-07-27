package com.aiprog.template.ui.launcher.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.aiprog.template.BR;
import com.aiprog.template.R;
import com.aiprog.template.base.BaseActivity;
import com.aiprog.template.databinding.SplashActivityBinding;
import com.aiprog.template.di.module.ViewModelProviderFactory;
import com.aiprog.template.ui.launcher.welcome.WelcomeActivity;

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


    @Inject
    ViewModelProviderFactory factory;
    private SplashViewModel viewModel;

    private SplashActivityBinding binding;

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    public void getActivityBinding(SplashActivityBinding binding) {
        this.binding = binding;
    }

    @Override
    protected void initialization(@Nullable Bundle state) {

    }

    @Override
    protected int getLayout() {
        return R.layout.splash_activity;
    }

    @Override
    public int getBindingVariable() {
        return BR.data;
    }

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    @Override
    public SplashViewModel getViewModel() {
        return viewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
    }


    @Override
    protected void init() {
        viewModel.setNavigator(this);
        viewModel.onStart("");
        showToast(stringFromJNI());
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

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
//        Intent intent = HomeActivity.newIntent(this);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void handleMessage(String message) {

    }

    @Override
    public void handleMessage(int index) {

    }
}
