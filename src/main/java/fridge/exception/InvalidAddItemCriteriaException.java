package fridge.exception;


import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;

/**
 * Created by dmatsumoto on 10/31/17.
 */

@Slf4j
public class InvalidAddItemCriteriaException extends RuntimeException{

    public InvalidAddItemCriteriaException(String message){
        super(message);
    }


    public InvalidAddItemCriteriaException(String message, Object... args) {
        super(MessageFormat.format(message,args));
    }

    public InvalidAddItemCriteriaException(Exception e) {
        this(e.getMessage());
        String exceptionMessage = MessageFormat.format("Request failed due to: {0}", e.getMessage());
        log.error(exceptionMessage);
    }
    
}
