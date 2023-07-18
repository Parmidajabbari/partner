package com.digiexpress.partner.web.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ExceptionResponse {

    private int status;
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private Date timestamp;
    private String message;
    private String code;

    public ExceptionResponse() {
        timestamp = new Date();
    }


    public ExceptionResponse(HttpStatus status, Throwable ex) {
        this();
        this.message = ex.getMessage();
        this.status = status.value();
        this.code = status.name();
    }
}