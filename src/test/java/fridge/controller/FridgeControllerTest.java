package fridge.controller;

import fridge.domain.item.ItemResponse;
import fridge.service.ItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by douglas on 1/18/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class FridgeControllerTest {
    @Mock
    ItemService itemServiceMock;

    @InjectMocks
    FridgeController fridgeController;

    List<ItemResponse> itemsList;

    @Test
    public void testGetAllItems(){
        //when(itemServiceMock.findAllItems()).thenReturn(
    }
}
