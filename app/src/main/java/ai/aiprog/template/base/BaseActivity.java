package ai.aiprog.template.base;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.SparseIntArray;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import ai.aiprog.template.R;
import ai.aiprog.template.data.DataManager;
import ai.aiprog.template.di.builder.ViewModelProviderFactory;
import ai.aiprog.template.ui.launcher.splash.SplashActivity;
import ai.aiprog.template.utils.setup.Logger;
import ai.aiprog.template.utils.setup.AppConstants;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

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
        extends AppCompatActivity implements BaseFragment.Callback, HasAndroidInjector {
    protected boolean REQUEST_PERMISSION_FOR_ACTIVITY = true;
    private B binding;
    private V viewModel;
    private int titleIndex = 0;
    private ActivityCallback callback;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;
    @Inject
    ViewModelProviderFactory factory;

    public interface ActivityCallback{
        void onPermissionGranted();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    /**
     * @param binding activity class data binding
     */
    public abstract void getActivityBinding(B binding);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        performDependencyInjection();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
/*
        if(getIntent().hasExtra(AppConstants.KEY_DEFAULT_ACTIVITY_BUNDLE)) {
            initialization(savedInstanceState, getIntent().getBundleExtra(AppConstants.KEY_DEFAULT_ACTIVITY_BUNDLE));
        }
        else{
            initialization(savedInstanceState, new Bundle());
        }
*/
        initialization(savedInstanceState, getIntent().getExtras());

        performDataBinding();
        init();

        if (REQUEST_PERMISSION_FOR_ACTIVITY) {
            checkAllPermission();
        }
    }

    public void setCallback(){
        callback = (ActivityCallback) this;
        Logger.e("onPermissionGranted " + "called");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (REQUEST_PERMISSION_FOR_ACTIVITY) {
//            checkAllPermission();
//        }
        Logger.e("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        if (getSupportActionBar() != null && titleIndex != 0) {
            getSupportActionBar().setTitle(titleIndex);
        }
        Logger.e("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
        viewModel = null;
        System.gc();
    }

    protected void performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayout());
//        this.viewModel = viewModel == null ? getViewModel() : viewModel;
        this.viewModel = viewModel == null ? ViewModelProviders.of(this,factory).get(setViewModel()) : viewModel;
        binding.setVariable(getBindingVariable(), viewModel);
        binding.executePendingBindings();
        getActivityBinding(binding);
        getViewModel(viewModel);
    }

    /**
     * @param state Initialise any thing here before data binding
     * @param args  this is the data which passes by previous activity or context
     */
    protected void initialization(Bundle state, Bundle args) {
    }

    protected ViewModelProviderFactory getFactory(){
        return factory;
    }

    /**
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

//    public abstract V getViewModel();

    protected abstract Class<V> setViewModel();

    protected abstract void getViewModel(V viewModel);

    /**
     * Do anything on onCreate after binding
     * viewModel.setNavigator(this);
     */
    protected abstract void init();

    /**
     * Call this method to set up the toolbar and title
     *
     * @param toolbar    xml toolbar
     * @param titleIndex title in toolbar
     */
    protected void setToolbar(Toolbar toolbar, int titleIndex) {
        this.titleIndex = titleIndex;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(titleIndex);
        }
    }

    public void onDialogFragmentAttached(Fragment fragment) {

    }

    @Override
    public void onFragmentAttached(Fragment fragment) {

    }

    @Override
    public void onFragmentDetached() {

    }


    /**
     * Injecting dependencies
     */
    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public void openActivityOnTokenExpire() {
//        startActivity(LoginFragment.newIntent(this));
        finish();
    }

    //-------------------Messages show--------------------------

    public void showToast(int index) {
        Toast.makeText(this, getString(index), Toast.LENGTH_SHORT).show();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong(int index) {
        Toast.makeText(this, getString(index), Toast.LENGTH_LONG).show();
    }

    //-------------------Permission----------------------------

    /**
     * override this method when manually asked for permission and get permission granted
     */
    private void onPermissionGranted() {
        if(callback != null){
            callback.onPermissionGranted();
        }
        Logger.e("onPermissionGranted " + "called");
    }

    public boolean isPermissionGranted() {
        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (String permission : allPermission()) {
            permissionCheck =+ ContextCompat.checkSelfPermission(this, permission);
        }
        return permissionCheck == PackageManager.PERMISSION_GRANTED;
    }

    public boolean isPermissionGrantedCamera() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private String[] allPermission(){
        return new String[]{
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
        };
    }

    public void checkAllPermission() {
        try {
            requestAppPermissions(allPermission(),
                    R.string.str_ShowOnPermisstion,
                    AppConstants.REQUEST_PERMISSIONS);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

/*

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions,
                                           @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == AppConstants.REQUEST_PERMISSIONS) {
            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int permission : grantResults) {
                permissionCheck = permissionCheck + permission;
            }
            if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //permission already granted
                onPermissionGranted();
            } else {
                //Permission Not granted
                Snackbar mSnackBar = Snackbar.make(findViewById(android.R.id.content),
                        getString(R.string.str_ShowOnPermisstion),
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        v -> {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        });

                mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
                TextView mainTextView = (mSnackBar.getView()).
                        findViewById(R.id.snackbar_text);
                mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.snackbar_textSize));
                mSnackBar.show();
            }
        }
    }

*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == AppConstants.REQUEST_PERMISSIONS) {
            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int permission : grantResults) {
                permissionCheck = permissionCheck + permission;
            }
            if ((grantResults.length > 0) && permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //permission already granted
                onPermissionGranted();
            } else {
                Snackbar mSnackBar = Snackbar.make(findViewById(android.R.id.content),
                        getString(R.string.str_ShowOnPermisstion),
                        Snackbar.LENGTH_INDEFINITE).setAction("ENABLE",
                        v -> {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            intent.addCategory(Intent.CATEGORY_DEFAULT);
                            intent.setData(Uri.parse("package:" + getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            startActivity(intent);
                        });

                mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
                mSnackBar.show();
            }
        }
    }

/*

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        SparseIntArray mErrorString = new SparseIntArray();
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
                Snackbar mSnackBar = Snackbar.make(findViewById(android.R.id.content), stringId,
                        Snackbar.LENGTH_INDEFINITE).setAction("GRANT",
                        v -> ActivityCompat.requestPermissions(BaseActivity.this,
                                requestedPermissions, requestCode));

                mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
                TextView mainTextView = (mSnackBar.getView()).
                        findViewById(R.id.snackbar_text);
                mainTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        getResources().getDimension(R.dimen.snackbar_textSize));
                mSnackBar.show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        }
    }

*/

    public void requestAppPermissions(final String[] requestedPermissions,
                                      final int stringId, final int requestCode) {
        SparseIntArray mErrorString = new SparseIntArray();
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
                        v -> ActivityCompat.requestPermissions(BaseActivity.this,
                                requestedPermissions, requestCode));
                mSnackBar.setActionTextColor(getResources().getColor(R.color.snackBarActionColor));
                mSnackBar.show();
            } else {
                ActivityCompat.requestPermissions(this, requestedPermissions, requestCode);
            }
        }
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
     * @return true if network available else false for not
     */
    public boolean isNetworkAvailable() {
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
     * @param view method which focus on EditText view
     */
    public void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    public <C extends Activity> void startActivity(Class<C> targetActivityClass) {
        startActivity(new Intent(this, targetActivityClass));
    }

    public <C extends Activity> void startActivity(Class<C> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
//        intent.putExtra(AppConstants.KEY_DEFAULT_ACTIVITY_BUNDLE, bundle);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public <C extends Activity> void startActivityFresh(Class<C> targetActivityClass, Bundle bundle) {
        Intent intent = new Intent(this, targetActivityClass);
//        intent.putExtra(AppConstants.KEY_DEFAULT_ACTIVITY_BUNDLE, bundle);
        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public <C extends Activity> void startActivityFresh(Class<C> targetActivityClass) {
        Intent intent = new Intent(this, targetActivityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * @param fragment new Fragment();
     * @param tag      Fragment.TAG or Fragment.class.getSimpleName()
     */
    public void startFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment, fragment, tag);
//        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
    }

    /**
     * @param fragment new Fragment();
     * @param tag      Fragment.TAG or Fragment.class.getSimpleName()
     * @param bundle   for extra params
     */
    public void startFragment(Fragment fragment, String tag, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragment, fragment, tag);
//        transaction.addToBackStack(fragmentClassName);
        transaction.commit();
    }

    /**
     * @param dialog new Dialog();
     * @param tag    Dialog.TAG or Dialog.class.getSimpleName()
     */
    public void startDialog(DialogFragment dialog, String tag) {
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.default_dialog);
        dialog.show(getSupportFragmentManager(), tag);
    }

    /**
     * @param dialog new Dialog();
     * @param tag    Dialog.TAG or Dialog.class.getSimpleName()
     * @param bundle for extra params
     */
    public void startDialog(DialogFragment dialog, String tag, Bundle bundle) {
        dialog.setArguments(bundle);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, R.style.default_dialog);
        dialog.show(getSupportFragmentManager(), tag);
    }

    /**
     * @param dialog new Dialog();
     * @param tag    Dialog.TAG or Dialog.class.getSimpleName()
     * @param bundle for extra params
     */
    public void startDialog(DialogFragment dialog, String tag, Bundle bundle, @StyleRes int styleId) {
        dialog.setArguments(bundle);
        dialog.setStyle(DialogFragment.STYLE_NORMAL, styleId);
        dialog.show(getSupportFragmentManager(), tag);
//        dialog.show(getSupportFragmentManager(), null);
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.add(dialog, tag);
//        ft.commitAllowingStateLoss();
    }

    /**
     * Logout
     */
    public void logout() {
        viewModel.getDataManager().setAccessToken("");
        viewModel.getDataManager().setEmail("");
        viewModel.getDataManager().setMobile("");
        viewModel.getDataManager().setCountryCode("");
        viewModel.getDataManager().setLoggedInMode(DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT);
        startActivity(SplashActivity.class);
        finishAffinity();
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
