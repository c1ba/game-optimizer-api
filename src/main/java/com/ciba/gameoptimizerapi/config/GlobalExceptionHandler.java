package com.ciba.gameoptimizerapi.config;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    ObjectMapper objectMapper;

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<Object> handleBadRequest(BadRequestException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(writeJsonBody(exception.getMessage()));
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(writeJsonBody(exception.getMessage()));
    }

    private Map<String, Object> writeJsonBody(String message) {
        final Map<String, Object> jsonBody = new HashMap<>();
        jsonBody.put("message", message);
        return jsonBody;
    }
}
