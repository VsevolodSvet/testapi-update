package com.kochmarev.testapiupdate.rest.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Formatter;

@ControllerAdvice
@Slf4j
public class PaymentOrderExceptionHandler {

    @ExceptionHandler(NoContentToUpdateException.class)
    public ResponseEntity<?> noContentToUpdateExceptionHandler(NoContentToUpdateException e, WebRequest request) {
        String errorText = formatErrorMessage(e, request);
        log.error(errorText);
        return new ResponseEntity<>(errorText, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> POExceptionHandler(Exception e, WebRequest request) {
        String errorText = formatErrorMessage(e, request);
        log.error(errorText);
        return new ResponseEntity<>(errorText, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String formatErrorMessage(Exception e, WebRequest request) {
        return new Formatter().format("\n%s\n%s\n", e.getMessage(), request.getDescription(false)).toString();
    }
}
