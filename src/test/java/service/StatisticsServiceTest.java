package service;

import fridge.domain.Statistic.Overview;
import fridge.domain.item.Item;
import fridge.domain.repo.ItemRepository;
import fridge.service.StatisticsService;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.beans.SamePropertyValuesAs.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

    @Mock
    ItemRepository itemRepository;

    @InjectMocks
    StatisticsService statisticsService;

    @Test
    public void fridgeOverview_shouldReturnItemsClassified(){
        // Data Setup
        Item expiredItem = new Item("Milk", LocalDateTime.now(), LocalDateTime.now().minusDays(2),true);
        Item goodItem = new Item("Apple", LocalDateTime.now(), LocalDateTime.now(),true);
        Item expireSoonItem = new Item("Apple", LocalDateTime.now(), LocalDateTime.now().plusDays(2),true);
        List<Item> itemList = new ArrayList<>();
        itemList.add(expireSoonItem);
        itemList.add(expiredItem);
        itemList.add(goodItem);

        Overview expectedResponse = new Overview(1,1,1);

        when(itemRepository.findAll()).thenReturn(itemList);
        Overview response = statisticsService.fridgeOverview();
        assertThat(response, samePropertyValuesAs(expectedResponse));
    }
}
