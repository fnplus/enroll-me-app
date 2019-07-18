package tech.fnplus.enrollme.app;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;
import tech.fnplus.enrollme.BuildConfig;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private static AppController mInstance;

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // Initialise Firebase
        FirebaseApp.initializeApp(this);

        // Check Build Config for debugging libraries
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
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
