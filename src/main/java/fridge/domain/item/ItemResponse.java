package fridge.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ItemResponse {

    private String id;
    private String name;
    private String startDate;
    private String validUntilDate;
    private boolean active;
    private String status;

    public ItemResponse(){}
}
