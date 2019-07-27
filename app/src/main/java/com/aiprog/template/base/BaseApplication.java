package com.aiprog.template.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;

import com.aiprog.template.di.component.DaggerAppComponent;
import com.aiprog.template.utils.Logger;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasServiceInjector;
//import static com.aiprog.template.utils.AppConstants.FIREBASE_NOTIFICATION_TOPIC;


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
public class BaseApplication extends Application  implements HasActivityInjector, HasServiceInjector{

    private static final String TAG = BaseApplication.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;


    @Inject
    DispatchingAndroidInjector<Service> serviceDispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

    /**
     * Returns an {@link AndroidInjector} of {@link Service}s.
     */
    @Override
    public AndroidInjector<Service> serviceInjector() {
        return serviceDispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Logger.init();

        setUpPicasso();

//        subscribToTopic();
    }

    private void setUpPicasso() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

/*    private void subscribToTopic(){
        Log.d(TAG, "Subscribing to weather topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(FIREBASE_NOTIFICATION_TOPIC)
                .addOnCompleteListener(task -> {
                    String msg = getString(R.string.msg_subscribed);
                    if (!task.isSuccessful()) {
                        msg = getString(R.string.msg_subscribe_failed);
                    }
                    Log.d(TAG, msg);
                });
        // [END subscribe_topics]
    }*/

    static {
        System.loadLibrary("algorithm-lib");
    }
}
