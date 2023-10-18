package com.example.userapp.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundException extends UserException {

    private final String entity;

    public EntityNotFoundException(String message, String entity) {
        super(message);
        this.entity = entity;
    }
}
