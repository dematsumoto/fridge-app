package service;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;

import fridge.domain.item.Item;
import fridge.domain.item.ItemRequest;
import fridge.domain.repo.ItemRepository;
import fridge.exception.InvalidAddItemCriteriaException;
import fridge.service.ItemService;
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

}
