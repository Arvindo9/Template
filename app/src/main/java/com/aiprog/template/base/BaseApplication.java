package com.aiprog.template.base;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.os.StrictMode;
import android.text.TextUtils;

import com.aiprog.template.BuildConfig;
import com.aiprog.template.di.component.DaggerAppComponent;
import com.aiprog.template.utils.Logger;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.dumpapp.ArgsHelper;
import com.facebook.stetho.dumpapp.DumpException;
import com.facebook.stetho.dumpapp.DumpUsageException;
import com.facebook.stetho.dumpapp.DumperContext;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;

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
public class BaseApplication extends Application  implements HasActivityInjector, HasServiceInjector {

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

        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Logger.init();

        setUpPicasso();

//        setUpEmoji();

        setUpStetho();

//        subscribToTopic();
    }

    private void setUpPicasso() {
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(false);
        Picasso.setSingletonInstance(built);
    }

    private void setUpStetho(){
        Stetho.initializeWithDefaults(this);
/*
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(() -> new Stetho.DefaultDumperPluginsBuilder(BaseApplication.this)
                        .provide(new HelloWorldDumperPlugin())
                        .finish())
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
        */
    }


    public class HelloWorldDumperPlugin implements DumperPlugin {
        private static final String NAME = "hello";

        @Override
        public String getName() {
            return NAME;
        }

        @Override
        public void dump(DumperContext dumpContext) throws DumpException {
            PrintStream writer = dumpContext.getStdout();
            Iterator<String> args = dumpContext.getArgsAsList().iterator();

            String helloToWhom = ArgsHelper.nextOptionalArg(args, null);
            if (helloToWhom != null) {
                doHello(dumpContext.getStdin(), writer, helloToWhom);
            } else {
                doUsage(writer);
            }
        }

        private void doHello(InputStream in, PrintStream writer, String name) throws DumpException {
            if (TextUtils.isEmpty(name)) {
                // This will print an error to the dumpapp user and cause a non-zero exit of the
                // script.
                throw new DumpUsageException("Name is empty");
            } else if ("-".equals(name)) {
                try {
                    name = new BufferedReader(new InputStreamReader(in)).readLine();
                } catch (IOException e) {
                    throw new DumpException(e.toString());
                }
            }

            writer.println("Hello " + name + "!");
        }

        private void doUsage(PrintStream writer) {
            writer.println("Usage: dumpapp " + NAME + " <name>");
            writer.println();
            writer.println("If <name> is '-', the name will be read from stdin.");
        }
    }

/*

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
*/

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
//        System.loadLibrary("algorithm-lib");
    }
}
