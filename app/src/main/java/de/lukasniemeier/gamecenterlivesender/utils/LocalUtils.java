package de.lukasniemeier.gamecenterlivesender.utils;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class LocalUtils {

    public static DateTime parseDateTimeEst(String gameTimeEst) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(gameTimeEst, formatter);
        return localDateTime.toDateTime(DateTimeZone.forID("EST"));
    }

    @SuppressWarnings("unchecked")
    public static <T extends JsonElement> T parseJson(String json) {
        JsonParser parser = new JsonParser();
        return (T) parser.parse(json);
    }
}
