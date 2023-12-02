package com.ciba.gameoptimizerapi.controllers;

import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/component_combos")
public interface ComponentsComboController {

    @PostMapping
    @Operation(summary = "Post Component Combo", description = "Posts component combo for user taking each component.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    ResponseEntity<Void> postComponentCombo(@RequestBody PostComponentsComboRequest request);
}
