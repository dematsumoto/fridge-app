package fridge.domain.repo;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public Item findById(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Item.class, ITEM_COLLECTION);
    }

    public List<Item> findAll(){
        Query query = new Query(Criteria.where("active").is(true));
        return mongoTemplate.find(query, Item.class, ITEM_COLLECTION);
    }

    public Item removeItem(String name){
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findAndRemove(query, Item.class, ITEM_COLLECTION);

    }

    public Item updateItem(Item itemRequest){
        Query query = new Query(Criteria.where("_id").is(itemRequest.getId()));
        mongoTemplate.save(itemRequest, ITEM_COLLECTION);
        return mongoTemplate.findOne(query, Item.class, ITEM_COLLECTION);
    }

    public void inactivateItem(String id){
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.updateFirst(query, Update.update("active", false), Item.class, ITEM_COLLECTION);
    }

}
