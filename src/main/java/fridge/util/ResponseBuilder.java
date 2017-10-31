package fridge.util;

import fridge.domain.SuccessMessage;
import fridge.domain.item.Item;
import fridge.domain.item.ItemResponse;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by douglas on 10/27/17.
 */
public class ResponseBuilder {

    public static ResponseEntity<ItemResponse> okItem(Item item){
        ItemResponse itemResponse = ItemResponse.builder().id(item.getId()).name(item.getName())
                .startDate(DateUtil.dateToStringFormatter(item.getStartDate()))
                .validUntilDate(DateUtil.dateToStringFormatter(item.getValidUntilDate())).active(item.isActive())
                .build();

        return new ResponseEntity<ItemResponse>(itemResponse, HttpStatus.OK);
    }

    public static ResponseEntity<List<ItemResponse>> okItem(List<Item> listItem) {

        List<ItemResponse> items = new ArrayList<>();
        for(Item item:listItem){
            ItemResponse itemResponse = ItemResponse.builder().id(item.getId()).name(item.getName())
                    .startDate(DateUtil.dateToStringFormatter(item.getStartDate()))
                    .validUntilDate(DateUtil.dateToStringFormatter(item.getValidUntilDate())).active(item.isActive())
                    .build();
            items.add(itemResponse);

        }

        return new ResponseEntity<List<ItemResponse>>(items, HttpStatus.OK);

    }

    public static ResponseEntity<SuccessMessage> okMessage (String messageBody){
        SuccessMessage message = SuccessMessage.builder().ts(DateUtil.dateToStringFormatter(LocalDateTime.now()))
                .message(messageBody).build();
        return new ResponseEntity<SuccessMessage>(message, HttpStatus.OK);
    }


}
