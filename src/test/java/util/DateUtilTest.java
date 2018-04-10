package util;

import fridge.util.DateUtil;
import org.joda.time.LocalDateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DateUtilTest {

    @Test
    public void dateToStringFormatter_withLocalDateTime_shouldReturnConvertedString(){
        LocalDateTime date = new LocalDateTime().withDate(2018, 01,01);
        String convertedDate = DateUtil.dateToStringFormatter(date);
        assertThat(convertedDate, equalTo("2018-01-01"));
    }

    @Test
    public void stringToDateFormatter_withStringDate_shouldReturnConvertedLocalDateTime(){
        String date = "2018-01-01";
        LocalDateTime convertedDate = DateUtil.stringToDateFormatter(date);
        LocalDateTime expectedDate = new LocalDateTime().withDate(2018,01,01).withTime(0,0,0,0);
        assertThat(convertedDate, equalTo(expectedDate));
    }
}
