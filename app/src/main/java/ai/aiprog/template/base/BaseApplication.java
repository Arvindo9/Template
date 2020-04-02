package ai.aiprog.template.base;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.core.provider.FontRequest;
import androidx.emoji.text.EmojiCompat;
import androidx.emoji.text.FontRequestEmojiCompatConfig;

import com.facebook.stetho.Stetho;
import com.google.firebase.messaging.FirebaseMessaging;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import ai.aiprog.template.R;
import ai.aiprog.template.di.component.DaggerAppComponent;
import ai.aiprog.template.utils.setup.Logger;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

import static ai.aiprog.template.utils.setup.AppConstants.FIREBASE_NOTIFICATION_TOPIC_GROUP;
//import static AppConstants.FIREBASE_NOTIFICATION_TOPIC;


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
public class BaseApplication extends Application implements HasAndroidInjector {
    private static final String TAG = BaseApplication.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    static {
//        System.loadLibrary("algorithm-lib");
    }
    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    @Override
    public void onCreate() {
/*
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
//                    .penaltyDeath()
//                    .detectAll()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
//                    .penaltyDeath()
//                    .detectAll()
                    .build());
        }
*/

        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Logger.init();

        setUpPicasso();

        setUpEmoji();

        setUpStetho();

//        subscribeToTopic();
    }

    private void setUpPicasso() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(false);
        Picasso.setSingletonInstance(built);
    }

    private void setUpStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void setUpEmoji() {
        final EmojiCompat.Config config;

        final FontRequest fontRequest = new FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs);
        config = new FontRequestEmojiCompatConfig(getApplicationContext(), fontRequest);

        config.setReplaceAll(true)
                .registerInitCallback(new EmojiCompat.InitCallback() {
                    @Override
                    public void onInitialized() {
                        Logger.i(TAG + " EmojiCompat initialized");
                    }

                    @Override
                    public void onFailed(@Nullable Throwable throwable) {
                        Logger.e(TAG + " EmojiCompat initialization failed", throwable);
                    }
                });

        EmojiCompat.init(config);
    }
    private void subscribeToTopic(){
        Logger.e(TAG + " Subscribing to weather topic");
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic(FIREBASE_NOTIFICATION_TOPIC_GROUP)
                .addOnCompleteListener(task -> {
                    String msg = getString(R.string.msg_subscribed);
                    if (!task.isSuccessful()) {
                        msg = getString(R.string.msg_subscribe_failed);
                    }
                    Logger.e(TAG, " " +msg);
                });
        // [END subscribe_topics]
    }

}
