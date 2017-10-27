package fridge.util;

import fridge.domain.SuccessMessage;

import java.time.LocalDateTime;

/**
 * Created by dmatsumoto on 10/26/17.
 */
public class MessageUtils {
    public static SuccessMessage messageBuilder(String message){
        return new SuccessMessage(LocalDateTime.now().toString(), message);

    }
}
