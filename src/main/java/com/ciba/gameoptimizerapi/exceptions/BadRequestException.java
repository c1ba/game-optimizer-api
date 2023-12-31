package com.ciba.gameoptimizerapi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
@Slf4j
public class BadRequestException extends Exception {

    public BadRequestException(String message) {
        super(message);
        log.error(message);
    }
}
