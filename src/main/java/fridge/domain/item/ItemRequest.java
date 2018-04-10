package fridge.domain.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
