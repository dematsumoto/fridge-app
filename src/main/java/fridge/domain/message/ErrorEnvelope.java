package fridge.domain.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ErrorEnvelope {
    private String ts;
    private String message;

}
