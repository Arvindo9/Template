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

import com.aiprog.template.utils.Logger;

import org.jetbrains.annotations.NotNull;

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
public abstract class BaseFragment<B extends ViewDataBinding, V extends BaseViewModel>
        extends Fragment {

    private BaseActivity activity;
    private Context context;
    private B binding;
    private V viewModel;
    private final String FragmentContextExecption = "Fragment exception";

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached();
    }

    /**
     *
     * @param binding is used in current attached fragment
     */
    public abstract void getFragmentBinding(B binding);

    public BaseActivity getBaseActivity() {
        return activity;
    }

    public Context getContext(){
        return context;
    }


    @Override
    public void onAttach(@NotNull(FragmentContextExecption) Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.activity = activity;
            this.context = context;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);

        viewModel = getViewModel();
        onCreateFragment(savedInstanceState, getArguments());
        setHasOptionsMenu(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        getFragmentBinding(binding);
        View view = binding.getRoot();
//        init();

        Logger.e("Fragment onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();

        init();
        if(setTitle() != 0) {
            if(activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle(setTitle());
            }
            else {
                activity.setTitle(setTitle());
            }
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
     * Override for set binding variable
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
     * @param args
     */
    protected abstract void onCreateFragment(Bundle savedInstanceState, Bundle args);

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
        super.onDetach();
        getBaseActivity().onFragmentDetached();
        activity = null;

        System.gc();
    }

    public B getDataBinding() {
        return binding;
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

    //------------
}
