package com.ciba.gameoptimizerapi.controllers;


import com.ciba.gameoptimizerapi.responses.ComponentResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@RequestMapping("/components")
public interface ComponentsController {

    @GetMapping
    @Operation(summary = "Get All Components", description = "Gets all components.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    ResponseEntity<List<ComponentResponse>> getAllComponents();
}
