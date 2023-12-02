package com.ciba.gameoptimizerapi.controllers;

import com.ciba.gameoptimizerapi.requests.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public interface UserController {

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Returns the user's token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    ResponseEntity<String> login(@RequestBody LoginRequest request);
}
