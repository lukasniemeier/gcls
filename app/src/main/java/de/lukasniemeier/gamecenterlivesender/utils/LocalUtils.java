package de.lukasniemeier.gamecenterlivesender.utils;


import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalUtils {

    public static DateTime parseDateTimeEastern(String gameTimeEst) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(gameTimeEst, formatter);
        return localDateTime.toDateTime(DateTimeZone.forID("America/New_York"));
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> T parseJson(String json) {
        JsonParser parser = new JsonParser();
        return (T) parser.parse(json);
    }

    public static void fixSwipeLayoutProgress(Activity activity , SwipeRefreshLayout swipeRefreshLayout) {
        TypedValue typed_value = new TypedValue();
        activity.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, typed_value, true);
        int size = activity.getResources().getDimensionPixelSize(typed_value.resourceId);
        swipeRefreshLayout.setProgressViewOffset(false, -size, size);
    }

}
