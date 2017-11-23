package fridge.controller;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import fridge.exception.ItemNotFoundException;
import fridge.service.ItemService;
import fridge.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
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


    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    @ResponseBody
    public ResponseEntity<?> fridge(@PathVariable String name){
        log.info("Fetching item given name: {}", name);
        Item itemResponse = itemService.findItem(name);

        if (itemResponse == null) {
            log.error("item not found: {0}", name);
            throw new ItemNotFoundException("Item not found: {0}", name);

        }

        return ResponseBuilder.okItem(itemResponse);

    }

    @RequestMapping(value = "/createSample", method = RequestMethod.GET)
    public void createItem(){
        itemService.createSample();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> fridgeAll() {
        List<Item> itemList = itemService.findAllItems();

        if (itemList.isEmpty()){
            log.error("No Items in the collection");
            throw new ItemNotFoundException("Fridge is empty");
        }

        return ResponseBuilder.okItem(itemList);

    }

    @RequestMapping(value = "/addItem", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> itemPost(@RequestBody @Valid ItemRequest item) {
        log.info("posting an Item {}", item);
        itemService.postItem(item);
        log.info("Successfully posted Item");
        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteItem(@PathVariable String name){
        log.info("Deleting item given name: {}", name);
        Item itemResponse = itemService.removeItem(name);

        if (itemResponse == null) {
            log.error("item not found: {0}", name);
            throw new ItemNotFoundException("Item not found: {0}", name);

        }

        String message = "Item successfully removed: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateItem(@RequestBody @Valid ItemRequest item) {
        log.info("updating Item: {}", item.getName());
        Item itemResponse = itemService.updateItem(item);

        if (itemResponse == null) {
            log.error("item not found: {}", item.getName());
            throw new ItemNotFoundException("Item not found: {}", item.getName());

        }

        log.info("Successfully updated Item");
        String message = "Item successfully updated: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);

    }
}

