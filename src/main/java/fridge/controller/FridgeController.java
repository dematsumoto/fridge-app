package fridge.controller;

import fridge.domain.Item;
import fridge.exception.ItemNotFoundException;
import fridge.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import java.util.List;



/**
 * Created by douglas on 10/3/17.
 */
@Slf4j
@RequestMapping("/fridge")
@RestController
public class FridgeController {


    @Autowired
    ItemService itemService;


    @GetMapping(value = "/{item}")
    @ResponseBody
    public ResponseEntity<?> fridge(@PathVariable String item){
        log.info("Fetching item given name: {}", item);

        Item itemResponse = itemService.findItem(item);
        if (itemResponse == null) {
            log.error("item not found: {0}", item);
            throw new ItemNotFoundException("Item not found: {0}", item);

        }
        return new ResponseEntity<>(itemResponse, HttpStatus.OK);

    }

    @RequestMapping("/create")
    public void createItem(){
        itemService.create();
    }

    @RequestMapping("/fridgeAll")
    public List<Item> fridgeAll() {

        return itemService.findAllItems();
    }
}

