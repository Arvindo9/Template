package ai.aiprog.template.ui.launcher.welcome;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import ai.aiprog.template.BR;
import ai.aiprog.template.R;
import ai.aiprog.template.base.BaseActivity;
import ai.aiprog.template.databinding.WelcomeActivityBinding;
import ai.aiprog.template.di.builder.ViewModelProviderFactory;
import ai.aiprog.template.ui.launcher.credential.LoginActivity;
import ai.aiprog.template.utils.setup.Logger;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Author       : Arvindo Mondal
 * Created on   : 13-05-2019
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
public class WelcomeActivity extends BaseActivity<WelcomeActivityBinding, WelcomeViewModel> implements WelcomeNavigator {
    private WelcomeViewModel viewModel;

    @Inject
    WelcomeAdapter adapter;

    private int mCurrentPage = 0;
    private final int NUM_PAGES = 6;
    private TextView[] mDots;
    private boolean isNextActivityStart = false;
    private WelcomeActivityBinding binding;

    /**
     * @param binding activity class data binding
     */
    @Override
    public void getActivityBinding(WelcomeActivityBinding binding) {
        this.binding = binding;
    }

    /**
     * @param state Initialise any thing here before data binding
     * @param args  this is the data which passes by previous activity or context
     */
    @Override
    protected void initialization(Bundle state, Bundle args) {

    }

    /**
     * @return R.layout.layout_file
     */
    @Override
    protected int getLayout() {
        return R.layout.welcome_activity;
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    @Override
    public int getBindingVariable() {
        return BR.data;
    }

    @Override
    protected Class<WelcomeViewModel> setViewModel() {
        return WelcomeViewModel.class;
    }

    @Override
    protected void getViewModel(WelcomeViewModel viewModel) {
        this.viewModel = viewModel;
    }


    /**
     * Do anything on onCreate after binding
     */
    @Override
    protected void init() {
        REQUEST_PERMISSION_FOR_ACTIVITY = false;
        viewModel.setNavigator(this);
        viewModel.onStart(this);
        binding.welcomePager.setAdapter(adapter);

        addDots(0);
        binding.welcomePager.addOnPageChangeListener(viewListener);

//        handlingBackground();
    }

    private void openStartAppActivity() {
        if(!isNextActivityStart) {
            startActivity(LoginActivity.class);
            finish();
            isNextActivityStart = true;
        }
    }


    //pager------------------------

    private void handlingBackground() {
        if (mCurrentPage == NUM_PAGES) {
//            mCurrentPage = 0;
            openStartAppActivity();
        }
        else {
            try {
                binding.welcomePager.setCurrentItem(mCurrentPage++, true);
            }catch (NullPointerException ignore){}
        }
    }

    public void addDots(int i){

        mDots =new TextView[NUM_PAGES];
        binding.dots.removeAllViews();

        for (int x = 0; x< mDots.length; x++){

            mDots[x]=new TextView(this);
            mDots[x].setText(Html.fromHtml("&#8226;"));
            mDots[x].setTextSize(35);
            mDots[x].setTextColor(getResources().getColor(R.color.welcomePagerDots));

            binding.dots.addView(mDots[x]);
        }
        if (mDots.length>0){

            mDots[i].setTextColor(getResources().getColor(R.color.white));

        }

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            mCurrentPage = position;
            if (position== mDots.length-1){
                binding.nextBtn.setText(getText(R.string.start));
                binding.backBtn.setVisibility(View.GONE);
            }
            else{
                binding.nextBtn.setText(getText(R.string.next));
                binding.backBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onNextClick() {
        if(mCurrentPage > 0 && !binding.nextBtn.getText().equals(getText(R.string.start))){
            binding.backBtn.setVisibility(View.VISIBLE);
        }

        if(binding.nextBtn.getText().equals(getText(R.string.start))){
            openStartAppActivity();
        }else{
            binding.welcomePager.setCurrentItem(++mCurrentPage, true);
        }

        if(mCurrentPage == NUM_PAGES){
            binding.nextBtn.setText(getString(R.string.start));
        }
    }

    @Override
    public void onBackClick() {
        binding.welcomePager.setCurrentItem(--mCurrentPage, true);
        if(mCurrentPage < 1){
            binding.backBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void handleError(Throwable throwable) {
        Logger.e(throwable.getMessage());
        if(throwable instanceof IOException){
            runOnUiThread(() -> showToast(R.string.network_error));
        }
        throwable.printStackTrace();
    }
}
