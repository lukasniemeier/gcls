package de.lukasniemeier.gamecenterlivesender;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.google.sample.castcompanionlibrary.cast.VideoCastManager;
import com.google.sample.castcompanionlibrary.utils.Utils;

import net.danlew.android.joda.JodaTimeAndroid;

import org.joda.time.DateTimeZone;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.TimeZone;

public class CoreApplication extends Application {

    public static final double VOLUME_INCREMENT = 0.05;
    public static String USER_AGENT;
    public static String APPLICATION_ID;

    private static VideoCastManager castManager;

    public static VideoCastManager getVideoCastManager(Context ctx) {
        if (castManager == null) {
            castManager = VideoCastManager.initialize(
                    ctx,
                    APPLICATION_ID,
                    null,
                    ctx.getString(R.string.receiver_data_channel));
            castManager.enableFeatures(
                    VideoCastManager.FEATURE_NOTIFICATION |
                            VideoCastManager.FEATURE_WIFI_RECONNECT |
                            VideoCastManager.FEATURE_DEBUGGING);
            castManager.setStopOnDisconnect(true);
        }
        castManager.setContext(ctx);
        return castManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        USER_AGENT = getString(R.string.user_agent);
        APPLICATION_ID = getString(R.string.receiver_application_id);

        JodaTimeAndroid.init(this);
        DateTimeZone.setDefault(DateTimeZone.forTimeZone(TimeZone.getDefault()));

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        PreferenceManager.setDefaultValues(this, R.xml.preference_login, false);

        Utils.saveFloatToPreference(getApplicationContext(),
                VideoCastManager.PREFS_KEY_VOLUME_INCREMENT, (float) VOLUME_INCREMENT);
    }
}

