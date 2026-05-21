package com.safoev.NeoSlots.exception;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, UUID id) {
        super("%s с id %s не найден".formatted(entityName, id));
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
