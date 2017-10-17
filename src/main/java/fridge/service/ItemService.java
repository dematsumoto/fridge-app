package fridge.service;

import fridge.domain.Item;
import fridge.domain.ItemImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by douglas on 10/3/17.
 */
@Service
public class ItemService {

    @Autowired
    ItemImpl itemImpl;


    public Item findItem(String name){
        return itemImpl.findItemByName(name);
    }

    public List<Item> findAllItems(){
        return itemImpl.findAll();
    }


    public void create() {

        String startDateString = "01-02-2017";
        String validUntilString = "01-03-2017";

        Item buceta = new Item("test", startDateString, validUntilString ,true);
        itemImpl.create(buceta);

    }
}
