package fridge.service;

import fridge.domain.item.Item;
import fridge.domain.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    ItemRepository itemRepository;

    public HashMap<String, Integer> overview(){
        List<Item> allItems = itemRepository.findAll();

        return classifyItems(allItems);
    }

    private HashMap<String, Integer> classifyItems(List<Item> itemList){
        HashMap<Integer, String> itemMap = new HashMap<>();
        
        return null;
    }
}
