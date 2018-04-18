package fridge.integrationtest;

import com.google.gson.Gson;
import fridge.domain.item.ItemRequest;
import fridge.domain.item.ItemResponse;
import fridge.service.ItemService;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FridgeControllerTest {

    private static final String INVALID_ID = "123";
    private static final String TEST_ID = "testId";
    private static final String UPDATE_ITEM_ID = "UpdateItemId";
    private static final String FRIDGE_PATH = "/fridge/";
    private static final String ITEM_PATH = "/item/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;

    private Gson gson = new Gson();


    @Test
    public void findById_withNonExistentId_shouldReturnBadRequest() throws Exception{
        this.mockMvc.perform(get(FRIDGE_PATH + ITEM_PATH + INVALID_ID)).andExpect(status().isBadRequest());
    }

    @Test
    public void findById_withExistingId_shouldReturnItem() throws Exception{
        itemService.removeItemById(TEST_ID);
        itemService.createSample(TEST_ID);

        ItemResponse itemResponse = new ItemResponse(TEST_ID,"test",
                new LocalDate().toString(),
                new LocalDate().plusDays(5).toString(),
                true,"Good");

        JSONObject expectedItem = new JSONObject(gson.toJson(itemResponse));

        this.mockMvc.perform(get(FRIDGE_PATH + ITEM_PATH + TEST_ID)).andExpect(status().isOk())
                .andExpect(content().json(expectedItem.toString()));
        itemService.removeItemById(TEST_ID);
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

    @Test
    @Ignore
    public void updateItem_withValidItem_shouldReturnOk() throws Exception{
        //Set data
        itemService.removeItemById(UPDATE_ITEM_ID);
        itemService.createSample(UPDATE_ITEM_ID);

        ItemResponse itemResponseBeforeUpdate = new ItemResponse(UPDATE_ITEM_ID,"test",
                new LocalDate().toString(),
                new LocalDate().plusDays(5).toString(),
                true,"Good");

        JSONObject expectedItemBefore = new JSONObject(gson.toJson(itemResponseBeforeUpdate));

        this.mockMvc.perform(get(FRIDGE_PATH + ITEM_PATH + UPDATE_ITEM_ID)).andExpect(status().isOk())
                .andExpect(content().json(expectedItemBefore.toString()));

        //Update Item
        ItemResponse itemResponseAfterUpdate = new ItemResponse(UPDATE_ITEM_ID,"testUpdate",
                new LocalDate().plusDays(1).toString(),
                new LocalDate().plusDays(6).toString(),
                true,"Good");

        ItemRequest updateItem = new ItemRequest(UPDATE_ITEM_ID, "testUpdate",
                new LocalDate().plusDays(1).toString(),
                new LocalDate().plusDays(6).toString(),
                true);

        JSONObject expectedItemAfter = new JSONObject(gson.toJson(itemResponseAfterUpdate));
        this.mockMvc.perform(post(FRIDGE_PATH + "/update")
                .content(updateItem.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
                .andExpect(content().json(expectedItemAfter.toString()));

    }

}
