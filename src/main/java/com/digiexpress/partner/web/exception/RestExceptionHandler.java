package com.digiexpress.partner.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse badRequest(BadRequestException ex) {
        return getAppropriateException(HttpStatus.BAD_REQUEST, ex);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ExceptionResponse notFound(BadRequestException ex) {
        return getAppropriateException(HttpStatus.NOT_FOUND, ex);
    }

    public ExceptionResponse getAppropriateException(HttpStatus httpStatus, Exception ex) {
        return new ExceptionResponse(httpStatus, ex);
    }
}
