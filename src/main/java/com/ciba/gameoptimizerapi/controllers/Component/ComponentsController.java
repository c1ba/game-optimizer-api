package com.ciba.gameoptimizerapi.controllers.Component;


import com.ciba.gameoptimizerapi.responses.ComponentResponse;
import com.ciba.gameoptimizerapi.responses.SortedComponentsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@RequestMapping("/components")
public interface ComponentsController {

    @GetMapping
    @Operation(summary = "Get All Components", description = "Gets all components.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    ResponseEntity<SortedComponentsResponse> getAllComponents();
}
