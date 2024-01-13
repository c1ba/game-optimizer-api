package com.ciba.gameoptimizerapi.controllers.ComponentsCombo;

import com.ciba.gameoptimizerapi.exceptions.BadRequestException;
import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.ciba.gameoptimizerapi.exceptions.UnauthorizedException;
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

import java.util.UUID;


@Validated
public interface ComponentsComboController {

    @PostMapping("/component_combos")
    @Operation(summary = "Post Component Combo", description = "Posts component combo for user taking each component. Logged users only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    ResponseEntity<Void> postComponentCombo(UserDetails userDetails, @RequestBody PostComponentsComboRequest request) throws BadRequestException, UnauthorizedException;

    @DeleteMapping("/component_combos/{uuid}")
    @Operation(summary = "Delete component combo", description = "Deletes component combo by UUID. Admin only.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    ResponseEntity<Void> delete(UserDetails userDetails, @PathVariable(value = "uuid") UUID uuid) throws NotFoundException, UnauthorizedException;
}
