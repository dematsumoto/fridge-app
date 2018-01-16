package fridge.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by douglas on 10/3/17.
 */

@Configuration
public class MongoConfig {

    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient("localhost");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), "fridgeApp");
    }

    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions.builder().serverSelectionTimeout(5000).build();
    }
}
