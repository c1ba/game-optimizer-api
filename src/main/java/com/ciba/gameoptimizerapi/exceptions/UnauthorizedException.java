package com.ciba.gameoptimizerapi.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
@Slf4j
public class UnauthorizedException extends Exception {

    public UnauthorizedException(String message) {
        super(message);
        log.error(message);
    }
}
