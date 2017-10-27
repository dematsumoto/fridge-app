package fridge.controller;

import fridge.domain.Item;
import fridge.domain.SuccessMessage;
import fridge.exception.ItemNotFoundException;
import fridge.service.ItemService;
import static fridge.util.MessageUtils.messageBuilder;
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

    @GetMapping("/create")
    public void createItem(){
        itemService.create();
    }

    @GetMapping()
    public ResponseEntity<?> fridgeAll() {
        List<Item> itemList = itemService.findAllItems();

        if (itemList.isEmpty()){
            log.error("No Items in the collection");
            throw new ItemNotFoundException("Fridge is empty");
        }

        return new ResponseEntity<>(itemList, HttpStatus.OK);

    }

    @PostMapping(value = "/addItem")
    @ResponseBody
    public ResponseEntity<?> itemPost(@RequestBody @Valid Item item) {
        log.info("posting an Item", item);
        itemService.postItem(item);
        log.info("Successfully posted Item");
        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/{item}")
    @ResponseBody
    public ResponseEntity<?> deleteItem(@PathVariable String item){
        log.info("Deleting item given name: {}", item);

        Item itemResponse = itemService.removeItem(item);
        if (itemResponse == null) {
            log.error("item not found: {0}", item);
            throw new ItemNotFoundException("Item not found: {0}", item);

        }
        String message = "Item successfully removed: " + itemResponse.getName();
        return new ResponseEntity<>(messageBuilder(message), HttpStatus.OK);
    }
}

