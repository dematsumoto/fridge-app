package fridge.domain.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by douglas on 10/19/17.
 */

@Getter
@ToString
@AllArgsConstructor
public class ErrorEnvelope {
    //private int httpStatus;
    private String ts;
    private String message;

}
