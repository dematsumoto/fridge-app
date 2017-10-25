package fridge.domain;

import lombok.Getter;
import lombok.Setter;
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
    public String startDate;
    public String validUntilDate;
    public boolean active;

    public Item(){}

    public Item(String name, String startDate, String validUntilDate, boolean active) {
        this.name = name;
        this.startDate = startDate;
        this.validUntilDate = validUntilDate;
        this.active = active;
    }

    public boolean isActive(){
        return this.active;
    }


}
