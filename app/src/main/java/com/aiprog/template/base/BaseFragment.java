package com.aiprog.template.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import dagger.android.support.AndroidSupportInjection;

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
public abstract class BaseFragment<B extends ViewDataBinding,
        V extends BaseViewModel>
        extends Fragment {

//    private A activityCalss;
    private BaseActivity activity;
    private Context context;
    private B bindingF;
    private V viewModelF;

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

    /**
     *
     * @param binding is used in current attached fragment
     */
    public abstract void getFragmentBinding(B binding);

    public V getFragmentViewModelF(){
        return viewModelF;
    }

//    public BaseActivity getActivityClass(){
//        return activity;
//    }

    public BaseActivity getBaseActivity() {
        return activity;
    }

    public Context getContext(){
        return context;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
//            this.activity = (A) activity;
            this.activity = activity;
            this.context = context;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        viewModelF = getViewModel();
        setHasOptionsMenu(false);

        onCreateFragment(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        bindingF = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        getFragmentBinding(bindingF);
        View view = bindingF.getRoot();
        init();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindingF.setVariable(getBindingVariable(), viewModelF);
        bindingF.executePendingBindings();

        if(setTitle() != 0) {
            activity.setTitle(setTitle());
        }
    }

    /**
     *
     * @return R.layout.layout_file
     */
    @LayoutRes
    public abstract int getLayout();

    /**
     * Override for set view model
     *
     * @return viewModel = ViewModelProviders.of(this,factory).get(WelcomeViewModel.class);
     */
    public abstract V getViewModel();

    /**
     * Override for set bindingF variable
     *
     * @return BR.data;
     */
    public abstract int getBindingVariable();

    /**
     *
     * @return R.strings.text
     */
    public abstract int setTitle();


    /**
     *
     * @param savedInstanceState save the instance of fragment before closing
     *                           viewModel.setNavigator(this);
     */
    protected abstract void onCreateFragment(Bundle savedInstanceState);

    /**
     * Write rest of the code of fragment in onCreateView after view created
     */
    protected abstract void init();


//    /**
//     *
//     * @return R.strings.text
//     */
//    public abstract int setSubTitle();

    @Override
    public void onDetach() {
        activity = null;
        super.onDetach();

        System.gc();
    }

    public B getDataBinding() {
        return bindingF;
    }

    private void performDependencyInjection() {
        AndroidSupportInjection.inject(this);
    }


    public void openActivityOnTokenExpire() {
        if (activity != null) {
            activity.openActivityOnTokenExpire();
        }
    }

    public void hideKeyboard() {
        if (activity != null) {
            activity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return activity != null && activity.isNetworkAvailable();
    }
}
