package fridge.domain.repo;

import fridge.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by douglas on 10/3/17.
 */

@Repository
public class ItemRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    final String ITEM_COLLECTION = "item";

    public void create(Item item){

        mongoTemplate.insert(item);
    }

    public Item findItemByName(String name){
        Query query = new Query(Criteria.where("name").is(name));

        return mongoTemplate.findOne(query, Item.class, ITEM_COLLECTION);
    }

    public List<Item> findAll(){

        return (List<Item>) mongoTemplate.findAll(Item.class);
    }

    public Item removeItem(String name){
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findAndRemove(query, Item.class, ITEM_COLLECTION);

    }

    public Item updateItem(Item item){
        Query query = new Query(Criteria.where("name").is(item.name));
        mongoTemplate.updateFirst(query, Update.update("validUntilDate",item.validUntilDate), Item.class, ITEM_COLLECTION);
        return mongoTemplate.findOne(query, Item.class, ITEM_COLLECTION);
    }

}
