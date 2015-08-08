package com.appspace.cpnpop;

import android.util.Log;

import com.appspace.cpnpop.helper.Constant;
import com.facebook.FacebookSdk;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by siwaweswongcharoen on 8/8/2015 AD.
 */
public class Application extends android.app.Application {

    public static GoogleAnalytics analytics;
    public static Tracker tracker;

    public Application() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        FacebookSdk.sdkInitialize(getApplicationContext());

        initParse();
        subscribeParseNotification();

        initGoogleAnalytics();
    }

    private void initGoogleAnalytics() {
        analytics = GoogleAnalytics.getInstance(this);
        analytics.setLocalDispatchPeriod(1800);

        tracker = analytics.newTracker(Constant.GA_ID);
        tracker.enableExceptionReporting(true);
        tracker.enableAdvertisingIdCollection(true);
        tracker.enableAutoActivityTracking(true);
    }

    private void subscribeParseNotification() {
        ParsePush.subscribeInBackground("", new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
                } else {
                    Log.e("com.parse.push", "failed to subscribe for push", e);
                }
            }
        });
    }

    private void initParse() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "ut71Hombs4drwmZCZ3Ez4cfU5fm19iiJkWSWe522", "0FtgEG5Fc6oN7Rj4LJvbGkZCvNo0D4jZ8aE8lNzF");
    }
}
