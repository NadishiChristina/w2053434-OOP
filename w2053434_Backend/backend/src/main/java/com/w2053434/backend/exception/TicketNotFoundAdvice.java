package com.w2053434.backend.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TicketNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TicketNotFoundException.class) //if you're getting the error, send the request to this handler
    @ResponseStatus(HttpStatus.NOT_FOUND)//response for "notfound"
    public Map<String,String> exceptionHandler(TicketNotFoundException exception){

        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("errorMessage",exception.getMessage());

        return errorMap;
    }

}

