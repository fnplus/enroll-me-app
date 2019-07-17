package tech.fnplus.enrollme.app;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;
import tech.fnplus.enrollme.BuildConfig;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    }

    private FirebaseAnalytics mFirebaseAnalytics;

    public static synchronized AppController getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // Initialise Firebase
        FirebaseApp.initializeApp(this);

        // Check Build Config for debugging libraries
        if (BuildConfig.DEBUG) {

        } else {
            // Obtain the FirebaseAnalytics
            mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            // Set Analytics collection to true
            mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
            // Set Crash Reporting to true
            Fabric.with(this, new Crashlytics());
        }
    }
}
