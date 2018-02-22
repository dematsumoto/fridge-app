package fridge.controller;

import fridge.domain.Statistic.Overview;
import fridge.service.ItemService;
import fridge.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/fridgeStats")
@RestController
public class StatisticsController {


    @Autowired
    ItemService itemService;

    @Autowired
    StatisticsService statsService;

    @RequestMapping(method = RequestMethod.GET, value = "/overview")
    @ResponseBody
    public ResponseEntity<?> getOverview(){
        log.info("Retrieving overview Stats");
        Overview overview = statsService.fridgeOverview();

        return new ResponseEntity<>(overview, HttpStatus.OK);
    }

}
