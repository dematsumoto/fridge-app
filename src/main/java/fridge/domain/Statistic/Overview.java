package fridge.domain.Statistic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Overview {

    private int expireSoonItems;
    private int expiredItems;
    private int stillGoodItems;

    public Overview() {}
}
