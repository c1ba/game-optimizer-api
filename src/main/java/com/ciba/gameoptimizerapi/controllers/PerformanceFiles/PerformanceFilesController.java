package com.ciba.gameoptimizerapi.controllers.PerformanceFiles;

import com.ciba.gameoptimizerapi.requests.PerformanceFilesRequest;
import com.ciba.gameoptimizerapi.responses.PerformanceFilesResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RequestMapping("/performance_files")
public interface PerformanceFilesController {

    @PostMapping
    @Operation(summary = "Get All Components", description = "Gets all components.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation Successful"),
    })
    ResponseEntity<List<PerformanceFilesResponse>> getPerformanceFiles(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PerformanceFilesRequest request);
}
