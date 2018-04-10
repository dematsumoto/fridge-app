package fridge.util;

import fridge.domain.message.SuccessMessage;
import fridge.domain.item.Item;
import fridge.domain.item.ItemResponse;
import fridge.service.ItemService;
import org.joda.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class ResponseBuilder {

    public static ResponseEntity<ItemResponse> okItem(Item item){
        ItemResponse itemResponse = ItemResponse.builder().id(item.getId()).name(item.getName())
                .startDate(DateUtil.dateToStringFormatter(item.getStartDate()))
                .validUntilDate(DateUtil.dateToStringFormatter(item.getValidUntilDate())).active(item.isActive())
                .status(ItemService.setItemStatus(item.getValidUntilDate()))
                .build();

        return new ResponseEntity<>(itemResponse, HttpStatus.OK);
    }

    public static ResponseEntity<List<ItemResponse>> okItem(List<Item> listItem) {

        List<ItemResponse> items = new ArrayList<>();
        for(Item item:listItem){
            ItemResponse itemResponse = ItemResponse.builder().id(item.getId()).name(item.getName())
                    .startDate(DateUtil.dateToStringFormatter(item.getStartDate()))
                    .validUntilDate(DateUtil.dateToStringFormatter(item.getValidUntilDate())).active(item.isActive())
                    .status(ItemService.setItemStatus(item.getValidUntilDate()))
                    .build();
            items.add(itemResponse);

        }

        return new ResponseEntity<>(items, HttpStatus.OK);

    }

    public static ResponseEntity<SuccessMessage> okMessage (String messageBody){
        SuccessMessage message = SuccessMessage.builder().ts(DateUtil.dateToStringFormatter(LocalDateTime.now()))
                .message(messageBody).build();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
