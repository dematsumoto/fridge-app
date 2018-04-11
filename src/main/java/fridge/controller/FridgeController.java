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

@Slf4j
@RequestMapping("/fridge")
@RestController
public class FridgeController {


    @Autowired
    ItemService itemService;

    @RequestMapping(method = RequestMethod.GET, value = "/item/{id}")
    @ResponseBody
    public ResponseEntity<?> getById(@PathVariable String id){
        log.info("Fetching item given id: {}", id);
        Item itemResponse = itemService.findItemById(id);

        if (itemResponse == null) {
            log.error("item not found id: {0}", id);
            throw new ItemNotFoundException("Item not found id: {0}", id);
        }
        return ResponseBuilder.okItem(itemResponse);
    }

    @RequestMapping(value = "/createSample", method = RequestMethod.GET)
    public void createItem(){
        itemService.createSample();
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> fridgeAll() {
        log.info("Retrieving all Items");
        List<Item> itemList = itemService.findAllItems();

        if (itemList.isEmpty()){
            log.error("No Items in the collection");
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

    //TODO
    //Remove this method and replace by deleteItemById()
    @RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteItemByName(@PathVariable String name){
        log.info("Deleting item given name: {}", name);
        Item itemResponse = itemService.removeItem(name);

        if (itemResponse == null) {
            log.error("item not found: {0}", name);
            throw new ItemNotFoundException("Item not found: {0}", name);
        }

        String message = "Item successfully removed: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);
    }

    @RequestMapping(value = "/item/{id}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteItemById(@PathVariable String id){
        log.info("Deleting item given id: {}", id);
        Item itemResponse = itemService.removeItemById(id);

        if (itemResponse == null) {
            log.error("item not found: {0}", id);
            throw new ItemNotFoundException("Item not found: {0}", id);
        }

        String message = "Item successfully removed: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> updateItem(@RequestBody @Valid ItemRequest item) {
        log.info("updating Item: {}", item.getName());
        Item itemResponse = itemService.findItemById(item.getId());
        if (itemResponse == null) {
            log.error("item not found id: {0}", item.getId());
            throw new ItemNotFoundException("Item not found id: {0}", item.getId());
        }

        itemResponse = itemService.updateItem(item);

        if (itemResponse == null) {
            log.error("item not found: {}", item.getName());
            throw new ItemNotFoundException("Item not found: {}", item.getName());
        }

        log.info("Successfully updated Item");
        String message = "Item successfully updated: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/{id}/status")
    @ResponseBody
    public ResponseEntity<?> inactivateItem(@PathVariable String id, @RequestBody ItemRequest itemRequest){
        log.info("inactivating Item: {}", id);
        Item itemResponse = itemService.findItemById(id);

        if (itemResponse == null) {
            log.error("item not found: {}", id);
            throw new ItemNotFoundException("Item not found: {}", id);
        }

        itemService.inactivateItem(itemRequest);
        log.info("Successfully inactivated Item");
        String message = "Item successfully inactivated: " + itemResponse.getName();
        return ResponseBuilder.okMessage(message);
    }
}

