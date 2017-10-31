package fridge.domain;

import lombok.*;

/**
 * Created by dmatsumoto on 10/26/17.
 */

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SuccessMessage {
    private String ts;
    private String message;

    public SuccessMessage(){}
}


