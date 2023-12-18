package com.ciba.gameoptimizerapi.controllers.ComponentsCombo;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.requests.PostComponentsComboRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RequestMapping("/component_combos")
public interface ComponentsComboController {

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_USER') OR hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Post Component Combo", description = "Posts component combo for user taking each component.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created")
    })
    ResponseEntity<Void> postComponentCombo(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PostComponentsComboRequest request) throws BadRequestException;
}
