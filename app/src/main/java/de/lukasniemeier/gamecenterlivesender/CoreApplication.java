package de.lukasniemeier.gamecenterlivesender;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import com.google.android.libraries.cast.companionlibrary.cast.CastConfiguration;
import com.google.android.libraries.cast.companionlibrary.cast.VideoCastManager;
import net.danlew.android.joda.JodaTimeAndroid;
import org.joda.time.DateTimeZone;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.TimeZone;

public class CoreApplication extends Application {

    public static final double VOLUME_INCREMENT = 0.1;
    public static String USER_AGENT;
    public static String APPLICATION_ID;

    private static VideoCastManager castManager;

    private static VideoCastManager initializeVideoCastManager(Context ctx) {
        if (castManager == null) {
            final CastConfiguration configuration = new CastConfiguration.Builder(APPLICATION_ID)
                    .addNamespace(ctx.getString(R.string.receiver_data_channel))
                    .enableNotification()
                    .enableWifiReconnection()
                    .enableDebug()
                    .build();

            castManager = VideoCastManager.initialize(ctx, configuration);
            castManager.setStopOnDisconnect(true);
        }
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

        initializeVideoCastManager(getApplicationContext());
    }
}

