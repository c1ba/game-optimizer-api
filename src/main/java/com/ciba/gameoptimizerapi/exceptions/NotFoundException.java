package com.ciba.gameoptimizerapi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Slf4j
public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
