package com.ciba.gameoptimizerapi.controllers.Component;


import com.ciba.gameoptimizerapi.exceptions.NotFoundException;
import com.ciba.gameoptimizerapi.exceptions.UnauthorizedException;
import com.ciba.gameoptimizerapi.responses.ComponentResponse;
import com.ciba.gameoptimizerapi.responses.SortedComponentsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Validated
public interface ComponentsController {

    @GetMapping("/components")
    @Operation(summary = "Get All Components", description = "Gets all components.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    ResponseEntity<SortedComponentsResponse> getAllComponents();

    @DeleteMapping("/components/{uuid}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Operation(summary = "Delete component", description = "Delete component by UUID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401/403", description = "Unauthorized"),
    })
    ResponseEntity<Void> delete(UserDetails userDetails, @PathVariable(value = "uuid") UUID uuid) throws NotFoundException, UnauthorizedException;
}
