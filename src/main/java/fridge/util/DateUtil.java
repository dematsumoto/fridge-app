package fridge.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static String dateToStringFormatter(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
        return formatter.print(dateTime);
    }

    public static LocalDateTime stringToDateFormatter(String dateTime){
        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_FORMAT);
        return formatter.parseLocalDateTime(dateTime);
    }
}
