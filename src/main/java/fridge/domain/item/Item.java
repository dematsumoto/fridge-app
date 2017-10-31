package fridge.domain.item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;

/**
 * Created by douglas on 10/3/17.
 */

@Getter
@Setter
public class Item {

    @Id
    public String id;


    public String name;
    public LocalDateTime startDate;
    public LocalDateTime validUntilDate;
    public boolean active;

    public Item(){}

    public Item(String name, LocalDateTime startDate, LocalDateTime validUntilDate, boolean active) {
        this.name = name;
        this.startDate = startDate;
        this.validUntilDate = validUntilDate;
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }


}
