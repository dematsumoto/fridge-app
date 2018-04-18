package fridge.integrationtest;

import fridge.domain.item.Item;
import fridge.service.ItemService;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FridgeControllerTest {

    private static final String INVALID_ID = "123";
    private static final String VALID_ID = "testId";
    private static final String FRIDGE_PATH = "/fridge/";
    private static final String ITEM_PATH = "/item/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;


    @Test
    public void findById_withNonExistentId_shouldReturnBadRequest() throws Exception{
        this.mockMvc.perform(get(FRIDGE_PATH + ITEM_PATH + INVALID_ID)).andExpect(status().isBadRequest());
    }

    @Test
    public void findById_withExistingId_shouldReturnItem() throws Exception{
        itemService.createSample(VALID_ID);
        this.mockMvc.perform(get(FRIDGE_PATH + ITEM_PATH+ VALID_ID)).andExpect(status().isOk());
        itemService.removeItemById(VALID_ID);
    }

    @Test
    public void fridgeAll_shouldReturnListOfItem() throws Exception{
        itemService.createSample("item1");
        itemService.createSample("item2");

        this.mockMvc.perform(get(FRIDGE_PATH)).andExpect(status().isOk())
                .andExpect(jsonPath(".id", hasItem("item2")))
                .andExpect(jsonPath(".id", hasItem("item1")));

        itemService.removeItemById("item1");
        itemService.removeItemById("item2");
    }

}
