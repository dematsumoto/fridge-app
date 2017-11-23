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

    private String id;
    private String name;
    private String startDate;
    private String validUntilDate;
    private boolean active;

    public ItemRequest(){}
}
