package fridge.controller.exception;

import fridge.domain.error.ErrorEnvelope;
import fridge.exception.InvalidAddItemCriteriaException;
import fridge.exception.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Created by douglas on 10/18/17.
 */

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorEnvelope handleItemNotFoundException(ItemNotFoundException notFoundException){

        return new ErrorEnvelope(getLocalTimeNow(), notFoundException.getMessage());
    }


    @ExceptionHandler(InvalidAddItemCriteriaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorEnvelope handlePostCriteriaException(InvalidAddItemCriteriaException ie){
        return new ErrorEnvelope(getLocalTimeNow(), ie.getMessage());

    }

    private String getLocalTimeNow(){
        return LocalDateTime.now().toString();

    }

}
