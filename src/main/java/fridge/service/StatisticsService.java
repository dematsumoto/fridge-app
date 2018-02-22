package fridge.service;

import fridge.domain.Statistic.Overview;
import fridge.domain.item.Item;
import fridge.domain.repo.ItemRepository;
import org.joda.time.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {

    public static final int ZERO = 0;

    @Autowired
    ItemRepository itemRepository;

    public Overview fridgeOverview(){
        List<Item> allItems = itemRepository.findAll();
        return classifyItems(allItems);
    }

    private Overview classifyItems(List<Item> itemList){
        int expireSoonItems = ZERO;
        int expiredItems = ZERO;
        int stillGoodItems = ZERO;

        DateTime today = new DateTime();
        Duration diffDays;
        long numDiffDays;

        for (Item item:itemList){
            diffDays = new Duration(today, item.getValidUntilDate().toDateTime());
            numDiffDays = diffDays.getStandardDays();
            if (numDiffDays == ZERO){
                expireSoonItems++;
            }
            else if (numDiffDays < ZERO){
                expiredItems++;
            }
            else {
                stillGoodItems++;
            }

        }
        return new Overview(expireSoonItems, expiredItems, stillGoodItems);
    }
}
