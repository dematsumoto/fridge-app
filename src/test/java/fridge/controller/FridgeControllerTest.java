package fridge.controller;

import fridge.domain.item.Item;
import fridge.service.ItemService;
import org.joda.time.LocalDateTime;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FridgeControllerTest {

    private static final String INVALID_ID = "123";
    private static final String VALID_ID = "1234";
    private static final String FRIDGE_PATH = "/fridge/";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void findById_withNonExistentId_shouldReturnBadRequest() throws Exception{
        when(itemService.findItemById(INVALID_ID)).thenReturn(null);

        this.mockMvc.perform(get(FRIDGE_PATH + INVALID_ID)).andExpect(status().isBadRequest());
    }

    @Test
    public void findById_withExistingId_shouldReturnItem() throws Exception{
        when(itemService.findItemById(VALID_ID)).thenReturn(new Item("Milk", LocalDateTime.now(), LocalDateTime.now(), true));

        this.mockMvc.perform(get(FRIDGE_PATH + VALID_ID)).andExpect(status().isOk());
    }

    @Test
    public void fridgeAll__withEmptyDatabase_shouldReturnItemList() throws Exception{
        when(itemService.findAllItems()).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get(FRIDGE_PATH)).andExpect(status().isOk());
    }
}
