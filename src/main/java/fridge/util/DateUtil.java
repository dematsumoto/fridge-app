package fridge.util;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by dmatsumoto on 10/31/17.
 */
public final class DateUtil {

    public static String dateToStringFormatter(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return formatter.print(dateTime);

    }

    public static LocalDateTime stringToDateFormatter(String dateTime){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        return formatter.parseLocalDateTime(dateTime);
    }
}
