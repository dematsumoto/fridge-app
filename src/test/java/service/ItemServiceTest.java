package service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import fridge.domain.repo.ItemRepository;
import fridge.exception.InvalidAddItemCriteriaException;
import fridge.service.ItemService;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    ItemService itemService;

    Item itemSample;
    ItemRequest itemRequest;

    @Before
    public void setup(){
        itemSample = new Item("Milk", LocalDateTime.now(), LocalDateTime.now(), true);
        itemRequest = new ItemRequest("123","milk", "2018-03-13","2018-03-13", true);
    }

    @Test
    public void findItemTest_withName_shouldReturnItem(){
        when(itemRepository.findItemByName("Banana")).thenReturn(new Item());
        assertThat(itemService.findItem("Banana"), notNullValue());
    }

    @Test(expected = InvalidAddItemCriteriaException.class)
    public void postITem_withEmptyName_shouldThrowException(){
        itemService.postItem(new ItemRequest("123","", "2018-03-13","2018-03-13", true));
    }

    @Test(expected = InvalidAddItemCriteriaException.class)
    public void postITem_withEmptyStartDate_shouldThrowException(){
        itemService.postItem(new ItemRequest("123","Banana", "","2018-03-13", true));
    }

    @Test(expected = InvalidAddItemCriteriaException.class)
    public void postITem_withEmptyValidUntilDate_shouldThrowException(){
        itemService.postItem(new ItemRequest("123","Banana", "2018-03-13","", true));
    }

    @Test
    public void postITem_withValidRequest_shouldNotThrowException(){
        itemService.postItem(itemRequest);
    }

    @Test
    public void updateITem_withItem_shouldReturnItem(){
        when(itemRepository.updateItem(itemSample)).thenReturn(new Item());
        assertThat(itemRepository.updateItem(itemSample), notNullValue());
    }

    @Test(expected = InvalidAddItemCriteriaException.class)
    public void updateItem_withEmptyId_shouldThrowException(){
        itemService.updateItem(new ItemRequest("","Banana","2018-03-13","2018-03-13",true));
    }

    @Test
    public void setItemStatus_withExpiredItem(){
        //itemService.setItemStatus()
    }

}
