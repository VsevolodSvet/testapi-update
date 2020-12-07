package com.kochmarev.testapiupdate.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoContentToUpdateException extends Exception{

    public NoContentToUpdateException(String message){
        super(message);
    }

    public NoContentToUpdateException(){
        super();
    }

}
