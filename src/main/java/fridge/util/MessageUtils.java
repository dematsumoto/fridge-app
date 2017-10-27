package fridge.util;

import fridge.domain.SuccessMessage;

import java.time.LocalDateTime;

/**
 * Created by dmatsumoto on 10/26/17.
 */
public class MessageUtils {
    public static SuccessMessage messageBuilder(String message){
        //SuccessMessage successMessage = new SuccessMessage();
        //successMessage.setTs(LocalDateTime.now().toString());
        //successMessage.setMessage(message);
        return new SuccessMessage(LocalDateTime.now().toString(), message);

    }
}
