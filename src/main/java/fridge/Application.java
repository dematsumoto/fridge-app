package fridge;

import fridge.config.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Created by douglas on 10/3/17.
 */

@SpringBootApplication
@Import({MongoConfig.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
