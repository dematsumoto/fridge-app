package fridge.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dmatsumoto on 10/31/17.
 */

@Setter
@Getter
@AllArgsConstructor
public class ItemRequest {

    public String name;
    public String startDate;
    public String validUntilDate;
    public boolean active;

    public ItemRequest(){}
}
