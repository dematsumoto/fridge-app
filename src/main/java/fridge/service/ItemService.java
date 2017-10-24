package fridge.service;

import fridge.domain.Item;
import fridge.domain.repo.ItemRepository;
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


    public void create() {

        String startDateString = "01-02-2017";
        String validUntilString = "01-03-2017";

        Item buceta = new Item("test", startDateString, validUntilString ,true);
        itemRepository.create(buceta);

    }
}
