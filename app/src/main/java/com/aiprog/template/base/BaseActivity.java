package com.aiprog.template.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;


import com.aiprog.template.R;

import org.jetbrains.annotations.NotNull;

import dagger.android.AndroidInjection;


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
public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel>
        extends AppCompatActivity implements BaseFragment.Callback{

    private static final int REQUEST_PERMISSIONS = 20;
    protected boolean REQUEST_PERMISSION_FOR_ACTIVITY = true;
    private B binding;
    private V viewModel;
    public Context context;
    public Activity activity;
    private SparseIntArray mErrorString;

    /**
     *
     * @param binding activity class data binding
     */
    public abstract void getActivityBinding(B binding);

    /**
     *
     * @return activity, Activity class activity or instance
     */
    public Activity getActivity() {
        return activity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        initialization(savedInstanceState);
        performDataBinding();
//        setTitle();
        init();

        mErrorString = new SparseIntArray();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(REQUEST_PERMISSION_FOR_ACTIVITY) {
            checkAllPermission();
        }
    }


    private void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayout());
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
        getActivityBinding(binding);
    }

    /**
     *
     * @param state Initialise any thing here before data binding
     */
    protected abstract void initialization(@Nullable Bundle state);

    /**
     *
     * @return R.layout.layout_file
     */
    @LayoutRes
    protected abstract int getLayout();

    /**
     * Override for set binding variable
     *
     * @return BR.data;
     */
    public abstract int getBindingVariable();

    /**
     * Set the title of activity here or leave it blank
     */
//    protected abstract void setTitle();

    /**
     * Override for get the instance of viewModel
     *
     * @return viewModel = ViewModelProviders.of(this,factory).get(WelcomeViewModel.class);
     */
    public abstract V getViewModel();

    /**
     * Do anything on onCreate after binding
     * viewModel.setNavigator(this);
     */
    protected abstract void init();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
        viewModel = null;
        activity = null;
        context = null;
        mErrorString = null;

        System.gc();
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }


    /**
     * Injecting dependencies
     */
    public void performDependencyInjection(){
        AndroidInjection.inject(this);
    }

    public void openActivityOnTokenExpire() {
//        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    //-------------------Message show--------------------------

    public void showToast(int index){
        Toast.makeText(context, getString(index), Toast.LENGTH_SHORT).show();
    }

    public void showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(int index){
        Toast.makeText(context, getString(index), Toast.LENGTH_LONG).show();
    }

    //-------------------Permission----------------------------

    /**
     * override this method when manually asked for permission and get permission granted
     */
    public void onPermissionGranted() {

    }

    public boolean isPermissionGranted() {
        int permissionState = ActivityCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isPermissionGrantedCamera() {
        int permissionState = ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    public void checkAllPermission() {
        try {
            requestAppPermissions(new String[]{
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_NETWORK_STATE
                    },
                    R.string.str_ShowOnPermisstion,
                    REQUEST_PERMISSIONS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int permission : grantResults) {
            permissionCheck = permissionCheck + permission;
        }
        if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //permission already granted
//            onPermissionsGranted(requestCode);
//            createFolder();

            onPermissionGranted();
        } else {
            Snackbar mSnackBar = Snackbar.make(findViewById(android.R.id.content),
                    getString( R.string.str_ShowOnPermisstion),
                    Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        }
                    });

            mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
            TextView mainTextView = (TextView) (mSnackBar.getView()).
                    findViewById(R.id.snackbar_text);
            mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                    getResources().getDimension(R.dimen.snackbar_textSize));
            mSnackBar.show();
        }
    }

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        mErrorString.put(requestCode, stringId);
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        boolean shouldShowRequestPermissionRationale = false;
        for (String permission : requestedPermissions) {
            permissionCheck = permissionCheck + ContextCompat.checkSelfPermission(this, permission);
            shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale ||
                    ActivityCompat.shouldShowRequestPermissionRationale(this, permission);
        }
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale) {
                Snackbar mSnackBar  = Snackbar.make(findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ActivityCompat.requestPermissions(BaseActivity.this,
                                        requestedPermissions, requestCode);
                            }
                        });

                mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
                TextView mainTextView = (TextView) (mSnackBar.getView()).
                        findViewById(R.id.snackbar_text);
                mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.snackbar_textSize));
                mSnackBar.show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        }
//        else {
//            createFolder();
            //onSuccess permission grant
//            onPermissionsGranted(requestCode);
//            onPermissionGranted();
//        }
    }

    //-----------------------TaskActivity----------------------------

    /**
     * If keypad is showing it can be hide immediately
     */
    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     *
     * @return true if network available else false for not
     */
    public boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     *
     * @param view method which focus on EditText view
     */
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }
/*
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }*/
}
