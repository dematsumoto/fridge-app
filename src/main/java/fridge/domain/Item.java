package fridge.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;

/**
 * Created by douglas on 10/3/17.
 */

@Getter
@Setter
//@AllArgsConstructor
public class Item {

    @Id
    public String Id;

    public String name;
    public String startDate;
    public String validUntilDate;
    public boolean isActive;

    //public Item(){}

    public Item(String name, String startDate, String validUntilDate, boolean isActive) {
        this.name = name;
        this.startDate = startDate;
        this.validUntilDate = validUntilDate;
        this.isActive = isActive;
    }

}
