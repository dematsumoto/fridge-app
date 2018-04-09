package fridge.service;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import fridge.domain.item.ItemResponse;
import fridge.domain.repo.ItemRepository;
import fridge.exception.InvalidAddItemCriteriaException;
import fridge.util.DateUtil;
import fridge.util.ResponseBuilder;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemService {

    private static final String EXPIRE_SOON = "ExpireSoon";
    private static final String EXPIRED = "Expired";
    private static final String GOOD = "Good";

    @Autowired
    ItemRepository itemRepository;

    public Item findItem(String name){
        return itemRepository.findItemByName(name);
    }

    public Item findItemById(String id){
        return itemRepository.findById(id);
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

    public void postItem(ItemRequest itemRequest) {
        if (StringUtils.isEmpty(itemRequest.getName())){
            throw new InvalidAddItemCriteriaException("name is required to add a new Item");
        }

        if (StringUtils.isEmpty(itemRequest.getStartDate()) || StringUtils.isEmpty(itemRequest.getValidUntilDate())){
            throw new InvalidAddItemCriteriaException("startDate and validUntilDate is required to add a new Item");
        }


        Item item = new Item(itemRequest.getName(), DateUtil.stringToDateFormatter(itemRequest.getStartDate()),
                DateUtil.stringToDateFormatter(itemRequest.getValidUntilDate()),
                        itemRequest.isActive());

        itemRepository.create(item);
    }

    public Item removeItem(String name){
        return itemRepository.removeItem(name);

    }

    public Item updateItem(ItemRequest itemRequest){
        if (StringUtils.isEmpty(itemRequest.getId())){
            throw new InvalidAddItemCriteriaException("id is required to update a Item");
        }

        if (StringUtils.isEmpty(itemRequest.getName())){
            throw new InvalidAddItemCriteriaException("name is required to update a Item");
        }

        if (StringUtils.isEmpty(StringUtils.isEmpty(itemRequest.getValidUntilDate()))){
            throw new InvalidAddItemCriteriaException("validUntilDate is required to update a Item");
        }

        Item item = new Item(itemRequest.getId(),itemRequest.getName(), DateUtil.stringToDateFormatter(itemRequest.getStartDate()),
                DateUtil.stringToDateFormatter(itemRequest.getValidUntilDate()),
                itemRequest.isActive());

        return itemRepository.updateItem(item);
    }

    public void inactivateItem(ItemRequest itemRequest){
        if (StringUtils.isEmpty(itemRequest.getId())){
            throw new InvalidAddItemCriteriaException("id is required to inactivate an Item");
        }

        itemRepository.inactivateItem(itemRequest.getId());
    }

    public static String setItemStatus(LocalDateTime validUntilData){
        DateTime today = new DateTime();
        Duration diffDays;
        long numDiffDays;
        diffDays = new Duration(today, validUntilData.toDateTime());
        numDiffDays = diffDays.getStandardDays();
        if (numDiffDays == 0){
            return EXPIRE_SOON;
        }
        else if (numDiffDays < 0){
            return EXPIRED;
        }

        return GOOD;
    }
}
