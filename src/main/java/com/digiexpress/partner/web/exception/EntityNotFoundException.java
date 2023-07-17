package com.digiexpress.partner.web.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

}