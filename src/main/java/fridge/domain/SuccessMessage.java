package fridge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by dmatsumoto on 10/26/17.
 */

@Setter
@Getter
@ToString
@AllArgsConstructor
public class SuccessMessage {
    private String ts;
    private String message;

    public SuccessMessage(){}
}


