package fridge.domain.message;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class SuccessMessage {
    private String ts;
    private String message;

    public SuccessMessage(){}
}


