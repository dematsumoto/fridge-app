package fridge.util;

import fridge.domain.Item;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.ResponseEntity;

/**
 * Created by douglas on 10/27/17.
 */
public class ResponseBuilder {

    //TODO create ItemResponse domain with dates as String
    public ResponseEntity<?> buildResponse(Item item){
        //item.startDate = dateToStringFormatter(item.startDate);


        return null;
    }

    private String dateToStringFormatter(Item item){
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.print(item.getStartDate());

    }

}
