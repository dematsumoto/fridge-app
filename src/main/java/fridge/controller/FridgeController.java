package fridge.controller;

import fridge.domain.Item;
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
//
//    @RequestMapping("/fridge")
//    public Item fridge() {
//
//        //Item itemResponse = itemService.findItem("ovos");
//        //return itemResponse;
//        return itemService.findItem("ovos");
//    }

    @GetMapping(value = "/{item}")
    @ResponseBody
    public ResponseEntity<?> fridge(@PathVariable String item){
        log.info("Fetching item given name: {}", item);

        try {
            Item itemResponse = itemService.findItem(item);
            return new ResponseEntity<>(itemResponse, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
        }


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

