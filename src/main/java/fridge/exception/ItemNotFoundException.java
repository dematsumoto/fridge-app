package fridge.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

/**
 * Created by douglas on 10/18/17.
 */

@Slf4j
public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(String message){
        super(message);
    }


    public ItemNotFoundException(String message, Object... args) {
        super(MessageFormat.format(message,args));
    }

    public ItemNotFoundException(Exception e) {
        this(e.getMessage());
        String exceptionMessage = MessageFormat.format("Request failed due to: {0}", e.getMessage());
        log.error(exceptionMessage);
    }
}
