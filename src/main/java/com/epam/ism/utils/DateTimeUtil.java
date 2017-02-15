package com.epam.ism.utils;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public final class DateTimeUtil {
    public static String getStrippedRequestUri(String uri) {
        int indexOf = uri.lastIndexOf('/');
        return uri.substring(indexOf, uri.length());
    }

    /**
     * Converts the given java.util.Date to java.sql.Date.
     * @param date The java.util.Date to be converted to java.sql.Date.
     * @return The converted java.sql.Date.
     */
    public static Date toSqlDate(java.util.Date date) {
        return (date != null) ? new Date(date.getTime()) : null;
    }

    public static java.util.Date toUtilDate(Date date) {
        return (date != null) ? new java.util.Date(date.getTime()) : null;
    }

    public static java.util.Date getDateFromString(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        try {
            return format.parse(dateStr);
        } catch (ParseException ignored) {}

        return null;
    }

    public static String getFormattedDateTime(java.util.Date date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        return dateFormatter.format(date);
    }

    public static java.util.Date getDateTime(String dateStr, String timeStr) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
        if (!timeStr.isEmpty()) timeStr += ":00";

        try {
            return dateFormatter.parse((dateStr + " " + timeStr).trim());
        } catch (ParseException e) {
            throw new DateTimeUtilException("Method failed: dateFormatter.parse(".concat(dateStr + " " + timeStr).concat(")"));
        }
    }

    public static java.util.Date getArrivalDateTime(java.util.Date departureDate, long duration) {
        long[] timeValues = getTimeValues(duration);

        long days = timeValues[0];
        long hours = timeValues[1];
        long minutes = timeValues[2];
        long seconds = timeValues[3];

        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal.setTime(departureDate);
        cal.add(Calendar.DAY_OF_MONTH, (int) days);
        cal.add(Calendar.HOUR_OF_DAY, (int) hours);
        cal.add(Calendar.MINUTE, (int) minutes);
        cal.add(Calendar.SECOND, (int) seconds);

        return cal.getTime();

    }

    public static String getParsedTravelTime(long duration) {
        long[] timeValues = getTimeValues(duration);

        long days = timeValues[0];
        long hours = timeValues[1];
        long minutes = timeValues[2];
        long seconds = timeValues[3];

        return String.format("%d days %d hours %d min",days,hours,minutes);
    }

    private static long[] getTimeValues(long duration) {

        TimeUnit mls = TimeUnit.MILLISECONDS;
        long days = mls.toDays(duration);
        long hours = mls.toHours(duration)%24;
        long minutes = mls.toMinutes(duration)%60;
        long seconds = mls.toSeconds(duration)%60;

        return new long[]{
                days,
                hours,
                minutes,
                seconds
        };

    }

    public static long getParkingTimeInMls(int t) {
        return t*60*1000;
    }

}
