package fridge.service;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import fridge.domain.item.ItemResponse;
import fridge.domain.repo.ItemRepository;
import fridge.util.DateUtil;
import fridge.util.ResponseBuilder;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by douglas on 10/3/17.
 */
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;


    public Item findItem(String name){
        return itemRepository.findItemByName(name);
    }

    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public void createSample() {
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime validUntilDate= startDate.plusDays(5);

        Item sample = new Item("test", startDate, validUntilDate,true);
        itemRepository.create(sample);

    }

    public void postItem(ItemRequest itemRequest){
        Item item = new Item(itemRequest.getName(), DateUtil.stringToDateFormatter(itemRequest.getStartDate()),
                DateUtil.stringToDateFormatter(itemRequest.getValidUntilDate()),
                        itemRequest.isActive());

        itemRepository.create(item);
    }

    public Item removeItem(String name){
        return itemRepository.removeItem(name);

    }

    public Item updateItem(ItemRequest itemRequest){
        Item item = new Item(itemRequest.getName(), DateUtil.stringToDateFormatter(itemRequest.getStartDate()),
                DateUtil.stringToDateFormatter(itemRequest.getValidUntilDate()),
                itemRequest.isActive());

        return itemRepository.updateItem(item);
    }
}
