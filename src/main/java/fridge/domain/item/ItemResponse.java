package fridge.domain.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by dmatsumoto on 10/31/17.
 */

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ItemResponse {

    public String id;
    public String name;
    public String startDate;
    public String validUntilDate;
    public boolean active;

    public ItemResponse(){}
}
