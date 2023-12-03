package com.ciba.gameoptimizerapi.controllers.User;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.requests.LoginRequest;
import com.ciba.gameoptimizerapi.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public ResponseEntity<String> login(LoginRequest request) throws BadRequestException {
        return ResponseEntity.ok().body(service.login(request.getUsername(), request.getPassword()));
    }
}
