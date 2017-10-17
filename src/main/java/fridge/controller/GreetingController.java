package fridge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by douglas on 10/3/17.
 */

@RestController
public class GreetingController {

    @RequestMapping("/")
    public String index(){
        return "Greetings from Spring Boot! Fridge App";
    }
}
